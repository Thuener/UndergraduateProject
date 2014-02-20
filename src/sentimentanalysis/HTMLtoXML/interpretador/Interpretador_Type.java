
/* First created by JCasGen Wed Aug 08 16:10:31 BRT 2007 */
package sentimentanalysis.HTMLtoXML.interpretador;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Dec 06 13:33:34 GST 2007
 * @generated */
public class Interpretador_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Interpretador_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Interpretador_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Interpretador(addr, Interpretador_Type.this);
  			   Interpretador_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Interpretador(addr, Interpretador_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Interpretador.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
 
  /** @generated */
  final Feature casFeat_nome;
  /** @generated */
  final int     casFeatCode_nome;
  /** @generated */ 
  public int getNome(int addr) {
        if (featOkTst && casFeat_nome == null)
      jcas.throwFeatMissing("nome", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    return ll_cas.ll_getRefValue(addr, casFeatCode_nome);
  }
  /** @generated */    
  public void setNome(int addr, int v) {
        if (featOkTst && casFeat_nome == null)
      jcas.throwFeatMissing("nome", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    ll_cas.ll_setRefValue(addr, casFeatCode_nome, v);}
    
  
 
  /** @generated */
  final Feature casFeat_data;
  /** @generated */
  final int     casFeatCode_data;
  /** @generated */ 
  public int getData(int addr) {
        if (featOkTst && casFeat_data == null)
      jcas.throwFeatMissing("data", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    return ll_cas.ll_getRefValue(addr, casFeatCode_data);
  }
  /** @generated */    
  public void setData(int addr, int v) {
        if (featOkTst && casFeat_data == null)
      jcas.throwFeatMissing("data", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    ll_cas.ll_setRefValue(addr, casFeatCode_data, v);}
    
  
 
  /** @generated */
  final Feature casFeat_autor;
  /** @generated */
  final int     casFeatCode_autor;
  /** @generated */ 
  public int getAutor(int addr) {
        if (featOkTst && casFeat_autor == null)
      jcas.throwFeatMissing("autor", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    return ll_cas.ll_getRefValue(addr, casFeatCode_autor);
  }
  /** @generated */    
  public void setAutor(int addr, int v) {
        if (featOkTst && casFeat_autor == null)
      jcas.throwFeatMissing("autor", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    ll_cas.ll_setRefValue(addr, casFeatCode_autor, v);}
    
  
 
  /** @generated */
  final Feature casFeat_resenha;
  /** @generated */
  final int     casFeatCode_resenha;
  /** @generated */ 
  public int getResenha(int addr) {
        if (featOkTst && casFeat_resenha == null)
      jcas.throwFeatMissing("resenha", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    return ll_cas.ll_getRefValue(addr, casFeatCode_resenha);
  }
  /** @generated */    
  public void setResenha(int addr, int v) {
        if (featOkTst && casFeat_resenha == null)
      jcas.throwFeatMissing("resenha", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    ll_cas.ll_setRefValue(addr, casFeatCode_resenha, v);}
    
  
 
  /** @generated */
  final Feature casFeat_nota;
  /** @generated */
  final int     casFeatCode_nota;
  /** @generated */ 
  public int getNota(int addr) {
        if (featOkTst && casFeat_nota == null)
      jcas.throwFeatMissing("nota", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    return ll_cas.ll_getRefValue(addr, casFeatCode_nota);
  }
  /** @generated */    
  public void setNota(int addr, int v) {
        if (featOkTst && casFeat_nota == null)
      jcas.throwFeatMissing("nota", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    ll_cas.ll_setRefValue(addr, casFeatCode_nota, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Interpretador_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_nome = jcas.getRequiredFeatureDE(casType, "nome", "sentimentanalysis.HTMLtoXML.interpretador.DadosXml", featOkTst);
    casFeatCode_nome  = (null == casFeat_nome) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_nome).getCode();

 
    casFeat_data = jcas.getRequiredFeatureDE(casType, "data", "sentimentanalysis.HTMLtoXML.interpretador.DadosXml", featOkTst);
    casFeatCode_data  = (null == casFeat_data) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_data).getCode();

 
    casFeat_autor = jcas.getRequiredFeatureDE(casType, "autor", "sentimentanalysis.HTMLtoXML.interpretador.DadosXml", featOkTst);
    casFeatCode_autor  = (null == casFeat_autor) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_autor).getCode();

 
    casFeat_resenha = jcas.getRequiredFeatureDE(casType, "resenha", "sentimentanalysis.HTMLtoXML.interpretador.DadosXml", featOkTst);
    casFeatCode_resenha  = (null == casFeat_resenha) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_resenha).getCode();

 
    casFeat_nota = jcas.getRequiredFeatureDE(casType, "nota", "sentimentanalysis.HTMLtoXML.interpretador.DadosXml", featOkTst);
    casFeatCode_nota  = (null == casFeat_nota) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_nota).getCode();

  }
}



    