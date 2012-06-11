package Objects;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class JPSentence.
 */
public class JPSentence implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1541610976401476879L;
	
	/** The is pos tagged. */
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
	 * Checks if is pOS tagged.
	 *
	 * @return true, if is pOS tagged
	 */
	public boolean isPOSTagged() {
		return isPOSTagged;
	}

	/**
	 * Sets the pOS tagged.
	 *
	 * @param isPOSTagged the new pOS tagged
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
	 * Gets the pOS tagged sentece string.
	 *
	 * @return the pOS tagged sentece string
	 */
	public String getPOSTaggedSenteceString() {
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
