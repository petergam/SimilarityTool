package Include;

import Model.JPConfiguration.IncludeType;
import Model.WordNetManager;
import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import Objects.JPWord.JPWordType;

// TODO: Auto-generated Javadoc
/**
 * The Class JPIncludeHypernyms.
 */
public class JPIncludeHypernyms extends JPAbstractInclude {

	/* (non-Javadoc)
	 * @see Include.JPInclude#include(Objects.JPDocument, Model.JPConfiguration.IncludeType)
	 */
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
					wnManager.findHypernyms(word, layers);
				}
			}
		}

		return document;
	}
}