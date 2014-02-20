/**
 * Autor: Thuener Armando da Silva
 * Projeto: Analise subjetiva de centeúdo textual aplicado ao português
 * Empresa: PUC-Rio
 */

/* First created by JCasGen Thu Oct 11 16:30:41 BRT 2007 */
package sentimentanalysis.find;

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
 * Updated by JCasGen Wed Oct 24 23:22:21 GST 2007
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
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("sentimentanalysis.find.Critica");
 
  /** @generated */
  final Feature casFeat_building;
  /** @generated */
  final int     casFeatCode_building;
  /** @generated */ 
  public String getBuilding(int addr) {
        if (featOkTst && casFeat_building == null)
      jcas.throwFeatMissing("building", "sentimentanalysis.find.Critica");
    return ll_cas.ll_getStringValue(addr, casFeatCode_building);
  }
  /** @generated */    
  public void setBuilding(int addr, String v) {
        if (featOkTst && casFeat_building == null)
      jcas.throwFeatMissing("building", "sentimentanalysis.find.Critica");
    ll_cas.ll_setStringValue(addr, casFeatCode_building, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Critica_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_building = jcas.getRequiredFeatureDE(casType, "building", "uima.cas.String", featOkTst);
    casFeatCode_building  = (null == casFeat_building) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_building).getCode();

  }
}



    