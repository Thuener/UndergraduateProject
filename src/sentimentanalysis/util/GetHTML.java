/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */
package sentimentanalysis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Obtem um paginas de criticas do site cineplayers e salva
 * em um diretorio 
 */
public class GetHTML {

	public static int count = 0;

	public void getHTML(String id) {

		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;

		System.out.println("Processando: " + id);

		/* local no qual os arquivos sao salvos */
		File outFile = new File(
				"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Data/"
						+ id + ".html");
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Obtem a critica de acordo com o id passado com parametro */
		try {
			url = new URL("http://www.cineplayers.com/critica.php?id=" + id);

			/* Cria uma conexao */
			conn = (HttpURLConnection) url.openConnection();
			/* determinar o tipo de metodo HTML que sera enviado */
			conn.setRequestMethod("GET");
			/* cria um BufferReader para ler o HTML */
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			/* le cada linha e escreve no arquivo de saida*/
			while ((line = rd.readLine()) != null) {
				printWriter.write(line);
			}
			/* fecha o arquivo de entrada */
			printWriter.flush();
			rd.close();
			printWriter.close();
			/* incrementa a variavel com a quantidade de paginas que foram
			 escritas */
			count++;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		GetHTML get = new GetHTML();
		try {
			/* arquivo de entrada no qual vao ser lidos os ids das criticas*/
			BufferedReader in = new BufferedReader(
					new FileReader(
							new File(
									"C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Input/urlsNegativos2.txt")));
			String str;
			/* le ate chegar ao final do arquivo */
			while ((str = in.readLine()) != null) {
				get.getHTML(str);
			}
			/* fecha o arquivo */
			in.close();
			System.out.println("FIM numero de aquivos processados: " + get.count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
