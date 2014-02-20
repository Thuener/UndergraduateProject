/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */

/* First created by JCasGen Mon Aug 06 14:35:21 BRT 2007 */
package sentimentanalysis.HTMLtoXML.tags;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Dec 06 14:42:28 GST 2007
 * XML source: C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Codigo/Descriptor/Tags/TagsAnnotator.xml
 * @generated */
public class Tags extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Tags.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Tags() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Tags(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Tags(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Tags(JCas jcas, int begin, int end) {
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
    if (Tags_Type.featOkTst && ((Tags_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "sentimentanalysis.HTMLtoXML.tags.Tags");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Tags_Type)jcasType).casFeatCode_building);}
    
  /** setter for building - sets Building containing this room 
   * @generated */
  public void setBuilding(String v) {
    if (Tags_Type.featOkTst && ((Tags_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "sentimentanalysis.HTMLtoXML.tags.Tags");
    jcasType.ll_cas.ll_setStringValue(addr, ((Tags_Type)jcasType).casFeatCode_building, v);}    
  }

    