package WVToolExtension;

import java.util.ArrayList;
import java.util.HashMap;

import edu.udo.cs.wvtool.main.WVTDocumentInfo;

public class JPWVTDocumentInfo extends WVTDocumentInfo {
	// ArrayList with all words in document
	private ArrayList<String> wordsArrayList = new ArrayList<String>();
	// Hashmap with all words. Key is the word, value is number of times the word ocures in document
	private HashMap<String, Integer> wordHashMap = new HashMap<String, Integer>();
	// number of words in document
	private int numberOfWords = 0;
	
	public JPWVTDocumentInfo(String arg0, String arg1, String arg2, String arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
	public JPWVTDocumentInfo(String arg0, String arg1, String arg2,
			String arg3, int arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
	}
	
	public ArrayList<String> getWordsArrayList() {
		return wordsArrayList;
	}


	public void setWordsArrayList(ArrayList<String> wordsArrayList) {
		this.wordsArrayList = wordsArrayList;
	}


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




}
