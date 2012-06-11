package Parser;

import java.util.HashMap;

import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;

// TODO: Auto-generated Javadoc
/**
 * The Class JPStringParser.
 */
public class JPStringParser {
	
	/**
	 * Parses the.
	 *
	 * @param string the string
	 * @return the jP document
	 */
	public JPDocument parse(String string) {
		
		JPDocument document = new JPDocument();
		
		StringBuilder currentWord = new StringBuilder();
		JPSentence currentSentence = new JPSentence();
		for (char ch: string.toCharArray()) {
			if (Character.isLetter(ch)) {
				currentWord.append(ch);
			} else if (ch == ' ' && currentWord.length()>0) {
				JPWord word = new JPWord();
				word.setValue(currentWord.toString());
				currentSentence.getWords().add(word);

				HashMap<String, Integer> wordHashMap = document.getWordHashMap();
				if (wordHashMap.containsKey(currentWord.toString())) {
					wordHashMap.put(currentWord.toString(), wordHashMap.get(currentWord.toString())+1);
				} else {
					wordHashMap.put(currentWord.toString(), 1);
				}
				document.setNumberOfWords(document.getNumberOfWords()+1);
				
				currentWord = new StringBuilder();
				
			} else if(ch == '.' || ch == '!' || ch == '?' || ch == ':' || ch == ';' || ch == '(' || ch == ')') {
				
				if (currentWord.length()>0) {
					JPWord word = new JPWord();
					word.setValue(currentWord.toString());
					currentSentence.getWords().add(word);
					currentWord = new StringBuilder();
				}

				if (currentSentence.getWords().size()>0) {
					document.getSentenceArray().add(currentSentence);
					currentSentence = new JPSentence();
				}
			}
		}
		
		return document;
	}
}
