package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class JPDocument implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2782176268756664285L;

	private ArrayList<JPSentence> sentenceArray = new ArrayList<JPSentence>();

	private HashMap<String, Integer> wordHashMap = new HashMap<String, Integer>();
	// number of words in document
	private int numberOfWords = 0;
	
	private double score = 0.0;
	
	private JPDocumentProgressType progressType = JPDocumentProgressType.JPDocumentProgressTypeNotLoaded;;

	private String documentTitle;
	
	private boolean isSenseTagged = false;
	
	public enum JPDocumentProgressType {
		JPDocumentProgressTypeNotLoaded,
		JPDocumentProgressTypeWaiting,
		JPDocumentProgressTypeLoading,
		JPDocumentProgressTypeLoaded,
		JPDocumentProgressTypeComputing,
		JPDocumentProgressTypeComputed
	}
	
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
	
	public ArrayList<JPSentence> getSentenceArray() {
		return sentenceArray;
	}

	public void setSentenceArray(ArrayList<JPSentence> sentenceArray) {
		this.sentenceArray = sentenceArray;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getNumberOfWords() {
		return numberOfWords;
	}

	public void setNumberOfWords(int numberOfWords) {
		this.numberOfWords = numberOfWords;
	}

	public HashMap<String, Integer> getWordHashMap() {
		return wordHashMap;
	}

	public void setWordHashMap(HashMap<String, Integer> wordHashMap) {
		this.wordHashMap = wordHashMap;
	}

	public JPDocumentProgressType getProgressType() {
		return progressType;
	}

	public void setProgressType(JPDocumentProgressType progressType) {
		this.progressType = progressType;
	}

	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	public boolean isSenseTagged() {
		return isSenseTagged;
	}

	public void setSenseTagged(boolean isSenseTagged) {
		this.isSenseTagged = isSenseTagged;
	}

}
