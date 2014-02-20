/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */

/* First created by JCasGen Mon Aug 06 14:35:21 BRT 2007 */
package sentimentanalysis.HTMLtoXML.tags;

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
 * Updated by JCasGen Thu Dec 06 14:42:28 GST 2007
 * @generated */
public class Tags_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Tags_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Tags_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Tags(addr, Tags_Type.this);
  			   Tags_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Tags(addr, Tags_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Tags.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("sentimentanalysis.HTMLtoXML.tags.Tags");
 
  /** @generated */
  final Feature casFeat_building;
  /** @generated */
  final int     casFeatCode_building;
  /** @generated */ 
  public String getBuilding(int addr) {
        if (featOkTst && casFeat_building == null)
      jcas.throwFeatMissing("building", "sentimentanalysis.HTMLtoXML.tags.Tags");
    return ll_cas.ll_getStringValue(addr, casFeatCode_building);
  }
  /** @generated */    
  public void setBuilding(int addr, String v) {
        if (featOkTst && casFeat_building == null)
      jcas.throwFeatMissing("building", "sentimentanalysis.HTMLtoXML.tags.Tags");
    ll_cas.ll_setStringValue(addr, casFeatCode_building, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Tags_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_building = jcas.getRequiredFeatureDE(casType, "building", "uima.cas.String", featOkTst);
    casFeatCode_building  = (null == casFeat_building) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_building).getCode();

  }
}



    