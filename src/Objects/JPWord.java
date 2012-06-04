package Objects;

import java.io.Serializable;
import java.util.ArrayList;

import edu.mit.jwi.item.POS;


public class JPWord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -647218912772388790L;
	public static final int SenseIndexUnkown = 0;

	public enum JPWordType {
		JPWordTypeUnknown,
		JPWordTypeNoun,
		JPWordTypeVerb,
		JPWordTypeAdjective,
		JPWordTypeAdverb
	}
	
	private String value;
	private String senseValue;
	private ArrayList<JPWord> synonyms;
	private ArrayList<JPWord> hypernyms;
	private ArrayList<JPWord> hyponyms;

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
	public ArrayList<JPWord> getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(ArrayList<JPWord> synonyms) {
		this.synonyms = synonyms;
	}
	public ArrayList<JPWord> getHypernyms() {
		return hypernyms;
	}
	public void setHypernyms(ArrayList<JPWord> hypernyms) {
		this.hypernyms = hypernyms;
	}
	public ArrayList<JPWord> getHyponyms() {
		return hyponyms;
	}
	public void setHyponyms(ArrayList<JPWord> hyponyms) {
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
			ArrayList<JPWord> words = hypernyms;
			
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
			ArrayList<JPWord> words = hyponyms;
			
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
			ArrayList<JPWord> words = synonyms;
			
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

	public String getSenseValue() {
		return senseValue;
	}

	public void setSenseValue(String senseValue) {
		this.senseValue = senseValue;
	}

	public void setWordTypeFromTag(String tag) {
		if (tag.equals("NN") || tag.equals("NNP") || tag.equals("NNPS") || tag.equals("NNS")) {
			wordType = JPWordType.JPWordTypeNoun;
		}else if (tag.equals("VB") || tag.equals("VBD") || tag.equals("VBG") || tag.equals("VBN") || tag.equals("VBP") || tag.equals("VBZ")) {
			wordType = JPWordType.JPWordTypeVerb;
		}else if (tag.equals("JJ") || tag.equals("JJR") || tag.equals("JJS")) {
			wordType = JPWordType.JPWordTypeAdjective;
		}else if (tag.equals("RB") || tag.equals("RBR") || tag.equals("RBS") || tag.equals("RP")) {
			wordType = JPWordType.JPWordTypeVerb;
		} else {
			wordType = JPWordType.JPWordTypeUnknown;
		}
	}
}
