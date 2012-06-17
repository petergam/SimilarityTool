package POSTagger;

import Model.StanfordPOSTaggerManager;
import Objects.JPDocument;
import Objects.JPSentence;

/**
 * The Class JPPOSTaggerStanford.
 * Tags a document using Stanford POS tagger
 */
public class JPPOSTaggerStanford extends JPAbstractPOSTagger {

	/* (non-Javadoc)
	 * @see POSTagger.JPPOSTagger#tag(Objects.JPDocument)
	 */
	@Override
	public JPDocument tag(JPDocument document) {

		StanfordPOSTaggerManager posTagger  = StanfordPOSTaggerManager.SharedInstance;
		for (JPSentence sentence : document.getSentenceArray()) {
			posTagger.tagSentence(sentence);			
		}
	
		return document;
	}
}