package sentimentanalysis.HTMLtoXML.critica;

import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import sentimentanalysis.HTMLtoXML.tags.Tags;

/**
 * Annotator que pega duas tags e seleciona o texto contindo entre elas
 */
public class CriticaAnnotator extends JCasAnnotator_ImplBase {
	/**
	 * Size in characters of window within which a RoomNumber, a Date, and two
	 * Times must occur in order for a meeting annotation to be created.
	 */
	@SuppressWarnings("unused")
	private int mWindowSize;
	private Pattern nomeIni = Pattern.compile("<span class=\"texto_titulo\">");
	private Pattern nomeFim = Pattern.compile("</span>");
	private Pattern autorIni = Pattern.compile("Por <span class=\"texto_subtitulo\">");
	private Pattern autorFim = Pattern.compile("</span><br>");
	private Pattern resenhaIni = Pattern.compile("<span>"); 
	private Pattern resenhaFim = Pattern.compile("</P>						</span>");
	private Pattern notaIni = Pattern.compile("<td class=\"texto_titulo\" width=\"10%\">");
	private Pattern notaFim = Pattern.compile("</td>");
	
	

	/**
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	public void initialize(UimaContext aContext)
	throws ResourceInitializationException {
		super.initialize(aContext);
		// Get config. parameter value
		mWindowSize = ((Integer) aContext.getConfigParameterValue("WindowSize"))
		.intValue();
	}

	/**
	 * @see JCasAnnotator_ImplBase#process(JCas)
	 */
	public void process(JCas aJCas) {
		// get annotation indexes
		FSIndex tagsIndex = aJCas.getAnnotationIndex(Tags.type);

		// procuarar a critica
		Tags tag1 = null, tag2 = null;
		String strTag1 = null, strTag2 = null;
		Iterator tagsIter = tagsIndex.iterator();
		while (tagsIter.hasNext()) {
			tag1 = (Tags) tagsIter.next();

			// Tem que iniciar com crítica e terminar com notas
			strTag1 = tag1.getBuilding();
			if (strTag1.compareTo("criFim") == 0 && strTag2 != null)/* fim */
			{
				if (strTag2.compareTo("criIni") == 0)/* inicio */
				{
					@SuppressWarnings("unused")
					Critica inte = new Critica(aJCas, tag2
							.getBegin(), tag1.getEnd(), tag2, tag1);
					inte.addToIndexes();
					// procura pelas tags que estao dentro da critica
					
					String critica = inte.getCoveredText();
					
					// procurando tag de inicio de titulo
					Matcher matcher = nomeIni.matcher(critica);
				    while (matcher.find()) {
				      TagsHtml annotation = new TagsHtml(aJCas);
				      annotation.setBegin(matcher.start()+tag2.getBegin());
				      annotation.setEnd(matcher.end()+tag2.getBegin());
				      annotation.setBuilding("nomeIni");
				      annotation.addToIndexes();
				    }
				    
					// procurando tag de fim de titulo
				    matcher = nomeFim.matcher(critica);
				    while (matcher.find()) {
				      TagsHtml annotation = new TagsHtml(aJCas);
				      annotation.setBegin(matcher.start()+tag2.getBegin());
				      annotation.setEnd(matcher.end()+tag2.getBegin());
				      annotation.setBuilding("nomeFim");
				      annotation.addToIndexes();
				    }
				    
				    // procurando tag de inicio de autor
				    matcher = autorIni.matcher(critica);
				    while (matcher.find()) {
				      TagsHtml annotation = new TagsHtml(aJCas);
				      annotation.setBegin(matcher.start()+tag2.getBegin());
				      annotation.setEnd(matcher.end()+tag2.getBegin());
				      annotation.setBuilding("autorIni");
				      annotation.addToIndexes();
				    }
				    
				    // procurando tag de fim de autor
				    matcher = autorFim.matcher(critica);
				    while (matcher.find()) {
				      TagsHtml annotation = new TagsHtml(aJCas);
				      annotation.setBegin(matcher.start()+tag2.getBegin());
				      annotation.setEnd(matcher.end()+tag2.getBegin());
				      annotation.setBuilding("autorFim");
				      annotation.addToIndexes();
				    }
				    
				    // procurando tag de inicio de resenha
				    matcher = resenhaIni.matcher(critica);
				    while (matcher.find()) {
				      TagsHtml annotation = new TagsHtml(aJCas);
				      annotation.setBegin(matcher.start()+tag2.getBegin());
				      annotation.setEnd(matcher.end()+tag2.getBegin());
				      annotation.setBuilding("resenhaIni");
				      annotation.addToIndexes();
				    }
				    
				    // procurando tag de fim de resenha
				    matcher = resenhaFim.matcher(critica);
				    while (matcher.find()) {
				      TagsHtml annotation = new TagsHtml(aJCas);
				      annotation.setBegin(matcher.start()+tag2.getBegin());
				      annotation.setEnd(matcher.end()+tag2.getBegin());
				      annotation.setBuilding("resenhaFim");
				      annotation.addToIndexes();
				    }
				    
				    //procurando tag de inicio de nota
				    matcher = notaIni.matcher(critica);
				    while (matcher.find()) {
				      TagsHtml annotation = new TagsHtml(aJCas);
				      annotation.setBegin(matcher.start()+tag2.getBegin());
				      annotation.setEnd(matcher.end()+tag2.getBegin());
				      annotation.setBuilding("notaIni");
				      annotation.addToIndexes();
				    }
				    
				    // procurando tag de fim de nota
				    matcher = notaFim.matcher(critica);
				    while (matcher.find()) {
				      TagsHtml annotation = new TagsHtml(aJCas);
				      annotation.setBegin(matcher.start()+tag2.getBegin());
				      annotation.setEnd(matcher.end()+tag2.getBegin());
				      annotation.setBuilding("notaFim");
				      annotation.addToIndexes();
				    }
				    
					// passa para o proximo ja que tag1 nao é inicio de críticas
					if (tagsIter.hasNext()) {
						tag2 = (Tags) tagsIter.next();
						strTag2 = tag2.getBuilding();
					}
				}
			} else {
				tag2 = tag1;
				strTag2 = strTag1;
			}

		}
	}

}
