package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Article {

	private ArrayList<String> wordsArrayList = new ArrayList<String>();
	private HashMap<String, Integer> wordHashMap = new HashMap<String, Integer>();
	private String text;

	public Article() {

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
