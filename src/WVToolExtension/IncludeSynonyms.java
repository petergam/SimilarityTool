package WVToolExtension;


import org.apache.commons.lang3.ArrayUtils;

import Model.WordNetManager;
import WVToolExtension.JPWord.JPWordType;
import edu.mit.jwi.item.POS;
import edu.udo.cs.wvtool.util.TokenEnumeration;
import edu.udo.cs.wvtool.util.WVToolException;

public class IncludeSynonyms extends AbstractInclude {

	public TokenEnumeration include(TokenEnumeration source, JPWVTDocumentInfo d)
			throws WVToolException {

		WordNetManager wnManager = WordNetManager.SharedInstance;

		for (JPWord word : d.getWordsArrayList()) {
			if (word.getWordType() == JPWordType.JPWordTypeUnknown) {
				// for all types of the word
				JPWord[] synonymsAdjective = wnManager.getSynonyms(word,
						POS.ADJECTIVE);
				JPWord[] synonymsAdverb = wnManager.getSynonyms(word,
						POS.ADVERB);
				JPWord[] synonymsNoun = wnManager.getSynonyms(word, POS.NOUN);
				JPWord[] synonymsVerb = wnManager.getSynonyms(word, POS.VERB);

				JPWord[] synonyms = (JPWord[]) ArrayUtils.addAll(ArrayUtils
						.addAll(ArrayUtils.addAll(synonymsAdjective,
								synonymsAdverb), synonymsNoun), synonymsVerb);
				word.setSynonyms(synonyms);

			} else {
				POS pos = wordNetPosFromWordType(word.getWordType());
				JPWord[] synonyms = wnManager.getSynonyms(word, pos);
				word.setSynonyms(synonyms);
			}
		}

		return source;
	}

	private POS wordNetPosFromWordType(JPWordType type) {
		switch (type) {
		case JPWordTypeAdjective:
			return POS.ADJECTIVE;
		case JPWordTypeAdverb:
			return POS.ADVERB;
		case JPWordTypeNoun:
			return POS.NOUN;
		case JPWordTypeVerb:
			return POS.VERB;
		default:
			throw new RuntimeException("No type");
		}
	}

}
