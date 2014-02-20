/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */
package sentimentanalysis.findCriticasHtml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;

import sentimentanalysis.util.GetHTMLGene;

/**
 * Programa procura no cineplayes por criticas de acordo com um arquivo de
 * entrada com as letras inciais das criticas e coloca todos os ids em um arquivo
 */
public class Main {

	public static JCas jcas;
	public static File[] files;
	public static CAS aCAS;
	public static PrintWriter fileWriterOut;

	public static void ListaArquivos() {
		File inputDir = new File(
				"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Data");
		// Obtem todos os arquivos do diretorio 
		files = inputDir.listFiles();

	}

	public static void main(String[] args) throws Exception {

		// Cria arquivo de saida
		File outFile = new File(
				"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/criticasurlsA-ZArt.txt");
		fileWriterOut = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFile),
				"UTF-8"));

		// Gera os htmls de entrada se for dado como parametro sim
		if (args[0] != null && args[0].equals("sim")) {
			GetHTMLGene get = new GetHTMLGene();
			get
					.ObterHtml(
							"http://www.cineplayers.com/index_criticas.php?letra=",
							"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Input/letras.txt",
							"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Data/");
		}
		ListaArquivos();
		String document;

		// Pega o recurso de arquivo XML
		XMLInputSource xml = new XMLInputSource(
				"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Descriptor/Find/Criticas/CriticasFindAnnotator.xml");
		ResourceSpecifier specifier = UIMAFramework.getXMLParser()
				.parseResourceSpecifier(xml);
		// Cria um Analysis Engine */
		AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
		// Cria um CAS para manipular os dados 
		aCAS = ae.newCAS();
		((DocumentAnnotation) aCAS.getJCas().getDocumentAnnotationFs()).setLanguage("pt");

		if (files == null) {
			System.out.println("Não há arquivos para processar nesse diretorio");
		} else {
			// Processa documentos
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isDirectory()) {
					// Transforma o HTML em string 
					document = FileUtils.file2String(files[i], "ISO-8859-1");
					document = document.trim();
					// analyze a document 
					aCAS.setDocumentText(document);
					ae.process(aCAS);
					// Imprime as anotacoes 
					printAnnotation();
					aCAS.reset();
				}
			}
		}
		fileWriterOut.close();
	}

	public static void printAnnotation() {
		// Escreve no arquivo de saida 
		String aText, id = "", nota = "";
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			e.printStackTrace();
		}

		// Imprime as anotacoes feitas 
		AnnotationIndex anno = jcas.getAnnotationIndex(FindTag.type);
		int flag = 0;
		// Obtem o iterador para as anotacoes 
		Iterator annotationIter = anno.iterator();
		while (annotationIter.hasNext()) {
			FindTag annot = (FindTag) annotationIter.next();
			aText = annot.getCoveredText();
			// Verifica se a anotacao e referente ao Id ou a Nota 
			if (annot.getBuilding().equals("Id")) {
				flag = 1;
				id = aText;
			} else if (annot.getBuilding().equals("Nota")) {
				nota = aText;
				// se já tiver pego o id imprime as anotacoes 
				if (flag == 1) {
					fileWriterOut.write(nota + ";" + id + "\n");
					flag = 0;
				}
			}
		}
	}
}
