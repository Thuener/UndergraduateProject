/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */

package sentimentanalysis.find;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;

import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public class Find {

	public static void main(String[] args) throws InvalidXMLException,
			ResourceInitializationException, ResourceProcessException {
		try {
			/* arquivo de entrada no qual vao ser lidos as classificacao das criticas */
			File claFile = new File("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/lixo/InFile/inCla.txt");
			BufferedReader inCla = new BufferedReader(new FileReader(claFile));

			/* arquivo de entrada no qual vao ser lidos os dicionario de palavras */
			File claDic = new File("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/lixo/InFile/inDic.txt");
			BufferedReader inDic = new BufferedReader(new FileReader(claDic));
			
			File FileXml = new File("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/lixo/In/p/Positivos.xml");
			// transforma o Positivos.xml em string
			String document = FileUtils.file2String(FileXml,"UTF-8");
		  document = document.trim();
		      
			File outFile = new File("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/lixo/out/output.txt");
			PrintWriter fileWriterOut = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
			
			String linhaCla, linhaDic;
			String[] split = new String[2];
			int i, y = 1,numCriPos=0;
			double cla;
			
			//pegar o numero de criticas positivas
			if((linhaDic = inDic.readLine()) != null)
			{
				numCriPos = Integer.parseInt(linhaDic);
				// Imprime as tags de Positivos
				fileWriterOut.write("Positivos\n");
			}
			else{
				System.out.println("Erro ao ler o aquivo de Dic");
				return;
			}
			
			while ((linhaCla = inCla.readLine()) != null) {
				split = linhaCla.split("=");
				i = Integer.parseInt(split[0]);
				cla = Double.parseDouble(split[1]);
				
				// Imprime as tags de Negativos e muda o document
				if(i == numCriPos+1){
					fileWriterOut.write("Negativos\n");
					FileXml = new File("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/lixo/In/n/Negativos.xml");
					// transforma o Negativos.xml em string
					document = FileUtils.file2String(FileXml,"UTF-8");
				  document = document.trim();
				}
					
				if(numCriPos < i && cla < 0)// nao pegar negativas com classificacao negativa
					continue;
				else if(numCriPos >= i && cla > 0)// nao pegar positivas com classificacao positivas
					continue;
				
				while ((linhaDic = inDic.readLine()) != null) {
					y++;
					// Le linha y e decarta as linha anteriores 
					if (i * 3 == y) {
						linhaDic = linhaDic.replace(" ", "\n");
						
						// Criando arquivo find.txt
						File outFind = new File("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/lixo/InFile/find.txt");
						PrintWriter fileWriterFind = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFind)));
						fileWriterFind.write(linhaDic.trim());
						fileWriterFind.close();
					      
						// get Resource Specifier from XML file
						XMLInputSource xml = new XMLInputSource("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Find/FindAnnotator.xml");
						ResourceSpecifier specifier = UIMAFramework
								.getXMLParser().parseResourceSpecifier(xml);
						// create AE here
						AnalysisEngine ae = UIMAFramework
								.produceAnalysisEngine(specifier);
						// create a CAS
						CAS aCAS = ae.newCAS();
						// analyze a document
						aCAS.setDocumentText(document);
						ae.process(aCAS);

						// Obtendo as annotation
						JCas jcas;
						try {
							jcas = aCAS.getJCas();
						} catch (CASException e) {
							throw new ResourceProcessException(e);
						}

						// iterate and print annotations
						AnnotationIndex anno = jcas
								.getAnnotationIndex(TagsXml.type);
						Iterator annotationIter = anno.iterator();
						String aText, stri,doc="erro",nom="erro";
						while (annotationIter.hasNext()) {
							TagsXml annot = (TagsXml) annotationIter.next();

							aText = annot.getCoveredText();
							if (annot.getBuilding().equals("Documento")) {
								String[] strVet = aText.split("/");
								doc = (strVet[strVet.length - 1]).split("\\.")[0]+ " ";
							} else if (annot.getBuilding().equals("Nome")) {
								stri = aText.substring(9,aText.length()-3);
								nom = "\""+stri + "\" ";
							}
						}
						fileWriterOut.write(nom+doc+"classificado com "+split[1]+"\n");
						aCAS.reset();
						ae.destroy();
						fileWriterOut.flush();
						break;
					}
				}
			}
		inCla.close();
		inDic.close();
		fileWriterOut.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
