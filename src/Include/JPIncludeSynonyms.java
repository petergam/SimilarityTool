package Include;

import Model.JPConfiguration.IncludeNeighbourWordsType;
import Model.JPConfiguration.IncludeType;
import Objects.JPDocument;

/**
 * The Class JPIncludeSynonyms.
 */
public class JPIncludeSynonyms extends JPAbstractInclude {

	/* (non-Javadoc)
	 * @see Include.JPInclude#include(Objects.JPDocument, Model.JPConfiguration.IncludeType)
	 */
	@Override
    public JPDocument include(JPDocument document, IncludeType includeType, IncludeNeighbourWordsType includeNeigbbourWordsType) {
//		int layers = getLayers();
//		WordNetManager wnManager = WordNetManager.SharedInstance;
//		for (JPSentence sentence : document.getSentenceArray()) {
//			for (JPWord word : sentence.getWords()) {
//				if (includeType==IncludeType.IncludeTypePOSTagged && word.getWordType()==JPWordType.JPWordTypeUnknown) {
//					continue;
//				} else if (includeType== IncludeType.IncludeTypeSenseRelated && word.getSenseIndex() == JPWord.SenseIndexUnkown) {
//					continue;
//				} else {
//					wnManager.findSynonyms(word, layers);
//				}
//			}
//		}

		return document;
	}
}
