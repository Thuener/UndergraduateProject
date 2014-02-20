
/* First created by JCasGen Tue Aug 07 14:02:08 BRT 2007 */
package sentimentanalysis.HTMLtoXML.critica;

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
public class Critica_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Critica_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Critica_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Critica(addr, Critica_Type.this);
  			   Critica_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Critica(addr, Critica_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Critica.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("sentimentanalysis.HTMLtoXML.critica.Critica");
 
  /** @generated */
  final Feature casFeat_startTag;
  /** @generated */
  final int     casFeatCode_startTag;
  /** @generated */ 
  public int getStartTag(int addr) {
        if (featOkTst && casFeat_startTag == null)
      jcas.throwFeatMissing("startTag", "sentimentanalysis.HTMLtoXML.critica.Critica");
    return ll_cas.ll_getRefValue(addr, casFeatCode_startTag);
  }
  /** @generated */    
  public void setStartTag(int addr, int v) {
        if (featOkTst && casFeat_startTag == null)
      jcas.throwFeatMissing("startTag", "sentimentanalysis.HTMLtoXML.critica.Critica");
    ll_cas.ll_setRefValue(addr, casFeatCode_startTag, v);}
    
  
 
  /** @generated */
  final Feature casFeat_endTag;
  /** @generated */
  final int     casFeatCode_endTag;
  /** @generated */ 
  public int getEndTag(int addr) {
        if (featOkTst && casFeat_endTag == null)
      jcas.throwFeatMissing("endTag", "sentimentanalysis.HTMLtoXML.critica.Critica");
    return ll_cas.ll_getRefValue(addr, casFeatCode_endTag);
  }
  /** @generated */    
  public void setEndTag(int addr, int v) {
        if (featOkTst && casFeat_endTag == null)
      jcas.throwFeatMissing("endTag", "sentimentanalysis.HTMLtoXML.critica.Critica");
    ll_cas.ll_setRefValue(addr, casFeatCode_endTag, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Critica_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_startTag = jcas.getRequiredFeatureDE(casType, "startTag", "sentimentanalysis.HTMLtoXML.tags.Tags", featOkTst);
    casFeatCode_startTag  = (null == casFeat_startTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_startTag).getCode();

 
    casFeat_endTag = jcas.getRequiredFeatureDE(casType, "endTag", "sentimentanalysis.HTMLtoXML.tags.Tags", featOkTst);
    casFeatCode_endTag  = (null == casFeat_endTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_endTag).getCode();

  }
}



    