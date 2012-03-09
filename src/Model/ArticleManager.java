package Model;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ArticleManager {

	public Article getArticleFromFile(File file) {

		try {
			
			Article article = POSTaggerManager.SharedInstance.tagFile(file);

			// Load file into string
			int len;
			char[] chr = new char[4096];
			final StringBuffer buffer = new StringBuffer();
			final FileReader reader = new FileReader(file);
			try {
				while ((len = reader.read(chr)) > 0) {
					buffer.append(chr, 0, len);
				}
			} finally {
				reader.close();
			}

			String text = buffer.toString();

			// remove symbols
			text = this.removeSymbolsFromString(text);

			ArrayList<String> wordsArray = this.getArrayListFromString(text);
			HashMap<String, Integer> wordsHashMap = this
					.getHashMapFromArrayList(wordsArray);

			article.setWordsArrayList(wordsArray);
			article.setWordHashMap(wordsHashMap);
			article.setText(text);
	
			return article;

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		
		
		return null;
	}

	public String removeSymbolsFromString(String string) {
		return string.replaceAll("[^A-Za-z0-9 ]", "");
	}

	public ArrayList<String> getArrayListFromString(String string) {
		return new ArrayList<String>(Arrays.asList(string.split(" ")));
	}

	public HashMap<String, Integer> getHashMapFromArrayList(
			ArrayList<String> arrayList) {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

		for (String string : arrayList) {
			if (hashMap.containsKey(string)) {
				hashMap.put(string, hashMap.get(string) + 1);
			} else {
				hashMap.put(string, 1);
			}
		}

		return hashMap;
	}
	
	private HashMap<String, String> stopWords = new HashMap<String, String>();
	
	private boolean isStopWord(String word) {
		
		
		return false;
	}

}
