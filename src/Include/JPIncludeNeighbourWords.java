package Include;

import Model.JPConfiguration.IncludeNeighbourWordsType;
import Model.JPConfiguration.IncludeType;
import Model.NeighbourWordsFactory;
import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;

public class JPIncludeNeighbourWords extends JPAbstractInclude {

	@Override
    public JPDocument include(JPDocument document, IncludeType includeType, IncludeNeighbourWordsType includeNeigbbourWordsType) {
		int layers = getLayers();
		NeighbourWordsFactory factory = new NeighbourWordsFactory();
		
		
		boolean includeSynonyms = false;
		boolean includeHypernymsHyponyms = false;
		
		if (includeNeigbbourWordsType == IncludeNeighbourWordsType.IncludeNeighbourWordsTypeAll) {
			includeSynonyms = true;
			includeHypernymsHyponyms = true;
		} else if (includeNeigbbourWordsType == IncludeNeighbourWordsType.IncludeNeighbourWordsTypeSynonyms) {
			includeSynonyms = true;
			includeHypernymsHyponyms = false;
		} else if (includeNeigbbourWordsType == IncludeNeighbourWordsType.IncludeNeighbourWordsTypeHypoHyperNyms) {
			includeSynonyms = false;
			includeHypernymsHyponyms = true;
		}
		
		for (JPSentence sentence : document.getSentenceArray()) {
			for (JPWord word : sentence.getWords()) {
				factory.findNeighbours(word, layers, includeSynonyms, includeHypernymsHyponyms);
			}
		}
		
		return document;
	}

}
