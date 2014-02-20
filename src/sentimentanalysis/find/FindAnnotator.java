/**
 * /Dados/UIMA/: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */

package sentimentanalysis.find;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

public class FindAnnotator extends JCasAnnotator_ImplBase {

	ArrayList<Pattern> ar = new ArrayList<Pattern>();
	
	// Tags que vão ser usadas no xml
	private Pattern iniRes = Pattern.compile("<Resenha>");
	private Pattern fimRes = Pattern.compile("</Resenha>");
	private Pattern iniCri = Pattern.compile("<Critica>");
	private Pattern fimCri = Pattern.compile("</Critica>");
	private Pattern iniDoc = Pattern.compile("<Documento>");
	private Pattern fimDoc = Pattern.compile("</Documento>");
	private Pattern iniNom = Pattern.compile("<Nome>");
	private Pattern fimNom = Pattern.compile("</Nome>");

	/*
	 * Obtém as palvras do arquivo e coloca no ArrayList 
	 */
	public void getWords() throws IOException {
		
		String str;
		
		// Arquivo de entrada com as palavras procuradas
		BufferedReader in = new BufferedReader(
				new FileReader(
						new File(
								"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/lixo/InFile/find.txt")));
		while ((str = in.readLine()) != null) {
			ar.add(Pattern.compile("([ \t\r\n]|\\.|,|<|>|;|:|\\{|\\}|\\[|\\]|\\(|\\)|!|\"|=|\\?|\'|\')"
					+str+"([ \t\r\n]|\\.|,|<|>|;|:|\\{|\\}|\\[|\\]|\\(|\\)|!|\"|=|\\?|\'|\')"));
		}
		in.close();
	}

	public void process(JCas aJCas) {

		try {
			this.getWords();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Obtém o texto do documento
		String docText = aJCas.getDocumentText();
		Matcher matcherString, matcherIniCri, matcherFimCri, matcherIniRes, matcherFimRes, matcherIniDoc, matcherFimDoc, matcherIniNom, matcherFimNom;
		Pattern pattern;
		Iterator it;
		String part, critica;
		int start, end, flag = 1, aux, startc, endc;

		// Procura a pelas tags de fim e inicio de crítica
		matcherIniCri = iniCri.matcher(docText);
		matcherFimCri = fimCri.matcher(docText);
		while (matcherIniCri.find()) {
			if (matcherFimCri.find()) {
				startc = matcherIniCri.end();
				endc = matcherFimCri.start();
				// Limita-se a procurar dentro da crítica 
				critica = docText.substring(startc, endc);
				// Procura a pelas tags de fim e inicio de resenha
				matcherIniRes = iniRes.matcher(critica);
				matcherFimRes = fimRes.matcher(critica);
				while (matcherIniRes.find()) {
					if (matcherFimRes.find()) {
						start = matcherIniRes.end();
						end = matcherFimRes.start();
						
						part = critica.substring(start, end);
						flag = 0;
						// Procura as palavras
						it = ar.iterator();
						while (it.hasNext()) {
							pattern = (Pattern) it.next();
							matcherString = pattern.matcher(part.toLowerCase());
							aux = 1;
							while (matcherString.find()) {
								FindTag annotation = new FindTag(aJCas);
								annotation.setBegin(matcherString.start() + start + startc);
								annotation.setEnd(matcherString.end() + start + startc);
								annotation.setBuilding("procura");
								annotation.addToIndexes();
								aux = 0;
							}
							// Se não achou a primeira tag então não é a crítica procurada  
							if (aux == 1 && flag == 0)
							{
								flag = 1;
								break;
							}
						}
						if (flag == 0) {
							Critica annotationCri = new Critica(aJCas);
							annotationCri.setBegin(startc);
							annotationCri.setEnd(endc);
							annotationCri.setBuilding("CriticaEsperada");
							annotationCri.addToIndexes();

							matcherIniDoc = iniDoc.matcher(critica);
							matcherFimDoc = fimDoc.matcher(critica);
							while (matcherIniDoc.find()) {
								if (matcherFimDoc.find()) {
									TagsXml annotationDoc = new TagsXml(aJCas);
									annotationDoc.setBegin(matcherIniDoc.end() + startc);
									annotationDoc.setEnd(matcherFimDoc.start() + startc);
									annotationDoc.setBuilding("Documento");
									annotationDoc.addToIndexes();
								}
							}
							matcherIniNom = iniNom.matcher(critica);
							matcherFimNom = fimNom.matcher(critica);
							while (matcherIniNom.find()) {
								if (matcherFimNom.find()) {
									TagsXml annotationDoc = new TagsXml(aJCas);
									annotationDoc.setBegin(matcherIniNom.end() + startc);
									annotationDoc.setEnd(matcherFimNom.start() + startc);
									annotationDoc.setBuilding("Nome");
									annotationDoc.addToIndexes();
								}
							}
							return;
						}
					}
				}
			}
		}
	}
}
