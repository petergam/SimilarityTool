package WVToolExtension;

import edu.udo.cs.wvtool.util.TokenEnumeration;
import edu.udo.cs.wvtool.util.WVToolException;

public class JPWordLoaderCustom implements JPWordLoader {

	@Override
	public TokenEnumeration load(TokenEnumeration tokens, JPWVTDocumentInfo d)
			throws WVToolException {

        while (tokens.hasMoreTokens()) {
        	String value = tokens.nextToken();
        	JPWord word = new JPWord();
        	word.setValue(value);
        	
        	d.getWordsArrayList().add(word);
        	
        	if(d.getWordHashMap().containsKey(word)) {
        		Integer count = d.getWordHashMap().get(word);
        		d.getWordHashMap().put(value, count+1);
        	}else {
        		d.getWordHashMap().put(value, 1);
        	}
        	
        	d.setNumberOfWords(d.getNumberOfWords()+1);
        }

		return tokens;
	}

}
