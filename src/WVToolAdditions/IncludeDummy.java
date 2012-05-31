package WVToolAdditions;

import WVToolExtension.JPWVTDocumentInfo;
import edu.udo.cs.wvtool.util.TokenEnumeration;
import edu.udo.cs.wvtool.util.WVToolException;

public class IncludeDummy extends AbstractInclude {

	//dummy class. Doesnt really do anything.

    public TokenEnumeration include(TokenEnumeration source, JPWVTDocumentInfo d) throws WVToolException {
    	
    	  
    	return source;
    }
	
}
