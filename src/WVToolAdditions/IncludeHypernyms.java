package WVToolAdditions;

import Model.WordNetManager;
import WVToolExtension.JPWVTDocumentInfo;
import edu.udo.cs.wvtool.util.TokenEnumeration;
import edu.udo.cs.wvtool.util.WVToolException;

public class IncludeHypernyms extends AbstractInclude {

	@Override
	public TokenEnumeration include(TokenEnumeration source, JPWVTDocumentInfo d)
			throws WVToolException {

		int layers = getLayers();
		WordNetManager wnManager = WordNetManager.SharedInstance;

		for(JPWord word : d.getAllWords()) {
			wnManager.findHypernyms(word, layers);
		}
		
		return source;
	}

}
