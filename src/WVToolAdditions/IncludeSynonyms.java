package WVToolAdditions;

import Model.WordNetManager;
import WVToolExtension.JPWVTDocumentInfo;
import edu.udo.cs.wvtool.util.TokenEnumeration;
import edu.udo.cs.wvtool.util.WVToolException;

public class IncludeSynonyms extends AbstractInclude {

	public TokenEnumeration include(TokenEnumeration source, JPWVTDocumentInfo d)
			throws WVToolException {

		WordNetManager wnManager = WordNetManager.SharedInstance;

		for (JPSentence sentence : d.getSentenceArray()) {
			for (JPWord word : sentence.getWords()) {				
				wnManager.findSynonyms(word, 0);
			}	
		}
		
		return source;
	}
}
