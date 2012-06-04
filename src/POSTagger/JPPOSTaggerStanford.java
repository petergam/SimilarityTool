package POSTagger;

import Model.StanfordPOSTaggerManager;
import Objects.JPDocument;
import Objects.JPSentence;

public class JPPOSTaggerStanford extends JPAbstractPOSTagger {

	@Override
	public JPDocument tag(JPDocument document) {

		StanfordPOSTaggerManager posTagger  = StanfordPOSTaggerManager.SharedInstance;
		for (JPSentence sentence : document.getSentenceArray()) {
			posTagger.tagSentence(sentence);			
		}
		
		return document;
	}
}
