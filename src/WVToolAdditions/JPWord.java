package WVToolAdditions;

import java.util.ArrayList;
import java.util.Arrays;

import edu.mit.jwi.item.POS;


public class JPWord {
	
	public static final int SenseIndexUnkown = 0;

	public enum JPWordType {
		JPWordTypeUnknown,
		JPWordTypeNoun,
		JPWordTypeVerb,
		JPWordTypeAdjective,
		JPWordTypeAdverb
	}
	
	private String value;
	private JPWord[] synonyms;
	private JPWord[] hypernyms;
	private JPWord[] hyponyms;

	private JPWordType wordType;
	private int senseIndex;
	
	public JPWord() {
		senseIndex = SenseIndexUnkown;
		wordType = JPWordType.JPWordTypeUnknown;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public JPWord[] getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(JPWord[] synonyms) {
		this.synonyms = synonyms;
	}
	public JPWord[] getHypernyms() {
		return hypernyms;
	}
	public void setHypernyms(JPWord[] hypernyms) {
		this.hypernyms = hypernyms;
	}
	public JPWord[] getHyponyms() {
		return hyponyms;
	}
	public void setHyponyms(JPWord[] hyponyms) {
		this.hyponyms = hyponyms;
	}
	public JPWordType getWordType() {
		return wordType;
	}
	public void setWordType(JPWordType wordType) {
		this.wordType = wordType;
	}
	
	public int getSynsetSize() {
		int size = 1;
		for (JPWord synonymWord : synonyms) {
			size += synonymWord.getSynsetSize();
		}
		
		for (JPWord hypernymWord : hypernyms) {
			size += hypernymWord.getSynsetSize();
		}
		
		return size;
	}
	
	public ArrayList<JPWord> getAllHypernyms() {
		if (hypernyms != null) {
			ArrayList<JPWord> words = new ArrayList<JPWord>(Arrays.asList(hypernyms));
			
			for (JPWord word : hypernyms) {
				ArrayList<JPWord> s = word.getAllHypernyms();
				if (s!=null) {
					words.addAll(s);
				}
			}

			return words;
		}
		
		return null;
	}
	
	public ArrayList<JPWord> getAllHyponyms() {
		if (hyponyms != null) {
			ArrayList<JPWord> words = new ArrayList<JPWord>(Arrays.asList(hyponyms));
			
			for (JPWord word : hyponyms) {
				ArrayList<JPWord> s = word.getAllHyponyms();
				if (s!=null) {
					words.addAll(s);
				}
			}

			return words;
		}
		
		return null;
	}
	
	public ArrayList<JPWord> getAllSynonyms() {
		if (synonyms != null) {
			ArrayList<JPWord> words = new ArrayList<JPWord>(Arrays.asList(synonyms));
			
			for (JPWord word : synonyms) {
				ArrayList<JPWord> s = word.getAllSynonyms();
				if (s!=null) {
					words.addAll(s);
				}
			}
			return words;
		}
		
		return null;
	}

	public int getSenseIndex() {
		return senseIndex;
	}

	public void setSenseIndex(int senseIndex) {
		this.senseIndex = senseIndex;
	}
	
	public POS getPOS() {
		switch (wordType) {
		case JPWordTypeAdjective:
			return POS.ADJECTIVE;
		case JPWordTypeAdverb:
			return POS.ADVERB;
		case JPWordTypeNoun:
			return POS.NOUN;
		case JPWordTypeVerb:
			return POS.VERB;
		case JPWordTypeUnknown:
		default:
            throw new RuntimeException("Unknown POS for word type: " + wordType);
		}
	}	
}
