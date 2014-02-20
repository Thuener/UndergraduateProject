/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */

/* First created by JCasGen Tue Oct 16 15:38:35 BRT 2007 */
package sentimentanalysis.find;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Oct 24 23:22:21 GST 2007
 * XML source: C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Descriptor/Find/FindAnnotator.xml
 * @generated */
public class TagsXml extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TagsXml.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TagsXml() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TagsXml(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TagsXml(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TagsXml(JCas jcas, int begin, int end) {
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
    if (TagsXml_Type.featOkTst && ((TagsXml_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "sentimentanalysis.find.TagsXml");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TagsXml_Type)jcasType).casFeatCode_building);}
    
  /** setter for building - sets Building containing this room 
   * @generated */
  public void setBuilding(String v) {
    if (TagsXml_Type.featOkTst && ((TagsXml_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "sentimentanalysis.find.TagsXml");
    jcasType.ll_cas.ll_setStringValue(addr, ((TagsXml_Type)jcasType).casFeatCode_building, v);}    
  }

    