package Objects;

import java.io.Serializable;
import java.util.ArrayList;

import edu.mit.jwi.item.POS;


/**
 * The Class JPWord.
 * Represents a word
 */
public class JPWord implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -647218912772388790L;
	
	/** The Constant SenseIndexUnkown. */
	public static final int SenseIndexUnkown = 0;

	/**
	 * The Enum JPWordType.
	 */
	public enum JPWordType {
		
		/** The JP word type unknown. */
		JPWordTypeUnknown,
		
		/** The JP word type noun. */
		JPWordTypeNoun,
		
		/** The JP word type verb. */
		JPWordTypeVerb,
		
		/** The JP word type adjective. */
		JPWordTypeAdjective,
		
		/** The JP word type adverb. */
		JPWordTypeAdverb
	}
	
	/** The value. */
	private String value;
	
	/** The sense tagged value. */
	private String senseValue;
	
	/** The synonyms. */
	private ArrayList<JPWord> synonyms;
	
	/** The hypernyms. */
	private ArrayList<JPWord> hypernyms;
	
	/** The hyponyms. */
	private ArrayList<JPWord> hyponyms;

	/** The word type. */
	private JPWordType wordType;
	
	/** The POS tag. */
	private String tag;
	
	/** The WordNet sense index. */
	private int senseIndex;
	
	/**
	 * Instantiates a new jP word.
	 */
	public JPWord() {
		senseIndex = SenseIndexUnkown;
		wordType = JPWordType.JPWordTypeUnknown;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Gets the synonyms.
	 *
	 * @return the synonyms
	 */
	public ArrayList<JPWord> getSynonyms() {
		return synonyms;
	}
	
	/**
	 * Sets the synonyms.
	 *
	 * @param synonyms the new synonyms
	 */
	public void setSynonyms(ArrayList<JPWord> synonyms) {
		this.synonyms = synonyms;
	}
	
	/**
	 * Gets the hypernyms.
	 *
	 * @return the hypernyms
	 */
	public ArrayList<JPWord> getHypernyms() {
		return hypernyms;
	}
	
	/**
	 * Sets the hypernyms.
	 *
	 * @param hypernyms the new hypernyms
	 */
	public void setHypernyms(ArrayList<JPWord> hypernyms) {
		this.hypernyms = hypernyms;
	}
	
	/**
	 * Gets the hyponyms.
	 *
	 * @return the hyponyms
	 */
	public ArrayList<JPWord> getHyponyms() {
		return hyponyms;
	}
	
	/**
	 * Sets the hyponyms.
	 *
	 * @param hyponyms the new hyponyms
	 */
	public void setHyponyms(ArrayList<JPWord> hyponyms) {
		this.hyponyms = hyponyms;
	}
	
	/**
	 * Gets the word type.
	 *
	 * @return the word type
	 */
	public JPWordType getWordType() {
		return wordType;
	}
	
	/**
	 * Sets the word type.
	 *
	 * @param wordType the new word type
	 */
	public void setWordType(JPWordType wordType) {
		this.wordType = wordType;
	}
	
	/**
	 * Gets the synset size.
	 *
	 * @return the synset size
	 */
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
	
	/**
	 * Gets the all hypernyms.
	 *
	 * @return the all hypernyms
	 */
	public ArrayList<JPWord> getAllHypernyms() {
		if (hypernyms != null) {
			ArrayList<JPWord> words = (ArrayList<JPWord>) hypernyms.clone();
			
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
	
	/**
	 * Gets the all hyponyms.
	 *
	 * @return the all hyponyms
	 */
	public ArrayList<JPWord> getAllHyponyms() {
		if (hyponyms != null) {
			ArrayList<JPWord> words = (ArrayList<JPWord>) hyponyms.clone();
			
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
	
	/**
	 * Gets the all synonyms.
	 *
	 * @return the all synonyms
	 */
	public ArrayList<JPWord> getAllSynonyms() {
		if (synonyms != null) {
			ArrayList<JPWord> words = (ArrayList<JPWord>) synonyms.clone();
			
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

	/**
	 * Gets the sense index.
	 *
	 * @return the sense index
	 */
	public int getSenseIndex() {
		return senseIndex;
	}

	/**
	 * Sets the sense index.
	 *
	 * @param senseIndex the new sense index
	 */
	public void setSenseIndex(int senseIndex) {
		this.senseIndex = senseIndex;
	}
	
	/**
	 * Gets the POS.
	 *
	 * @return the POS
	 */
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

	/**
	 * Gets the sense value.
	 *
	 * @return the sense value
	 */
	public String getSenseValue() {
		return senseValue;
	}

	/**
	 * Sets the sense value.
	 *
	 * @param senseValue the new sense value
	 */
	public void setSenseValue(String senseValue) {
		this.senseValue = senseValue;
	}

	/**
	 * Sets the word type from tag.
	 *
	 * @param tag the new word type from tag
	 */
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

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag.
	 *
	 * @param tag the new tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
}
