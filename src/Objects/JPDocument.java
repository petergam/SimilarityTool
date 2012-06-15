package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class JPDocument.
 * Represents a document
 */
public class JPDocument implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2782176268756664285L;

	/** The sentence array. */
	private ArrayList<JPSentence> sentenceArray = new ArrayList<JPSentence>();

	/** The word hash map. */
	private HashMap<String, Integer> wordHashMap = new HashMap<String, Integer>();
	// number of words in document
	/** The number of words. */
	private int numberOfWords = 0;
	
	/** The score of algorithm calculations. */
	private double score = 0.0;
	
	/** The progress type. */
	private JPDocumentProgressType progressType = JPDocumentProgressType.JPDocumentProgressTypeNotLoaded;;

	/** The document title. */
	private String documentTitle;
	
	/** Indicates if the document is sense tagged. */
	private boolean isSenseTagged = false;
	
	/**
	 * The Enum JPDocumentProgressType.
	 */
	public enum JPDocumentProgressType {
		
		/** The JP document progress type not loaded. */
		JPDocumentProgressTypeNotLoaded,
		
		/** The JP document progress type waiting. */
		JPDocumentProgressTypeWaiting,
		
		/** The JP document progress type loading. */
		JPDocumentProgressTypeLoading,
		
		/** The JP document progress type loaded. */
		JPDocumentProgressTypeLoaded,
		
		/** The JP document progress type computing. */
		JPDocumentProgressTypeComputing,
		
		/** The JP document progress type computed. */
		JPDocumentProgressTypeComputed
	}
	
	/**
	 * Return all words in the document
	 *
	 * @return all the words
	 */
	public ArrayList<JPWord> getAllWords() {
		ArrayList<JPWord> words = new ArrayList<JPWord>();
		
		
		for (JPSentence sentence : sentenceArray) {
			for (JPWord word : sentence.getWords()) {				
				words.add(word);

				ArrayList<JPWord> synonyms = word.getAllSynonyms();
				if (synonyms!=null) {
					words.addAll(synonyms);
				}
				
				ArrayList<JPWord> hypernyms = word.getAllHypernyms();
				if (hypernyms!=null) {
					words.addAll(hypernyms);
				}	
				
				ArrayList<JPWord> hyponyms = word.getAllHyponyms();
				if (hyponyms!=null) {
					words.addAll(hyponyms);
				}	
			}
		}
						
		return words;
	}
	
	/**
	 * Gets the sentence array.
	 *
	 * @return the sentence array
	 */
	public ArrayList<JPSentence> getSentenceArray() {
		return sentenceArray;
	}

	/**
	 * Sets the sentence array.
	 *
	 * @param sentenceArray the new sentence array
	 */
	public void setSentenceArray(ArrayList<JPSentence> sentenceArray) {
		this.sentenceArray = sentenceArray;
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

	/**
	 * Gets the number of words.
	 *
	 * @return the number of words
	 */
	public int getNumberOfWords() {
		return numberOfWords;
	}

	/**
	 * Sets the number of words.
	 *
	 * @param numberOfWords the new number of words
	 */
	public void setNumberOfWords(int numberOfWords) {
		this.numberOfWords = numberOfWords;
	}

	/**
	 * Gets the word hash map.
	 *
	 * @return the word hash map
	 */
	public HashMap<String, Integer> getWordHashMap() {
		return wordHashMap;
	}

	/**
	 * Sets the word hash map.
	 *
	 * @param wordHashMap the word hash map
	 */
	public void setWordHashMap(HashMap<String, Integer> wordHashMap) {
		this.wordHashMap = wordHashMap;
	}

	/**
	 * Gets the progress type.
	 *
	 * @return the progress type
	 */
	public JPDocumentProgressType getProgressType() {
		return progressType;
	}

	/**
	 * Sets the progress type.
	 *
	 * @param progressType the new progress type
	 */
	public void setProgressType(JPDocumentProgressType progressType) {
		this.progressType = progressType;
	}

	/**
	 * Gets the document title.
	 *
	 * @return the document title
	 */
	public String getDocumentTitle() {
		return documentTitle;
	}

	/**
	 * Sets the document title.
	 *
	 * @param documentTitle the new document title
	 */
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	/**
	 * Checks if is sense tagged.
	 *
	 * @return true, if is sense tagged
	 */
	public boolean isSenseTagged() {
		return isSenseTagged;
	}

	/**
	 * Sets the sense tagged.
	 *
	 * @param isSenseTagged the new sense tagged
	 */
	public void setSenseTagged(boolean isSenseTagged) {
		this.isSenseTagged = isSenseTagged;
	}

}
