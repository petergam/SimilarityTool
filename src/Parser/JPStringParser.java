package Parser;

import java.util.ArrayList;
import java.util.HashMap;

import Model.JPCache;
import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import Objects.JPWordIndex;
import Utilities.GUILog;

/**
 * The Class JPStringParser.
 * Parses a string to a document
 */
public class JPStringParser {
	
	/**
	 * Parses the.
	 *
	 * @param string the string
	 * @return the jP document
	 */
	public JPDocument parse(JPDocument document, String string) {
		JPCache cache = new JPCache();
		JPDocument anotherDocument = cache.loadCachedDocument(document.getDocumentTitle());
		if (anotherDocument != null) {
			GUILog.nLog("Using cached " + anotherDocument.getDocumentTitle());
			return anotherDocument;
		}
		
		StringBuilder currentWord = new StringBuilder();
		JPSentence currentSentence = new JPSentence();
		for (char ch: string.toCharArray()) {
			if (Character.isLetter(ch)) {
				currentWord.append(ch);
			} else if (ch == ' ' && currentWord.length()>0) {
				JPWord word = new JPWord();
				word.setValue(currentWord.toString());
				currentSentence.getWords().add(word);

				HashMap<String, ArrayList<JPWordIndex>> wordHashMap = document.getWordHashMap();
				if (wordHashMap.containsKey(currentWord.toString())) {
					JPWordIndex wordIndex = new JPWordIndex();
					wordIndex.setWordIndex(currentSentence.getWords().size()-1);
					wordIndex.setSentenceIndex(document.getSentenceArray().size());
					
					ArrayList<JPWordIndex> indexes = wordHashMap.get(currentWord.toString());
					indexes.add(wordIndex);
				} else {
					ArrayList<JPWordIndex> indexes = new ArrayList<JPWordIndex>();
					JPWordIndex wordIndex = new JPWordIndex();
					wordIndex.setWordIndex(currentSentence.getWords().size()-1);
					wordIndex.setSentenceIndex(document.getSentenceArray().size());
					indexes.add(wordIndex);
					wordHashMap.put(currentWord.toString(), indexes);
				}
				document.setNumberOfWords(document.getNumberOfWords()+1);
				
				currentWord = new StringBuilder();
				
			} else if(ch == '.' || ch == '!' || ch == '?' || ch == ':' || ch == ';' || ch == '(' || ch == ')') {
				
				if (currentWord.length()>0) {
					JPWord word = new JPWord();
					word.setValue(currentWord.toString());
					currentSentence.getWords().add(word);
					currentWord = new StringBuilder();
					
					HashMap<String, ArrayList<JPWordIndex>> wordHashMap = document.getWordHashMap();
					if (wordHashMap.containsKey(currentWord.toString())) {
						JPWordIndex wordIndex = new JPWordIndex();
						wordIndex.setWordIndex(currentSentence.getWords().size()-1);
						wordIndex.setSentenceIndex(document.getSentenceArray().size());
						
						ArrayList<JPWordIndex> indexes = wordHashMap.get(currentWord.toString());
						indexes.add(wordIndex);
					} else {
						ArrayList<JPWordIndex> indexes = new ArrayList<JPWordIndex>();
						JPWordIndex wordIndex = new JPWordIndex();
						wordIndex.setWordIndex(currentSentence.getWords().size()-1);
						wordIndex.setSentenceIndex(document.getSentenceArray().size());
						indexes.add(wordIndex);
						wordHashMap.put(currentWord.toString(), indexes);
					}
				}

				if (currentSentence.getWords().size()>0) {
					document.getSentenceArray().add(currentSentence);
					currentSentence = new JPSentence();
				}
			}
		}
		
		if(currentSentence.getWords().size() > 0) {
			document.getSentenceArray().add(currentSentence);
		}
		
		return document;
	}
}
