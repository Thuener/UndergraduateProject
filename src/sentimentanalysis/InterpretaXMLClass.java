/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de cente�do textual aplicado ao portugu�s
 * Empresa: PUC-Rio
 */
package sentimentanalysis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class InterpretaXMLClass {
	
	public static void main(String[] args) {

		// Abre o arquivo de saida
		File outFile = new File(
				"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/textclass/source/thuener/Log/TesteVicio.txt");
		PrintWriter printWriter;
		
		try {
			// Abre o arquivo xml de entrada			
			printWriter = printWriter = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(outFile)));
			File inFile = new File(
					"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/textclass/source/thuener/Log/TesteVicio.xml");
			
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
			
			// Declara as vara�veis que v�o ser usadas na obten��o dos dados do xml 
			int i, tipo, y, w;
			String num, idCri, result;
			Element root = doc.getDocumentElement();
			// Obt�m a lista de tags itera��o
			NodeList list = root.getElementsByTagName("Iteracao");
			Element iteracao, critica, criticaTipoElemento;
			NodeList listaCriticas, criticasTipo;
			
			printWriter.write("Iteracao;IdCritca;Clasificacao;Tipo\n");
			
			for (i = 0; i < list.getLength(); i++) {
				// Obt�m de cada itera��o o atributo num  
				iteracao = (Element) list.item(i);
				num = iteracao.getAttribute("num");
				// Obt�m de cada itera��o as tags filhas 
				criticasTipo = iteracao.getChildNodes();
				for (y = 1; y < criticasTipo.getLength(); y += 2) {
					// Obt�m a lista de tags de tipo de cr�tica
					criticaTipoElemento = (Element) criticasTipo.item(y);
					// preenche a vari�vel tipo
					if (criticaTipoElemento.getTagName().equals("CriticasNegativas12"))
						tipo = 0;
					else
						tipo = 1;
					// Obt�m a lista de tags cr�ticas
					listaCriticas = criticaTipoElemento.getElementsByTagName("Critica");
					for (w = 0; w < listaCriticas.getLength(); w++) {
						// Obt�m uma cr�tica e imprime os atributos obtidos
						critica = (Element) listaCriticas.item(w);
						idCri = critica.getAttribute("id");
						result = critica.getAttribute("result");
						printWriter.write(num + ";" + idCri + ";" + result + ";" + tipo + "\n");
					}
				}
			}
			printWriter.close();
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
}