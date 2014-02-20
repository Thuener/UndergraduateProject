

/* First created by JCasGen Wed Aug 08 16:10:31 BRT 2007 */
package sentimentanalysis.HTMLtoXML.interpretador;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;



/** 
 * Updated by JCasGen Thu Dec 06 13:33:34 GST 2007
 * XML source: C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Codigo/Descriptor/AggregateTAE.xml
 * @generated */
public class Interpretador extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Interpretador.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
/** Never called.  Disable default constructor
   * @generated */
  protected Interpretador() {}
    
/** Internal - constructor used by generator 
   * @generated */
  public Interpretador(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
/** @generated */
  public Interpretador(JCas jcas) {
    super(jcas);
    readObject();   
  } 

/** @generated */  
  public Interpretador(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: nome

  /** getter for nome - gets 
   * @generated */
  public DadosXml getNome() {
    if (Interpretador_Type.featOkTst && ((Interpretador_Type)jcasType).casFeat_nome == null)
      jcasType.jcas.throwFeatMissing("nome", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    return (DadosXml)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Interpretador_Type)jcasType).casFeatCode_nome)));}
    
/** setter for nome - sets  
   * @generated */
  public void setNome(DadosXml v) {
    if (Interpretador_Type.featOkTst && ((Interpretador_Type)jcasType).casFeat_nome == null)
      jcasType.jcas.throwFeatMissing("nome", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    jcasType.ll_cas.ll_setRefValue(addr, ((Interpretador_Type)jcasType).casFeatCode_nome, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
//*--------------*
  //* Feature: data

  /** getter for data - gets 
   * @generated */
  public DadosXml getData() {
    if (Interpretador_Type.featOkTst && ((Interpretador_Type)jcasType).casFeat_data == null)
      jcasType.jcas.throwFeatMissing("data", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    return (DadosXml)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Interpretador_Type)jcasType).casFeatCode_data)));}
    
/** setter for data - sets  
   * @generated */
  public void setData(DadosXml v) {
    if (Interpretador_Type.featOkTst && ((Interpretador_Type)jcasType).casFeat_data == null)
      jcasType.jcas.throwFeatMissing("data", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    jcasType.ll_cas.ll_setRefValue(addr, ((Interpretador_Type)jcasType).casFeatCode_data, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
//*--------------*
  //* Feature: autor

  /** getter for autor - gets 
   * @generated */
  public DadosXml getAutor() {
    if (Interpretador_Type.featOkTst && ((Interpretador_Type)jcasType).casFeat_autor == null)
      jcasType.jcas.throwFeatMissing("autor", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    return (DadosXml)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Interpretador_Type)jcasType).casFeatCode_autor)));}
    
/** setter for autor - sets  
   * @generated */
  public void setAutor(DadosXml v) {
    if (Interpretador_Type.featOkTst && ((Interpretador_Type)jcasType).casFeat_autor == null)
      jcasType.jcas.throwFeatMissing("autor", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    jcasType.ll_cas.ll_setRefValue(addr, ((Interpretador_Type)jcasType).casFeatCode_autor, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
//*--------------*
  //* Feature: resenha

  /** getter for resenha - gets 
   * @generated */
  public DadosXml getResenha() {
    if (Interpretador_Type.featOkTst && ((Interpretador_Type)jcasType).casFeat_resenha == null)
      jcasType.jcas.throwFeatMissing("resenha", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    return (DadosXml)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Interpretador_Type)jcasType).casFeatCode_resenha)));}
    
/** setter for resenha - sets  
   * @generated */
  public void setResenha(DadosXml v) {
    if (Interpretador_Type.featOkTst && ((Interpretador_Type)jcasType).casFeat_resenha == null)
      jcasType.jcas.throwFeatMissing("resenha", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    jcasType.ll_cas.ll_setRefValue(addr, ((Interpretador_Type)jcasType).casFeatCode_resenha, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
//*--------------*
  //* Feature: nota

  /** getter for nota - gets 
   * @generated */
  public DadosXml getNota() {
    if (Interpretador_Type.featOkTst && ((Interpretador_Type)jcasType).casFeat_nota == null)
      jcasType.jcas.throwFeatMissing("nota", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    return (DadosXml)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Interpretador_Type)jcasType).casFeatCode_nota)));}
    
/** setter for nota - sets  
   * @generated */
  public void setNota(DadosXml v) {
    if (Interpretador_Type.featOkTst && ((Interpretador_Type)jcasType).casFeat_nota == null)
      jcasType.jcas.throwFeatMissing("nota", "sentimentanalysis.HTMLtoXML.interpretador.Interpretador");
    jcasType.ll_cas.ll_setRefValue(addr, ((Interpretador_Type)jcasType).casFeatCode_nota, jcasType.ll_cas.ll_getFSRef(v));}    
  }


    