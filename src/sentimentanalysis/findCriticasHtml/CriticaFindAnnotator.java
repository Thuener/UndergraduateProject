/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */

package sentimentanalysis.findCriticasHtml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

/* 
 * Procura os atributos id e nota dentro de uma critica 
 */
public class CriticaFindAnnotator extends JCasAnnotator_ImplBase {
	// Inicializa as variaveis Pattern
	private Pattern Id = Pattern.compile("<a href=\"critica.php\\?id\\=" + "([0-9]*)"
			+ "\"");
	private Pattern Nota = Pattern.compile("<td>" + "([0-9]?[0-9].[0-9])" + "<\\/td>");
	private Pattern IniCriticas = Pattern.compile("Crítica - Avaliação");
	private Pattern FimCriticas = Pattern.compile("\\|\\| destaques ::.");

	public void process(JCas aJCas) {

		// Obtem o texto do documento
		String docText = aJCas.getDocumentText();
		Matcher matcherId, matcherNota, matcherIniCri, matcherFimCri;
		String part1, part2;
		int InicioCri;

		// Procura um incio de critica no texto
		matcherIniCri = IniCriticas.matcher(docText);
		if (matcherIniCri.find()) {
			InicioCri = matcherIniCri.end();
			// Procura um fim de critica no textoProcura
			matcherFimCri = FimCriticas.matcher(docText);
			if (matcherFimCri.find()) {
				// Limita a parte do texto a procura
				part1 = docText.substring(InicioCri, matcherFimCri.start());
				// Procura o id da critica na part1
				matcherId = Id.matcher(part1);
				while (matcherId.find()) {
					// limita que a nota a ser procurada tem que estar entre 10 a 150
					// caracteres depois do id
					part2 = docText.substring(InicioCri + matcherId.end() + 10, InicioCri
							+ matcherId.end() + 150);
					// Procura pela nota da critica na part2
					matcherNota = Nota.matcher(part2);
					// se achar a nota cria uma anotacao com ela
					if (matcherNota.find()) {
						FindTag nota = new FindTag(aJCas);
						nota.setBegin(InicioCri + matcherNota.start() + matcherId.end() + 14);
						nota.setEnd(InicioCri + matcherNota.end() + matcherId.end() + 5);
						nota.setBuilding("Nota");
						nota.addToIndexes();
					}
					// cria um anotacao com a id da critica mesmo que nao ache uma nota
					// para criticas
					FindTag annotation = new FindTag(aJCas);
					annotation.setBegin(InicioCri + matcherId.start() + 24);
					annotation.setEnd(InicioCri + matcherId.end() - 1);
					annotation.setBuilding("Id");
					annotation.addToIndexes();
				}
			}
		}
	}
}
