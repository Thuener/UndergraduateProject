

/* First created by JCasGen Thu Aug 09 11:39:53 BRT 2007 */
package sentimentanalysis.HTMLtoXML.interpretador;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Dec 06 13:33:34 GST 2007
 * XML source: C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Codigo/Descriptor/AggregateTAE.xml
 * @generated */
public class DadosXml extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DadosXml.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DadosXml() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DadosXml(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DadosXml(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DadosXml(JCas jcas, int begin, int end) {
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
  //* Feature: building

  /** getter for building - gets Building containing this room
   * @generated */
  public String getBuilding() {
    if (DadosXml_Type.featOkTst && ((DadosXml_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "sentimentanalysis.HTMLtoXML.interpretador.DadosXml");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DadosXml_Type)jcasType).casFeatCode_building);}
    
  /** setter for building - sets Building containing this room 
   * @generated */
  public void setBuilding(String v) {
    if (DadosXml_Type.featOkTst && ((DadosXml_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "sentimentanalysis.HTMLtoXML.interpretador.DadosXml");
    jcasType.ll_cas.ll_setStringValue(addr, ((DadosXml_Type)jcasType).casFeatCode_building, v);}    
  }

    