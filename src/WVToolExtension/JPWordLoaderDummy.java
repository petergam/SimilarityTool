package WVToolExtension;

import edu.udo.cs.wvtool.main.WVTDocumentInfo;
import edu.udo.cs.wvtool.util.TokenEnumeration;
import edu.udo.cs.wvtool.util.WVToolException;

public class JPWordLoaderDummy implements JPWordLoader {

	@Override
	public TokenEnumeration load(TokenEnumeration source, JPWVTDocumentInfo d)
			throws WVToolException {

		return source;
	}

}
