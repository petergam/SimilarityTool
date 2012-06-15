package Objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Class JPSentence.
 * Represents a sentece
 */
public class JPSentence implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1541610976401476879L;
	
	/** Indicates if POS tagged. */
	private boolean isPOSTagged = false;
	
	/** The words. */
	private ArrayList<JPWord> words = new ArrayList<JPWord>();

	/**
	 * Gets the words.
	 *
	 * @return the words
	 */
	public ArrayList<JPWord> getWords() {
		return words;
	}

	/**
	 * Sets the words.
	 *
	 * @param words the new words
	 */
	public void setWords(ArrayList<JPWord> words) {
		this.words = words;
	}

	/**
	 * Checks if is POS tagged.
	 *
	 * @return true, if is POS tagged
	 */
	public boolean isPOSTagged() {
		return isPOSTagged;
	}

	/**
	 * Sets if POS tagged.
	 *
	 * @param isPOSTagged the new POS tagged
	 */
	public void setPOSTagged(boolean isPOSTagged) {
		this.isPOSTagged = isPOSTagged;
	}
	
	/**
	 * Gets the sentence string.
	 *
	 * @return the sentence string
	 */
	public String getSentenceString() {
		StringBuilder sentence = new StringBuilder();
		
		for (JPWord word : getWords()) {
			sentence.append(word.getValue());
			sentence.append(" ");
		}
		sentence.deleteCharAt(sentence.length()-1);
		
		return sentence.toString();
	}
	
	/**
	 * Gets the POS tagged sentence string.
	 *
	 * @return the POS tagged sentence string
	 */
	public String getPOSTaggedSentenceString() {
		if (isPOSTagged) {
			StringBuilder sentence = new StringBuilder();
			for (JPWord word : getWords()) {
				sentence.append(word.getValue());
				sentence.append("/");
				sentence.append(word.getTag());
				sentence.append(" ");
			}
			
			sentence.deleteCharAt(sentence.length()-1);
			
			return sentence.toString();
		} else {
			throw new RuntimeException("Sentence not postagged");
		}
	}
	
}
