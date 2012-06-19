package Objects;

import java.io.Serializable;
import java.util.ArrayList;

import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;


// TODO: Auto-generated Javadoc
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
	public enum JPWordPOS {
		
		/** The JP word type unknown. */
		JPWordPOSUnknown,
		
		/** The JP word type noun. */
		JPWordPOSNoun,
		
		/** The JP word type verb. */
		JPWordPOSVerb,
		
		/** The JP word type adjective. */
		JPWordPOSAdjective,
		
		/** The JP word type adverb. */
		JPWorPOSAdverb
	}
	
	public enum JPWordType {
		JPWordTypeMain,
		JPWordTypeSynonym,
		JPWordTypeHyponym,
		JPWordTypeHypernym
	}
	
	/** The value. */
	private String value;
	
	/** The sense tagged value. */
	private String senseValue;
	
	private ArrayList<Object[]> neighbourWord;

	/** The word type. */
	private JPWordPOS wordPOS;
	
	private JPWordType wordType;
	
	/** The POS tag. */
	private String tag;
	
	/** The WordNet sense index. */
	private int senseIndex;
	
	/** Indicates if the word is a stop word. */
	private boolean isStopWord = false;
	
	/** The score of the word. Default is 1.0 */
	private double score = 1.0;

	public IWordID wordNetID;
	
	/**
	 * Instantiates a new jP word.
	 */
	public JPWord() {
		senseIndex = SenseIndexUnkown;
		wordPOS = JPWordPOS.JPWordPOSUnknown;
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
	 * Gets the word type.
	 *
	 * @return the word type
	 */
	public JPWordPOS getWordPOS() {
		return wordPOS;
	}
	
	/**
	 * Sets the word type.
	 *
	 * @param wordType the new word type
	 */
	public void setWordPOS(JPWordPOS wordPOS) {
		this.wordPOS = wordPOS;
	}
	
	public void setWordPOSFromWordNetPOS(POS pos) {
		switch (pos) {
		case ADJECTIVE:
			wordPOS = JPWordPOS.JPWordPOSAdjective;
			break;
		case VERB:
			wordPOS = JPWordPOS.JPWordPOSVerb;
			break;
		case ADVERB:
			wordPOS = JPWordPOS.JPWorPOSAdverb;
			break;
		case NOUN:
			wordPOS = JPWordPOS.JPWordPOSNoun;
			break;
		default:
			break;
		}
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
		switch (wordPOS) {
		case JPWordPOSAdjective:
			return POS.ADJECTIVE;
		case JPWorPOSAdverb:
			return POS.ADVERB;
		case JPWordPOSNoun:
			return POS.NOUN;
		case JPWordPOSVerb:
			return POS.VERB;
		case JPWordPOSUnknown:
		default:
            throw new RuntimeException("Unknown POS for word type: " + wordPOS);
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
	public void setWordPOSFromTag(String tag) {
		if (tag.equals("NN") || tag.equals("NNP") || tag.equals("NNPS") || tag.equals("NNS")) {
			wordPOS = JPWordPOS.JPWordPOSNoun;
		}else if (tag.equals("VB") || tag.equals("VBD") || tag.equals("VBG") || tag.equals("VBN") || tag.equals("VBP") || tag.equals("VBZ")) {
			wordPOS = JPWordPOS.JPWordPOSVerb;
		}else if (tag.equals("JJ") || tag.equals("JJR") || tag.equals("JJS")) {
			wordPOS = JPWordPOS.JPWordPOSAdjective;
		}else if (tag.equals("RB") || tag.equals("RBR") || tag.equals("RBS") || tag.equals("RP")) {
			wordPOS = JPWordPOS.JPWorPOSAdverb;
		} else {
			wordPOS = JPWordPOS.JPWordPOSUnknown;
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

	/**
	 * Returns if the word is a stop word.
	 *
	 * @return returns true if the word is a stop word.
	 */
	public boolean isStopWord() {
		return isStopWord;
	}

	/**
	 * Sets if the word is a stop word.
	 *
	 * @param isStopWord the new stop word
	 */
	public void setStopWord(boolean isStopWord) {
		this.isStopWord = isStopWord;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(double score) {
		this.score = score;
	}

	public JPWordType getWordType() {
		return wordType;
	}

	public void setWordType(JPWordType wordType) {
		this.wordType = wordType;
	}

	public ArrayList<Object[]> getNeighbourWord() {
		return neighbourWord;
	}

	public void setNeighbourWord(ArrayList<Object[]> neighbourWordIDs) {
		this.neighbourWord = neighbourWordIDs;
	}

}
