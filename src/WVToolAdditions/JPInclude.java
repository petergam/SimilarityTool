package WVToolAdditions;

import WVToolExtension.JPWVTDocumentInfo;
import edu.udo.cs.wvtool.util.TokenEnumeration;
import edu.udo.cs.wvtool.util.WVToolException;

public interface JPInclude {

    public TokenEnumeration include(TokenEnumeration source, JPWVTDocumentInfo d) throws WVToolException;
	
}
