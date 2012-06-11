package Stemmer;

import java.util.HashMap;

import Model.WordNetManager;
import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;

// TODO: Auto-generated Javadoc
/**
 * The Class JPStemmerWordnet.
 */
public class JPStemmerWordnet extends JPAbstractStemmer {

	/** The cache hashmap. */
	HashMap<String, String> cacheHashmap = new HashMap<String, String>();

	/* (non-Javadoc)
	 * @see Stemmer.JPStemmer#stem(Objects.JPDocument)
	 */
	public JPDocument stem(JPDocument document) {
		
		for (JPSentence sentence : document.getSentenceArray()) {
			for (JPWord word : sentence.getWords()) {
				if (cacheHashmap.containsKey(word.getValue())) {
					word.setValue(cacheHashmap.get(word.getValue()));
				} else {
					String oldValue = word.getValue();
					String newValue = WordNetManager.SharedInstance.stemWord(word);
					cacheHashmap.put(oldValue, newValue);
					
					word.setValue(newValue);
				}
			}
		}
		
		return document;
	}

	
}
