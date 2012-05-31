package WVToolExtension;

import java.util.ArrayList;
import java.util.HashMap;

import WVToolAdditions.JPSentence;
import WVToolAdditions.JPWord;

import edu.udo.cs.wvtool.main.WVTDocumentInfo;

public class JPWVTDocumentInfo extends WVTDocumentInfo {
	private String documentTitle;

	private ArrayList<Integer> sentenceIndexArray = new ArrayList<Integer>();	
	private ArrayList<JPSentence> sentenceArray = new ArrayList<JPSentence>();

	// ArrayList with all words in document
//	private ArrayList<JPWord> wordsArrayList = new ArrayList<JPWord>();
	// Hashmap with all words. Key is the word, value is number of times the word ocures in document
	private HashMap<String, Integer> wordHashMap = new HashMap<String, Integer>();
	// number of words in document
	private int numberOfWords = 0;
	
	private double score = 0.0;

	public JPWVTDocumentInfo(String arg0, String arg1, String arg2, String arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
	public JPWVTDocumentInfo(String arg0, String arg1, String arg2,
			String arg3, int arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
	}
	
//	public ArrayList<JPWord> getWordsArrayList() {
//		return wordsArrayList;
//	}
//
//
//	public void setWordsArrayList(ArrayList<JPWord> wordsArrayList) {
//		this.wordsArrayList = wordsArrayList;
//	}


	public HashMap<String, Integer> getWordHashMap() {
		return wordHashMap;
	}


	public void setWordHashMap(HashMap<String, Integer> wordHashMap) {
		this.wordHashMap = wordHashMap;
	}

	
	public int getNumberOfWords() {
		return numberOfWords;
	}


	public void setNumberOfWords(int numberOfWords) {
		this.numberOfWords = numberOfWords;
	}

	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public ArrayList<JPWord> getAllWords() {
		ArrayList<JPWord> words = new ArrayList<JPWord>();
		
		
		for (JPSentence sentence : sentenceArray) {
			for (JPWord word : sentence.getWords()) {				
				words.add(word);
//				System.out.println(word);
//				ArrayList<JPWord> synonyms = word.getAllSynonyms();
//				if (synonyms!=null) {
//					words.addAll(synonyms);
//				}
//				
//				ArrayList<JPWord> hypernyms = word.getAllHypernyms();
//				if (hypernyms!=null) {
//					words.addAll(hypernyms);
//				}	
			}
		}
				
		return words;
	}

	public ArrayList<Integer> getSentenceIndexArray() {
		return sentenceIndexArray;
	}

	public void setSentenceIndexArray(ArrayList<Integer> sentenceIndexArray) {
		this.sentenceIndexArray = sentenceIndexArray;
	}

	public ArrayList<JPSentence> getSentenceArray() {
		return sentenceArray;
	}

	public void setSentenceArray(ArrayList<JPSentence> sentenceArray) {
		this.sentenceArray = sentenceArray;
	}
}
