package POSTagger;

import Model.IllinoisPOSTaggerManager;
import Objects.JPDocument;
import Objects.JPSentence;

/**
 * The Class JPPOSTaggerIllinois.
 * Tags a document using Illinois POS tagger.
 */
public class JPPOSTaggerIllinois extends JPAbstractPOSTagger{

	/* (non-Javadoc)
	 * @see POSTagger.JPPOSTagger#tag(Objects.JPDocument)
	 */
	@Override
	public JPDocument tag(JPDocument document) {
		IllinoisPOSTaggerManager posTagger = IllinoisPOSTaggerManager.SharedInstance;
		for (JPSentence sentence : document.getSentenceArray()) {
			posTagger.tagSentence(sentence);			
		}
		
		return document;
	}

}
