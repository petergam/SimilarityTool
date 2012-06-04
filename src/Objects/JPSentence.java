package Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class JPSentence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1541610976401476879L;
	private boolean isPOSTagged = false;
	private ArrayList<JPWord> words = new ArrayList<JPWord>();

	public ArrayList<JPWord> getWords() {
		return words;
	}

	public void setWords(ArrayList<JPWord> words) {
		this.words = words;
	}

	public boolean isPOSTagged() {
		return isPOSTagged;
	}

	public void setPOSTagged(boolean isPOSTagged) {
		this.isPOSTagged = isPOSTagged;
	}
	
	public String getSentenceString() {
		StringBuilder sentence = new StringBuilder();
		
		for (JPWord word : getWords()) {
			sentence.append(word.getValue());
			sentence.append(" ");
		}
		
		return sentence.toString();
	}
	
}
