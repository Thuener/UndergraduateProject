/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */

package sentimentanalysis.HTMLtoXML.tags;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

public class TagsAnnotator extends JCasAnnotator_ImplBase {
	
	// define as expressoes que serao procuradas
  private Pattern cri = Pattern.compile("crítica ::.");
  private Pattern not = Pattern.compile("Equipe ::.");

  public void process(JCas aJCas) {
  	
    // Obtem o texto do documento 
    String docText = aJCas.getDocumentText();

    // Procura por tags de incio de critica
    Matcher matcher = cri.matcher(docText);
    while (matcher.find()) {
      Tags annotation = new Tags(aJCas);
      annotation.setBegin(matcher.start());
      annotation.setEnd(matcher.end());
      annotation.setBuilding("criIni");
      annotation.addToIndexes();
    }
    
    //  Procurando tag de fim de critica
    matcher = not.matcher(docText);
    while (matcher.find()) {
      Tags annotation = new Tags(aJCas);
      annotation.setBegin(matcher.start());
      annotation.setEnd(matcher.end());
      annotation.setBuilding("criFim");
      annotation.addToIndexes();
    }    
  }
}