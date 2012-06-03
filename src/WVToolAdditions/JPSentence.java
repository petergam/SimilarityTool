package WVToolAdditions;

public class JPSentence {

	private boolean isPOSTagged = false;
	private JPWord[] words;

	public JPWord[] getWords() {
		return words;
	}

	public void setWords(JPWord[] words) {
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
