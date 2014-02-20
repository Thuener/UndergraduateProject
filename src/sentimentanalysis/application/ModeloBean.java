/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */
package sentimentanalysis.application;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Jama.Matrix;

/**
 * Esse programa tem por objetivo armazenar um modelo de SVM gerado
 */
public class ModeloBean {
	// Elementos que serao procurados no xml
	private double b;
	private double[][] Dalfa;
	private double[] SV;
	private Vector<Hashtable<String, Integer>> dicNeg;
	private Vector<Hashtable<String, Integer>> dicPos;

	/**
	 * @param outFile
	 */
	public void obtemDadosModelo(String outFile) {
		try {
			// Abre o arquivo xml de entrada
			File inFile = new File(outFile);
			

			// Faz um parse do arquivo de xml
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = dbfactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(inFile);

			// verifica se o parse foi feito
			if (doc == null) {
				System.out.println("Arquivo nao existe");
				return;
			}

			// Declara as varaáveis que vão ser usadas na obtenção dos dados do xml
			int i;
			Element root = doc.getDocumentElement();
			NodeList lista = root.getChildNodes();
			String strb = root.getAttribute("b");
			b = Double.parseDouble(strb);

			// Obtém a matriz SV
			NodeList listSV = root.getElementsByTagName("Sv");
			Element SVEle = (Element) listSV.item(0);
			NodeList listItemS = SVEle.getElementsByTagName("Item");
			SV = new double[listItemS.getLength()];
			Element itemEleS;
			for (i = 0; i < listItemS.getLength(); i++) {
				itemEleS = (Element) listItemS.item(i);
				SV[Integer.parseInt(itemEleS.getAttribute("index"))] = Double
						.parseDouble(itemEleS.getAttribute("valor"));
			}

			// Obtém a matriz Dalfa
			int ind = 0;
			NodeList listDalfa = root.getElementsByTagName("Dalfa");
			Element dalfaEle = (Element) listDalfa.item(0);
			NodeList listItemD = dalfaEle.getElementsByTagName("Item");
			Element itemEleD;
			Dalfa = new double[1][listItemD.getLength()];
			for (i = 0; i < listItemD.getLength(); i++) {
				// Se for uma posição valida do
				if (SV[i] == 1) {
					itemEleD = (Element) listItemD.item(i);
					Dalfa[0][ind] = Double.parseDouble(itemEleD.getAttribute("valor"));
					ind++;
				}
			}

			// Obtém o dicionário de cada crítia positiva utilizada no treino
			int y;
			Element CriPosEle = (Element) lista.item(5);
			NodeList listDicPos = CriPosEle.getElementsByTagName("Dicionario");
			dicPos = new Vector<Hashtable<String, Integer>>(listDicPos.getLength());
			Element dicEleP;
			String[] strDicP;
			Hashtable<String, Integer> hasTempP;
			String[] wordFreP;

			// pecorre cada dicionário
			for (i = 0; i < listDicPos.getLength(); i++) {
				// Se sv[i] for igual a 1 insere no dicionario
				if (SV[i] == 1) {
					dicEleP = (Element) listDicPos.item(i);
					strDicP = (dicEleP.getTextContent().trim()).split(" ");
					hasTempP = new Hashtable<String, Integer>();
					// percorre as palavras do dicionário
					for (y = 0; y < strDicP.length; y++) {
						wordFreP = strDicP[y].split(":");
						hasTempP.put(wordFreP[0], Integer.parseInt(wordFreP[1]));
					}
					dicPos.add(hasTempP);
				}
			}

			// Obtém o dicionário de cada crítia negativa utilizada no treino
			int svInd = i;
			y = 0;
			
			Element CriNegEle = (Element) lista.item(7);
			NodeList listDicNeg = CriNegEle.getElementsByTagName("Dicionario");
			dicNeg = new Vector<Hashtable<String, Integer>>(listDicNeg.getLength());
			Element dicEleN;
			String[] strDicN;
			Hashtable<String, Integer> hasTempN;
			String[] wordFreN;

			// pecorre cada dicionário
			for (i = 0; i < listDicNeg.getLength(); i++) {
				// Se sv[i] for igual a 1 insere no dicionario
				if (SV[svInd + i] == 1) {
					dicEleN = (Element) listDicNeg.item(i);
					strDicN = (dicEleN.getTextContent().trim()).split(" ");
					hasTempN = new Hashtable<String, Integer>();
					// percorre as palavras do dicionário
					for (y = 0; y < strDicN.length; y++) {
						wordFreN = strDicN[y].split(":");
						hasTempN.put(wordFreN[0], Integer.parseInt(wordFreN[1]));
					}
					dicNeg.add(hasTempN);
				}
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param critica
	 * @return
	 */
	public double classifica(Hashtable critica) {
		Matrix mDalfa = new Matrix(Dalfa);

		Matrix k1 = new Matrix(Dalfa[0].length, 1, 0.0000);
		Matrix B1 = new Matrix(1, 1, b);

		int linha = 0;

		Iterator itPos = dicPos.iterator();
		Iterator itNeg = dicNeg.iterator();
		while (itPos.hasNext()) {
			k1.set(linha, 0, kernelfuncnorm((Hashtable) itPos.next(), critica));
			linha++;
		}

		while (itNeg.hasNext()) {
			k1.set(linha, 0, kernelfuncnorm((Hashtable) itNeg.next(), critica));
			linha++;
		}
		Matrix aux = new Matrix(1, 1);
		aux = mDalfa.times(k1);
		Matrix aux2 = aux.plus(B1);
		return aux2.get(0, 0);

	}

	/**
	 * @param dic
	 * @param dic2
	 * @return
	 */
	public double kernelfuncnorm(Hashtable dic, Hashtable dic2) {
		return innerprod(dic, dic2) / Math.sqrt(innerprod(dic, dic) * innerprod(dic2, dic2));
	}

	/**
	 * @param dicPos2
	 * @param dic2
	 * @return
	 */
	public int innerprod(Hashtable dicPos2, Hashtable dic2) {
		int temp = 0;
		String st = (String) dicPos2.get(0);
		Iterator it = dicPos2.keySet().iterator();
		String tm;
		while (it.hasNext()) {
			try {
				tm = (String) it.next();
				temp = temp + ((Integer) dic2.get(tm) * (Integer) dicPos2.get(tm));
			} catch (NullPointerException e) {
				// faz nada
			}
		}
		return temp;
	}
}
