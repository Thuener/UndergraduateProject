/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */
package sentimentanalysis.application;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.uima.UIMAFramework;
import org.apache.uima.collection.CollectionProcessingEngine;
import org.apache.uima.collection.impl.metadata.cpe.CpeDescriptorFactory;
import org.apache.uima.collection.metadata.CasProcessorConfigurationParameterSettings;
import org.apache.uima.collection.metadata.CpeDescription;
import org.apache.uima.collection.metadata.CpeDescriptorException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import sentimentanalysis.util.GetHTMLGene;

;

/**
 * Esse programa baixa um html a partir de um id de crítica do cineplayers e
 * depois classifica essa crítica
 */
public class OLDInterpretaXMLModelo {
	
	
	private ModeloBean modPos;
	private ModeloBean modNeg;
	private ModeloBean modNeu;
	private GetHTMLGene get;
	private String dirTrans;
	
	public OLDInterpretaXMLModelo() {
		// Inicialização das variáveis que serão usadas
		
		/* Inicialização dos modelos positivo */
		modPos = new ModeloBean();
		modPos
				.obtemDadosModelo("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/Modelos/ModPositivos.xml");
		/* negativo */
		modNeg = new ModeloBean();
		modNeg
				.obtemDadosModelo("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/Modelos/ModNegativos.xml");
		/* neutro */
		modNeu = new ModeloBean();
		modNeu
				.obtemDadosModelo("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/Modelos/ModNeutros.xml");
		
		/* Inicialização da função auxiliar de obtenção do HTML */
		get = new GetHTMLGene();
		
		dirTrans = "C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Data/";
	}
	
	/**
	 * Verifica se existe algum expressão com ' que não deva ser particionada
	 * 
	 * @param tokens
	 * @param in
	 * @param part
	 * @param ini
	 * @param end
	 * @return
	 */
	public int verificaAsSimp(ArrayList<String> tokens, String in, String part,
			int ini, int end) {
		String aux;
		int ultimo = tokens.size() - 1;

		// verifica se há ao menos 2 tokens
		if (ultimo > 0) {
			// Obtém o ultimo token a ser inserido
			aux = tokens.get(ultimo);
			if (part.equals("s") || part.equals("S") || part.equals("d") || part.equals("D")
					|| part.equals("m") || part.equals("M")) {
				if (aux.equals("'")) {
					tokens.remove(ultimo);
					tokens.add(aux + part);
					return end;
				}
			} else if (part.equals("re") || part.equals("RE") || part.equals("ve")
					|| part.equals("VE") || part.equals("ll") || part.equals("LL")) {
				if (aux.equals("'")) {
					tokens.remove(ultimo);
					tokens.add(aux + part);
					return end;
				}
			} else if (part.equals("'") && aux.endsWith("s")) {
				tokens.remove(ultimo);
				tokens.add(aux + part);
				return end;
			}
		}
		return -1;
	}

	/**
	 * Faz um particionamento da palavras
	 * 
	 * @param in:
	 *          texto a ser particionado
	 * @return: lista de tokens encontrados
	 */
	public String[] mySplit(String in) {

		// Expressão que determinará como o texto será particionado
		Pattern pattern = Pattern.compile("(^$|^[A-Z][a-zA-Z]{0,3}\\.[A-Z]?|"
				+ "^[a-zA-Z]+$[a-zA-Z]{0,}|\\d+[,.]\\d+|\\.\\.+|,|\\w(?:\\.\\w)+\\.?|"
				+ "\\.|:$|;|\\?|[!]+|[nN]\'[tT]|\"|"
				+ "^O\'[A-Z][\\w]*|\'|\\[|\\]|\\{|\\}|\\(|\\))");

		Matcher matcher = pattern.matcher(in);
		ArrayList<String> tokens = new ArrayList<String>();
		int ultimo = 0, auxInt = 0;
		String subs;

		// Procuras pelos tokens
		while (matcher.find()) {
			subs = in.substring(ultimo, matcher.start());
			// So adiciona se nao for String vazia
			if (!subs.equals(""))
				tokens.add(subs);
			subs = in.substring(matcher.start(), matcher.end());

			// So adiciona se nao for String vazia e se não for expressão com aspas
			// simples
			if (!subs.equals("")) {
				if ((auxInt = verificaAsSimp(tokens, in, subs, matcher.start(), matcher.end())) == -1) {
					tokens.add(subs);
					ultimo = matcher.end();
				} else {
					// Então é uma expressão com aspas simples e atualiza o ultimo
					ultimo = auxInt;
				}
			}
		}
		// Verifica se não sobrou nenhuma palavra no final
		subs = in.substring(ultimo, in.length());
		if (!subs.equals("") && (verificaAsSimp(tokens, in, subs, ultimo, in.length())) == -1)
			tokens.add(subs);
		return (String[]) tokens.toArray(new String[tokens.size()]);
	}

