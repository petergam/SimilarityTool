package WVToolExtension;

import WVToolAdditions.IncludeDummy;
import WVToolAdditions.JPWordLoaderDummy;
import edu.udo.cs.wvtool.config.WVTConfiguration;
import edu.udo.cs.wvtool.config.WVTConfigurationFact;

public class JPWVTConfiguration extends WVTConfiguration {
    public static final String STEP_INCLUDE = "include";
    public static final String STEP_WORDLOADER = "wordloader";

    public JPWVTConfiguration() {
    	super();
    	
    	setConfigurationRule(STEP_INCLUDE, new WVTConfigurationFact(new IncludeDummy()));
    	setConfigurationRule(STEP_WORDLOADER, new WVTConfigurationFact(new JPWordLoaderDummy()));
    }
    
    public JPWVTConfiguration copy() {
    	
    	JPWVTConfiguration newConfig = new JPWVTConfiguration();
    	
    	Object object = getComponentForStep(STEP_INCLUDE, null);
    	System.out.println(object);
    	if (object!=null) { newConfig.setConfigurationRule(STEP_INCLUDE, new WVTConfigurationFact(object)); }
    	
    	object = getComponentForStep(STEP_INCLUDE, null);
    	System.out.println(object);
    	if (object!=null) { newConfig.setConfigurationRule(STEP_WORDLOADER,   new WVTConfigurationFact(object)); }
    	
    	object = getComponentForStep(STEP_INPUT_FILTER, null);
    	System.out.println(object);
    	if (object!=null) { newConfig.setConfigurationRule(STEP_INPUT_FILTER,   new WVTConfigurationFact(object)); }
    	
    	object = getComponentForStep(STEP_CHAR_MAPPER, null);
    	System.out.println(object);
    	if (object!=null) { newConfig.setConfigurationRule(STEP_CHAR_MAPPER,   new WVTConfigurationFact(object)); }
    	
    	object = getComponentForStep(STEP_LOADER, null);
    	System.out.println(object);
    	if (object!=null) { newConfig.setConfigurationRule(STEP_LOADER,   new WVTConfigurationFact(object)); }
    	
    	object = getComponentForStep(STEP_TOKENIZER, null);
    	System.out.println(object);
    	if (object!=null) { newConfig.setConfigurationRule(STEP_TOKENIZER,   new WVTConfigurationFact(object)); }
    	
    	object = getComponentForStep(STEP_WORDFILTER, null);
    	System.out.println(object);
    	if (object!=null) { newConfig.setConfigurationRule(STEP_WORDFILTER,   new WVTConfigurationFact(object)); }
    	
    	object = getComponentForStep(STEP_STEMMER, null);
    	System.out.println(object);
    	if (object!=null) { newConfig.setConfigurationRule(STEP_STEMMER,   new WVTConfigurationFact(object)); }
    	
    	object = getComponentForStep(STEP_VECTOR_CREATION, null);
    	System.out.println(object);
    	if (object!=null) { newConfig.setConfigurationRule(STEP_VECTOR_CREATION,   new WVTConfigurationFact(object)); }
    	
//    	object = getComponentForStep(STEP_OUTPUT, null);
//    	System.out.println(object);
//    	if (object!=null) { newConfig.setConfigurationRule(STEP_OUTPUT,   new WVTConfigurationFact(object)); }

    	return newConfig;
    }
    
}