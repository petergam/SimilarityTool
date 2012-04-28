package WVToolExtension;

import java.io.StringReader;

import Model.WordNetManager;
import edu.mit.jwi.item.POS;
import edu.udo.cs.wvtool.generic.tokenizer.SimpleTokenizer;
import edu.udo.cs.wvtool.generic.wordfilter.AbstractStopWordFilter;
import edu.udo.cs.wvtool.main.WVTDocumentInfo;
import edu.udo.cs.wvtool.util.TokenEnumeration;
import edu.udo.cs.wvtool.util.WVToolException;

public class JPWVTSynonymFilter extends AbstractStopWordFilter {

	@Override
	public TokenEnumeration filter(TokenEnumeration token, WVTDocumentInfo document)
			throws WVToolException {
		
		StringBuffer stringBuffer = new StringBuffer();
		
		while(token.hasMoreTokens()) {
			String currentToken = token.nextToken();
			
			String synonymsNoun = WordNetManager.SharedInstance.getSynonyms(currentToken, POS.NOUN);
			String synonymsVerb = WordNetManager.SharedInstance.getSynonyms(currentToken, POS.VERB);
			String synonymsAdverb = WordNetManager.SharedInstance.getSynonyms(currentToken, POS.ADVERB);
			String synonymsAdjective = WordNetManager.SharedInstance.getSynonyms(currentToken, POS.ADJECTIVE);

			stringBuffer.append(currentToken);
			stringBuffer.append(" ");
			stringBuffer.append(synonymsNoun);
			stringBuffer.append(synonymsVerb);
			stringBuffer.append(synonymsAdverb);
			stringBuffer.append(synonymsAdjective);
		}
		
		SimpleTokenizer newToken = new SimpleTokenizer();
		newToken.tokenize(new StringReader(stringBuffer.toString()), document);
		
		return newToken;
	}

	@Override
	protected boolean isStopword(String arg0) {
		return false;
	}

}
