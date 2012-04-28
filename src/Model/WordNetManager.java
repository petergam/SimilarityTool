package Model;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Word;
import edu.mit.jwi.morph.WordnetStemmer;


public enum WordNetManager {
    SharedInstance;
    
    private IDictionary dict;
    
    private WordNetManager() {
		String wnhome = System.getenv("WNHOME");
		String path = wnhome + File.separator + "dict";
				
		try {
			URL url = new URL("file", null, path);
			dict = new Dictionary(url);
			dict.open();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public IWord[] getWordsFromString(String wordString, POS pos) {
    	IIndexWord idxWord = dict.getIndexWord(wordString, pos);
    	
    	List<IWordID> wordIDs = idxWord.getWordIDs();
    	
    	IWord[] words = new IWord[wordIDs.size()];
    	
    	int index = 0;
    	for(IWordID wordID : wordIDs) {
    		words[index] = dict.getWord(wordID);
    		index++;
    	}    	
    	
    	return words;
    }
    
    public String[] getStemmedWords(String word, POS pos) {
		WordnetStemmer stemmer = new WordnetStemmer(dict);
		List<String> stem = stemmer.findStems(word, pos);
    	
		 String[] words = new String[stem.size()];
		 stem.toArray(words);
		
		return words;		
    }
    
	public IWord[] getSynonyms(IWord word){
		ISynset synset = word.getSynset();
		
		List<IWord> wordsList = synset.getWords();	
		IWord[] words = new IWord[wordsList.size()];
		wordsList.toArray(words);
		
		return words;
	}
	
	public String getSynonyms(String wordString, POS pos) {		
		StringBuffer synonyms = new StringBuffer();
		
		IIndexWord idxWord = dict.getIndexWord(wordString, pos);
		if(idxWord!=null) {
			for (IWordID wordID : idxWord.getWordIDs()) {
				IWord word = dict.getWord(wordID);
				ISynset synset = word.getSynset();

				for(IWord w : synset.getWords()) {
					synonyms.append(w.getLemma());
					synonyms.append(" ");
				}
			}
			
		}

		return synonyms.toString();
	}
    
    
}
