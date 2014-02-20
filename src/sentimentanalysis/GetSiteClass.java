/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */
package sentimentanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Obtem a nota de uma determinada crítica de acordo com o XML
 * 
 */

public class GetSiteClass {

	public static void main(String[] args) {
		try {
			int cont = 0;
			Hashtable<String, String> ht = new Hashtable<String, String>();
			String line;
			BufferedReader in = new BufferedReader(new FileReader(new File(args[2])));
			while ((line = in.readLine()) != null) {
				ht.put(line, cont + "");
				cont++;
			}

			File inXml1 = new File(args[0]);
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = dbfactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(inXml1);

			if (doc == null) {
				System.out.println("Arquivo nao existe");
				return;
			}
			int i;
			Element root = doc.getDocumentElement();
			NodeList listCritica = root.getElementsByTagName("Critica");
			Element nota, critica;
			NodeList listaNotas;
			String[] ids = new String[cont + 1], notas = new String[cont + 1];
			String strIndex;
			int index = 0;

			System.out.println("IdCritca;ClasificacaoSite");
			for (i = 0; i < listCritica.getLength(); i++) {
				critica = (Element) listCritica.item(i);
				listaNotas = critica.getElementsByTagName("Nota");
				nota = (Element) listaNotas.item(0);
				if ((strIndex = ht.get(critica.getAttribute("id"))) != null) {
					index = Integer.parseInt(strIndex);
					ids[index] = critica.getAttribute("id");
					notas[index] = nota.getTextContent();
				}
			}

			File inXml2 = new File(args[1]);
			DocumentBuilderFactory dbfactory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder2;
			builder2 = dbfactory2.newDocumentBuilder();
			Document doc2;
			doc2 = builder2.parse(inXml2);

			if (doc == null) {
				System.out.println("Arquivo nao existe");
				return;
			}
			Element root2 = doc2.getDocumentElement();
			NodeList listCritica2 = root2.getElementsByTagName("Critica");
			Element nota2, critica2;
			NodeList listaNotas2;
			for (i = 0; i < listCritica2.getLength(); i++) {
				critica2 = (Element) listCritica2.item(i);
				listaNotas2 = critica2.getElementsByTagName("Nota");
				nota2 = (Element) listaNotas2.item(0);
				if ((strIndex = ht.get(critica2.getAttribute("id"))) != null) {
					index = Integer.parseInt(strIndex);
					ids[index] = critica2.getAttribute("id");
					notas[index] = nota2.getTextContent();
				}
			}
			for (i = 0; i < cont; i++)
				System.out.println(notas[i]);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
