

/* First created by JCasGen Wed Aug 08 15:14:57 BRT 2007 */
package sentimentanalysis.HTMLtoXML.critica;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Dec 06 13:33:33 GST 2007
 * XML source: C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Codigo/Descriptor/AggregateTAE.xml
 * @generated */
public class TagsHtml extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TagsHtml.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TagsHtml() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TagsHtml(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TagsHtml(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TagsHtml(JCas jcas, int begin, int end) {
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
    if (TagsHtml_Type.featOkTst && ((TagsHtml_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "sentimentanalysis.HTMLtoXML.critica.TagsHtml");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TagsHtml_Type)jcasType).casFeatCode_building);}
    
  /** setter for building - sets Building containing this room 
   * @generated */
  public void setBuilding(String v) {
    if (TagsHtml_Type.featOkTst && ((TagsHtml_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "sentimentanalysis.HTMLtoXML.critica.TagsHtml");
    jcasType.ll_cas.ll_setStringValue(addr, ((TagsHtml_Type)jcasType).casFeatCode_building, v);}    
  }

    