package WVToolExtension;

import java.util.ArrayList;
import java.util.Arrays;

public class JPWord {

	public enum JPWordType {
		JPWordTypeUnknown,
		JPWordTypeNoun,
		JPWordTypeVerb,
		JPWordTypeAdjective,
		JPWordTypeAdverb
	}
	
	private String value;
	private JPWord[] synonyms;
	private JPWord[] hypernyms;
	private JPWordType wordType;
	
	
	public JPWord() {

		wordType = JPWordType.JPWordTypeUnknown;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public JPWord[] getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(JPWord[] synonyms) {
		this.synonyms = synonyms;
	}
	public JPWord[] getHypernyms() {
		return hypernyms;
	}
	public void setHypernyms(JPWord[] hypernyms) {
		this.hypernyms = hypernyms;
	}
	public JPWordType getWordType() {
		return wordType;
	}
	public void setWordType(JPWordType wordType) {
		this.wordType = wordType;
	}
	
	public int getSynsetSize() {
		int size = 1;
		for (JPWord synonymWord : synonyms) {
			size += synonymWord.getSynsetSize();
		}
		
		for (JPWord hypernymWord : hypernyms) {
			size += hypernymWord.getSynsetSize();
		}
		
		return size;
	}
	
	public ArrayList<JPWord> getAllHypernyms() {
		if (hypernyms != null) {
			ArrayList<JPWord> words = new ArrayList<JPWord>(Arrays.asList(hypernyms));
			
			for (JPWord word : hypernyms) {
				ArrayList<JPWord> s = word.getAllHypernyms();
				if (s!=null) {
					words.addAll(s);
				}
			}
			
			return words;
		}
		
		return null;
	}
	
	public ArrayList<JPWord> getAllSynonyms() {
		if (synonyms != null) {
			ArrayList<JPWord> words = new ArrayList<JPWord>(Arrays.asList(synonyms));
			
			for (JPWord word : synonyms) {
				ArrayList<JPWord> s = word.getAllSynonyms();
				if (s!=null) {
					words.addAll(s);
				}
			}
			
			return words;
		}
		
		return null;
	}
	
}
