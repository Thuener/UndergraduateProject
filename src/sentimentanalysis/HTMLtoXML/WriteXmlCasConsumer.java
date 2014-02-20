/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */
package sentimentanalysis.HTMLtoXML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.collection.base_cpm.CasObjectProcessor;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import sentimentanalysis.HTMLtoXML.interpretador.DadosXml;

/**
 * Esse programa consome as anotacoes feitas pelo interpretador
 * Criando o arquivo de xml de saida do programa
 */
public class WriteXmlCasConsumer extends CasConsumer_ImplBase implements CasObjectProcessor {

	public static final String PARAM_OUTPUTFILE = "outputFile";
	public static final String PARAM_TOPICO = "topico";
	File outFile;
	PrintWriter printWriter;
	private static Document xml;
	Element in;

	public void initialize() throws ResourceInitializationException {

		/* inicializa um fabrica de documentos DOM*/
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
			/* Cria um um documento xml */
			xml = factory.newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/* Extrai os parametros */
		String oPath = (String) getUimaContext().getConfigParameterValue(PARAM_OUTPUTFILE);
		String topico = (String) getUimaContext().getConfigParameterValue(PARAM_TOPICO);

		/* Verifica se os parametros foram passados e não aborta o programa */
		if (oPath == null || topico == null) {
			throw new ResourceInitializationException(
					ResourceInitializationException.CONFIG_SETTING_ABSENT,
					new Object[] { "outputFile" });
		}
		/* Se o diretorio especificado nao existir cria */
		outFile = new File(oPath.trim());
		if (outFile.getParentFile() != null && !outFile.getParentFile().exists()) {
			if (!outFile.getParentFile().mkdirs())
				throw new ResourceInitializationException(
						ResourceInitializationException.RESOURCE_DATA_NOT_VALID, new Object[] {
								oPath, "outputFile" });
		}
		try {

			/* Criar um PrintWriter com o arquivo de saida */
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFile),
					"UTF-8"));
			/* Cria um elemento In no xml */
			in = xml.createElement("In");
			/* adiciona na raiz do xml */
			xml.appendChild(in);
			/* Cria um elemento Topico no xml */
			Element top = xml.createElement("Topico");
			/* adiciona dentro da tag In*/
			in.appendChild(top);
			/* Cria um elemento text que contera o tipo do topico */
			Text txTop = xml.createTextNode(topico);
			/* adiciona dentro da tag top*/
			top.appendChild(txTop);

		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		}
	}

	public synchronized void processCas(CAS aCAS) throws ResourceProcessException {
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

		String docUri = null;
		Iterator it = jcas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
		if (it.hasNext()) {
			SourceDocumentInformation srcDocInfo = (SourceDocumentInformation) it.next();
			docUri = srcDocInfo.getUri();
		}

		/* Criando as tags do xml */
		Element ele;
		Text texto;
		Element critica = xml.createElement("Critica");
		in.appendChild(critica);
		if (docUri != null) {
			String[] str = docUri.toString().split("/");
			critica.setAttribute("id", (str[str.length - 1].split("\\."))[0]);
			AnnotationIndex anno = jcas.getAnnotationIndex(DadosXml.type);
			Iterator annotationIter = anno.iterator();
			while (annotationIter.hasNext()) {
				DadosXml annot = (DadosXml) annotationIter.next();

				/* Obtem o texto da annotation */
				String aText;
				aText = annot.getCoveredText();
				aText = aText.replace("\n", "");
				aText = aText.replace("\r", "");
				aText = aText.replace("\t", "");
				if (annot.getBuilding().compareTo("Resenha") == 0) {
					aText = aText.replace("</STRONG>", "");
					aText = aText.replace("<STRONG>", "");
					aText = aText.replace("&nbsp;", " ");
					aText = aText.replace("<P align=justify>", "");
					aText = aText.replace("</P>", "\n");
					aText = aText.replace("<BR> <BR>", "<BR><BR>");
					aText = aText.replace("<BR><BR>", "\n");
					aText = aText.replace("</EM>", "");
					aText = aText.replace("<EM>", "");
					aText = aText.replace("<BR>", "\n");
					aText = aText.replace("<P align=right>", "");
					aText = aText.replace("<I>", "");
					aText = aText.replace("</I>", "");
					aText = aText.replace("’", "\'");
					aText = aText.replace("‘", "\'");
					aText = aText.replace("”", "\"");
					aText = aText.replace("“", "\"");
					aText = aText.replace("–", "-");
					aText = aText.replace("&amp;", "&");
				}
				ele = xml.createElement(annot.getBuilding());
				critica.appendChild(ele);
				if (annot.getBuilding().equals("Resenha") || annot.getBuilding().equals("Nome"))
					texto = xml.createCDATASection(aText);
				else
					texto = xml.createTextNode(aText);
				ele.appendChild(texto);
			}
		}
	}

	public void batchProcessComplete(ProcessTrace aTrace) throws ResourceProcessException,
			IOException {
	}

	public void collectionProcessComplete(ProcessTrace aTrace)
			throws ResourceProcessException, IOException {
		if (printWriter != null) {
			/* cria o formato de saida do xml */
			OutputFormat format = new OutputFormat("xml", "UTF-8", true);
			format.setStandalone(false);
			format.setOmitXMLDeclaration(false);
			format.setOmitDocumentType(false);
			format.setIndent(1);
			format.setLineSeparator(System.getProperty("line.separator"));
			/* Seta o printWriter com o formato especificado */
			XMLSerializer serial = new XMLSerializer(printWriter, format);
			try {
				/* Imprime no arquivo de saida */
				serial.asDOMSerializer();
				serial.serialize(xml.getDocumentElement());
				printWriter.flush();

			} catch (IOException e) {
				System.err.println(e);
			} catch (Exception e) {
				System.err.println(e);
			}
			/* Fecha o arquivo de saida */
			printWriter.close();
		}
	}

	public void reconfigure() throws ResourceConfigurationException {
		super.reconfigure();
		/* Obtem os parametros */
		String oPath = (String) getUimaContext().getConfigParameterValue("outputFile");
		File oFile = new File(oPath.trim());
		/* Se o arquivo mudou, fecha o arquivo e cria um novo */
		if (!oFile.equals(this.outFile)) {
			this.outFile = oFile;
			try {
				printWriter.close();

				/*Se o arquivo especificado não existir cria um novo */
				if (oFile.getParentFile() != null && !oFile.getParentFile().exists()) {
					if (!oFile.getParentFile().mkdirs())
						throw new ResourceConfigurationException(
								ResourceInitializationException.RESOURCE_DATA_NOT_VALID, new Object[] {
										oPath, "outputFile" });
				}
				/* Abre um o arquivo para leitura */
				printWriter = new PrintWriter(new OutputStreamWriter(
						new FileOutputStream(outFile), "UTF-8"));
			} catch (IOException e) {
				throw new ResourceConfigurationException();
			}
		}
	}

	public void destroy() {
		if (printWriter != null) {
			printWriter.close();
		}
	}

}
