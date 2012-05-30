package WVToolExtension;

import edu.udo.cs.wvtool.util.TokenEnumeration;
import edu.udo.cs.wvtool.util.WVToolException;

public interface JPWordLoader {

    public TokenEnumeration load(TokenEnumeration source, JPWVTDocumentInfo d) throws WVToolException;
	
}
