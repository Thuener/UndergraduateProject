/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */
package sentimentanalysis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/* 
 * Esse programa baixa a uma pagina HTML para o disco de acordo 
 * com a url, id e diretorio de saída especificado
 */
public class GetHTMLGene {
   public static int count=0;
   public void getHTML(String id,String surl,String outdir) {
	  System.out.println("Processando: "+id);
	  
      URL url;
      HttpURLConnection conn; 
      BufferedReader rd; 
      String line;
      /* local no qual os arquivos sao salvos */
      File outFile =  new File(outdir+id+".html");
      PrintWriter saida = null; 
      FileWriter fileWriter = null;
      try {
      	fileWriter = new FileWriter(outFile);
        saida = new PrintWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
      try {
    	  url = new URL(surl+id);
         conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         while ((line = rd.readLine()) != null) {
        	 saida.println(line);
         }
         saida.flush();
         rd.close();
         conn.disconnect();
      } catch (Exception e) {
         e.printStackTrace();
      }
      try {
      	fileWriter.close();
      	saida.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      count++;
   }
   public void ObterHtml(String url,String infile,String outdir)
   {
	   try {
		    /* arquivo de entrada no qual vao ser lidos os ids das criticas*/
	        BufferedReader in = new BufferedReader(new FileReader(new File(infile)));
	        String str;
	        while ((str = in.readLine()) != null) {
	        	this.getHTML(str,url,outdir);
	        }
	        in.close();
	        System.out.println("FIM numero de aquivos processados: " +this.count);
	    } catch (IOException e) {
	    }

	   
   }
   public static void main(String[] args){
  	 GetHTMLGene get = new GetHTMLGene();
  	 
  	 if(args[0] != null)
  		 get.ObterHtml(args[0],args[1], args[2]);
  	 else
  		 get.ObterHtml("http://www.cineplayers.com/critica.php?id=", "C:/Documents and Settings/thuener/Desktop/Trabalhando/Artigo/Dados/CorpusFinal/Ids/TreinoPos.TXT", "C:/Documents and Settings/thuener/Desktop/Trabalhando/Artigo/Dados/CorpusFinal/Html/TreinoPos/");
   }
}
