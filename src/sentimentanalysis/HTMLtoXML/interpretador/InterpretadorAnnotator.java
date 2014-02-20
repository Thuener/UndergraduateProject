/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */

package sentimentanalysis.HTMLtoXML.interpretador;

import java.util.Hashtable;
import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import sentimentanalysis.HTMLtoXML.critica.TagsHtml;

public class InterpretadorAnnotator extends JCasAnnotator_ImplBase {

	private static Hashtable<String, String> tagsFimIni;

	public InterpretadorAnnotator() {
		tagsFimIni = new Hashtable<String, String>();
		tagsFimIni.put("nomeFim", "nomeIni");
		tagsFimIni.put("autorFim", "autorIni");
		tagsFimIni.put("resenhaFim", "resenhaIni");
		tagsFimIni.put("notaFim", "notaIni");
	}

	public void process(JCas aJCas) {
		// get annotation indexes
		FSIndex tagsHtmlIndex = aJCas.getAnnotationIndex(TagsHtml.type);

		// Procurar a tags
		TagsHtml tag1 = null, tag2 = null;
		String strTag1 = null, strTag2 = null, aux;
		Iterator tagsIter = tagsHtmlIndex.iterator();
		Interpretador inte = null;
		while (tagsIter.hasNext()) {
			tag1 = (TagsHtml) tagsIter.next();
			strTag1 = tag1.getBuilding();
			aux = tagsFimIni.get(strTag1);
			
			// compara para verifica se a tag de inicio que foi
			// achada anteriormente é compativel com a do HastTable
			if (strTag1 != null && strTag2 != null && aux != null
					&& (strTag2.compareTo(strTag1) != 0)
					&& (strTag2.compareTo(aux) == 0)) {
				if (inte == null) {
					inte = new Interpretador(aJCas);
				}
				
				// se achou uma determinada tag de inicio procura a respectiva tag de fim
				if (strTag2.compareTo("nomeIni") == 0) {
					DadosXml annotation = new DadosXml(aJCas, tag2.getEnd(),
							tag1.getBegin());
					annotation.setBuilding("Nome");
					annotation.addToIndexes();
					inte.setNome(annotation);
				} else if (strTag2.compareTo("autorIni") == 0) {
					DadosXml annotation = new DadosXml(aJCas, tag2.getEnd(),
							tag1.getBegin());
					annotation.setBuilding("Autor");
					annotation.addToIndexes();
					inte.setAutor(annotation);
				}  else if (strTag2.compareTo("resenhaIni") == 0) {
					DadosXml annotation = new DadosXml(aJCas, tag2.getEnd(),
							tag1.getBegin());
					annotation.setBuilding("Resenha");
					annotation.addToIndexes();
					inte.setResenha(annotation);
				} else if (strTag2.compareTo("notaIni") == 0) {
					DadosXml annotation = new DadosXml(aJCas, tag2.getEnd(),
							tag1.getBegin());
					annotation.setBuilding("Nota");
					annotation.addToIndexes();
					inte.setNota(annotation);
					inte.addToIndexes();
					inte = null;
				}
				
				// passa para o proximo já que tag1 nao pode ser inicio
				if (tagsIter.hasNext()) {
					tag2 = (TagsHtml) tagsIter.next();
					strTag2 = tag2.getBuilding();
				}
			} else {
				tag2 = tag1;
				strTag2 = strTag1;
			}
		}
	}
}
