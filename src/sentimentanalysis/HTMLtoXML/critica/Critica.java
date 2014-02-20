

/* First created by JCasGen Tue Aug 07 14:02:08 BRT 2007 */
package sentimentanalysis.HTMLtoXML.critica;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;

import sentimentanalysis.HTMLtoXML.tags.Tags;


/** 
 * Updated by JCasGen Thu Dec 06 13:33:34 GST 2007
 * XML source: C:/Documents and Settings/thuener/Desktop/Trabalhando/ProjetoFinal/Codigo/Descriptor/AggregateTAE.xml
 * @generated */
public class Critica extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Critica.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Critica() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Critica(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Critica(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Critica(JCas jcas, int begin, int end) {
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
  //* Feature: startTag

  /** getter for startTag - gets 
   * @generated */
  public Tags getStartTag() {
    if (Critica_Type.featOkTst && ((Critica_Type)jcasType).casFeat_startTag == null)
      jcasType.jcas.throwFeatMissing("startTag", "sentimentanalysis.HTMLtoXML.critica.Critica");
    return (Tags)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Critica_Type)jcasType).casFeatCode_startTag)));}
    
  /** setter for startTag - sets  
   * @generated */
  public void setStartTag(Tags v) {
    if (Critica_Type.featOkTst && ((Critica_Type)jcasType).casFeat_startTag == null)
      jcasType.jcas.throwFeatMissing("startTag", "sentimentanalysis.HTMLtoXML.critica.Critica");
    jcasType.ll_cas.ll_setRefValue(addr, ((Critica_Type)jcasType).casFeatCode_startTag, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: endTag

  /** getter for endTag - gets 
   * @generated */
  public Tags getEndTag() {
    if (Critica_Type.featOkTst && ((Critica_Type)jcasType).casFeat_endTag == null)
      jcasType.jcas.throwFeatMissing("endTag", "sentimentanalysis.HTMLtoXML.critica.Critica");
    return (Tags)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Critica_Type)jcasType).casFeatCode_endTag)));}
    
  /** setter for endTag - sets  
   * @generated */
  public void setEndTag(Tags v) {
    if (Critica_Type.featOkTst && ((Critica_Type)jcasType).casFeat_endTag == null)
      jcasType.jcas.throwFeatMissing("endTag", "sentimentanalysis.HTMLtoXML.critica.Critica");
    jcasType.ll_cas.ll_setRefValue(addr, ((Critica_Type)jcasType).casFeatCode_endTag, jcasType.ll_cas.ll_getFSRef(v));}    
                /** Custom constructor taking all parameters */
  public Critica(JCas jcas, int begin, int end,Tags startTag,Tags endTag) {
	  	this(jcas,begin,end);
	    setStartTag(startTag);
	    setEndTag(endTag);
	    
	  } 
  }

    