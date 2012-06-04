package Include;

import Model.WordNetManager;
import Model.JPConfiguration.IncludeType;
import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import Objects.JPWord.JPWordType;

public class JPIncludeHyponyms extends JPAbstractInclude{

	@Override
	public JPDocument include(JPDocument document, IncludeType includeType) {
		int layers = getLayers();
		WordNetManager wnManager = WordNetManager.SharedInstance;

		for (JPSentence sentence : document.getSentenceArray()) {
			for (JPWord word : sentence.getWords()) {
				if (includeType==IncludeType.IncludeTypePOSTagged && word.getWordType()==JPWordType.JPWordTypeUnknown) {
					continue;
				} else if (includeType== IncludeType.IncludeTypeSenseRelated && word.getSenseIndex() == JPWord.SenseIndexUnkown) {
					continue;
				} else {
					wnManager.findHyponyms(word, layers);
				}
			}
		}

		return document;
	}

}
