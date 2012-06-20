package Parser;

import java.util.ArrayList;
import java.util.HashMap;

import Model.WordNetManager;
import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import Objects.JPWordIndex;
import edu.mit.jwi.item.IWordID;

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
		StringBuilder currentWord = new StringBuilder();
		JPSentence currentSentence = new JPSentence();
		for (char ch: string.toCharArray()) {
			if (Character.isLetter(ch)) {
				currentWord.append(ch);
			} else if (ch == ' ' && currentWord.length()>0) {
				JPWord word = new JPWord();
				word.setValue(currentWord.toString());
				currentSentence.getWords().add(word);

				document.setNumberOfWords(document.getNumberOfWords()+1);
				
				currentWord = new StringBuilder();
				
			} else if(ch == '.' || ch == '!' || ch == '?' || ch == ':' || ch == ';' || ch == '(' || ch == ')') {
				
				if (currentWord.length()>0) {
					JPWord word = new JPWord();
					word.setValue(currentWord.toString());
					currentSentence.getWords().add(word);
					currentWord = new StringBuilder();

					document.setNumberOfWords(document.getNumberOfWords()+1);

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
		
		int sIndex = 0;
		for (JPSentence sentence : document.getSentenceArray()) {
			int wIndex = 0;
			for (JPWord word : sentence.getWords()) {
				IWordID id = WordNetManager.SharedInstance.getWordID(word);
				word.wordNetID = id;
				
				HashMap<String, ArrayList<JPWordIndex>> wordHashMap = document.getWordHashMap();
				if (wordHashMap.containsKey(word.getValue())) {
					JPWordIndex wordIndex = new JPWordIndex();
					wordIndex.setWordIndex(wIndex);
					wordIndex.setSentenceIndex(sIndex);
					
					ArrayList<JPWordIndex> indexes = wordHashMap.get(word.getValue());
					indexes.add(wordIndex);
				} else {
					ArrayList<JPWordIndex> indexes = new ArrayList<JPWordIndex>();
					JPWordIndex wordIndex = new JPWordIndex();
					wordIndex.setWordIndex(wIndex);
					wordIndex.setSentenceIndex(sIndex);
					indexes.add(wordIndex);
					wordHashMap.put(word.getValue(), indexes);
				}
				wIndex++;
			}
			sIndex++;
		}
				
		return document;
	}
}