	public void criaHtml(String tipo, String pagina, String diretorio) {
		File outFile = new File(diretorio + "saida.html");

		try {
			PrintWriter saida = new PrintWriter(new FileWriter(outFile));
			saida.println("<html>");
			saida.println("<body topmargin=\"0\" leftmargin=\"0\">");
			saida.println("<Center>");
			saida.println("<h1> Classificado como " + tipo + " </h1>");
			saida.println("<HR>");
			saida.println("</Center>");
			saida.println("<IFRAME name=palco src=\"" + pagina
					+ "\" frameBorder=0 width=100% height=80% scrolling=auto></IFRAME>");
			saida.println("</body>");
			saida.println("</html>");
			saida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Constroi um dicionário com as frequências das palavras
	 * 
	 * @param texto
	 * @return
	 */
	public Hashtable<String, Integer> ConstroiDic(String texto) {
		int num = 0;
		String[] textoSpli = texto.toLowerCase().split("\\s");
		String[] tokens;
		Hashtable<String, Integer> dic = new Hashtable<String, Integer>();

		for (int i = 0; i < textoSpli.length; i++) {
			tokens = mySplit(textoSpli[i]);

			for (int y = 0; y < tokens.length; y++) {
				try {
					num = dic.get(tokens[y]);
					dic.remove(textoSpli[i]);
					dic.put(tokens[y], num + 1);
				} catch (java.lang.NullPointerException e) {
					dic.put(tokens[y], 1);
				}
			}
		}
		return dic;
	}

	/**
	 * Retira as stops do texto
	 * 
	 * @param texto
	 * @return
	 */
	public void retiraStopWords(Hashtable<String, Integer> dic) {
		BufferedReader inStopWords;
		try {
			inStopWords = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									new File(
											"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/textclass/source/thuener/stopwords")),
							"UTF-8"));

			String line;

			while ((line = inStopWords.readLine()) != null) {
				if (line.equals("não")) {
					int y = 0;
					y++;
				}
				dic.remove(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletaDirArq(String inDir) {
		File inputDir = new File(inDir);
		// Obtem todos os arquivos do diretorio
		File[] files = inputDir.listFiles();
		for (int i = 0; i < files.length; i++)
			if (!files[i].isDirectory())
				files[i].delete();
	}

	public void Processa(String id, boolean Inter, boolean Del) {	
		// <Thuener> derrepente transformar esse CPE em um AE com o Interpretador
		// somente, tentar retirar tambem o input e output do processo
		// tentar colcar isso em outra classe <Thuener>
		
		File fl;
		if(Inter){
			
			// Obtém o HTML da critica
			get.getHTML(id, "http://www.cineplayers.com/critica.php?id=", dirTrans);
			
		}else{
			fl =  new File("C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/SiteTodo/TODO/"+id+".html");
			if(!fl.exists())
			{
				JOptionPane.showMessageDialog(null, "Não foi possive achar o arquivo"+id+".html no diretório C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/SiteTodo/TODO/","ERRO",JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			fl.renameTo(new File(dirTrans));
		}

		// Parse da descricao do CPE no arquivo
		CpeDescription cpeDesc;
		try {
			cpeDesc = UIMAFramework
					.getXMLParser()
					.parseCpeDescription(
							new XMLInputSource(
									"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/CPE.xml"));
			
			// Preenche os parâmetros do FileSystemCollectionReader
			CasProcessorConfigurationParameterSettings paraCR = CpeDescriptorFactory
					.produceCasProcessorConfigurationParameterSettings();
			paraCR.setParameterValue("InputDirectory", dirTrans);
			paraCR.setParameterValue("Language", "pt");
			cpeDesc.getAllCollectionCollectionReaders()[0]
					.setConfigurationParameterSettings(paraCR);

			// Preenche os parâmetros do WriteXml
			CasProcessorConfigurationParameterSettings paraWX = CpeDescriptorFactory
					.produceCasProcessorConfigurationParameterSettings();
			paraWX.setParameterValue("outputFile", dirTrans + id+".xml");
			paraWX.setParameterValue("topico", "ClassificarCritica");
			((cpeDesc.getCpeCasProcessors()).getCpeCasProcessor(1))
					.setConfigurationParameterSettings(paraWX);

			// Instacia o CPE
			CollectionProcessingEngine mCPE = UIMAFramework
					.produceCollectionProcessingEngine(cpeDesc);

			// Inicia o processamento
			mCPE.process();
			
			// Espera até o final do processamento
			int flag = 0;
			while (true) {
				// Espera até o começo do processamento
				if (mCPE.isProcessing() == true)
					flag = 1;
				// Sai do while assim que terminar o processamento
				if (mCPE.isProcessing() == false && flag == 1)
					break;
			}

			// Faz um parse do arquivo de xml
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = dbfactory.newDocumentBuilder();
			Document doc;
			doc = builder
					.parse(new File(
							"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Data/"+id+".xml"));
			Element root = doc.getDocumentElement();
			Element critica = (Element) (root.getElementsByTagName("Critica")).item(0);
			Element resenha = (Element) (critica.getElementsByTagName("Resenha")).item(0);

			Hashtable<String, Integer> hsCritica = ConstroiDic(resenha.getTextContent());
			retiraStopWords(hsCritica);

			/* classificação da crítica nos diferentes modelos */
			double pos = modPos.classifica(hsCritica);
			double neg = modNeg.classifica(hsCritica);
			double neu = modNeu.classifica(hsCritica);
			System.out.println("pos "+pos+" neg "+neg+" neu "+neu);
			String result= null;
			
			if(pos > neg && pos > neu)
				result = "Positiva";
			else if(neg > pos && neg > neu)
				result = "Negativa";
			else if(neu > neg && neu > pos)
				result = "Neutra";
			
			if(result == null)
				JOptionPane.showMessageDialog(null, "Não foi possivel classificar essa crítica ","ERRO",JOptionPane.INFORMATION_MESSAGE);
			else
			if(Inter)
				criaHtml(result, "http://www.cineplayers.com/critica.php?id="+id, dirTrans);
			else
				criaHtml(result, dirTrans+id+".html", dirTrans);
			
			Desktop.getDesktop().open(new File(dirTrans + "saida.html"));
			
			if(Del)
				deletaDirArq(dirTrans);

		} catch (InvalidXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CpeDescriptorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}