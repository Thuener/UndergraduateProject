/**
 * /Dados/UIMA/: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */

/* First created by JCasGen Thu Oct 11 16:13:21 BRT 2007 */
package sentimentanalysis.find;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 16 15:38:35 BRT 2007
 * XML source: C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Dados/UIMA/Descriptor/Find/TypeSystemFind.xml
 * @generated */
public class FindTag extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(FindTag.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected FindTag() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FindTag(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FindTag(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public FindTag(JCas jcas, int begin, int end) {
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
    if (FindTag_Type.featOkTst && ((FindTag_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "sentimentanalysis.find.FindTag");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FindTag_Type)jcasType).casFeatCode_building);}
    
  /** setter for building - sets Building containing this room 
   * @generated */
  public void setBuilding(String v) {
    if (FindTag_Type.featOkTst && ((FindTag_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "sentimentanalysis.find.FindTag");
    jcasType.ll_cas.ll_setStringValue(addr, ((FindTag_Type)jcasType).casFeatCode_building, v);}    
  }

    