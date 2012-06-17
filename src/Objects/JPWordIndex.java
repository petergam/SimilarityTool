package Objects;

import java.io.Serializable;

public class JPWordIndex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4445119965126267904L;
	private int sentenceIndex;
	private int wordIndex;
	public int getSentenceIndex() {
		return sentenceIndex;
	}
	public void setSentenceIndex(int sentenceIndex) {
		this.sentenceIndex = sentenceIndex;
	}
	public int getWordIndex() {
		return wordIndex;
	}
	public void setWordIndex(int wordIndex) {
		this.wordIndex = wordIndex;
	}
}
