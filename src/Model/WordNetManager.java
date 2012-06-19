package Model;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Objects.JPWord;
import Objects.JPWord.JPWordPOS;
import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.WordnetStemmer;


// TODO: Auto-generated Javadoc
/**
 * The Enum WordNetManager.
 * Uses JWI to perform basic WordNet operations
 */
public enum WordNetManager {
    
    /** The Shared instance. */
    SharedInstance;
    
    /** The dict. */
    private IDictionary dict;
    
    /**
     * Gets the dict.
     *
     * @return the dict
     */
    public synchronized IDictionary getDict() {
		return dict;
	}

	/**
	 * Sets the dict.
	 *
	 * @param dict the new dict
	 */
	public synchronized void setDict(IDictionary dict) {
		this.dict = dict;
	}

	/**
	 * Instantiates a new word net manager.
	 */
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
    
    /**
     * Gets the words from string.
     *
     * @param wordString the word string
     * @param pos the pos
     * @return the words from string
     */
    public synchronized IWord[] getWordsFromString(String wordString, POS pos) {
    	IIndexWord idxWord = getDict().getIndexWord(wordString, pos);
    	
    	List<IWordID> wordIDs = idxWord.getWordIDs();
    	
    	IWord[] words = new IWord[wordIDs.size()];
    	
    	int index = 0;
    	for(IWordID wordID : wordIDs) {
    		words[index] = getDict().getWord(wordID);
    		index++;
    	}    	
    	
    	return words;
    }
    
    /**
     * Gets the stemmed words.
     *
     * @param word the word
     * @param pos the pos
     * @return the stemmed words
     */
    public synchronized String[] getStemmedWords(String word, POS pos) {
		WordnetStemmer stemmer = new WordnetStemmer(dict);
		List<String> stem = stemmer.findStems(word, pos);
    	
		 String[] words = new String[stem.size()];
		 stem.toArray(words);
		
		return words;		
    }
    
    
    public IWordID getWordID(JPWord word) {
		POS[] p;
		if (word.getWordPOS()!=JPWord.JPWordPOS.JPWordPOSUnknown) {
			POS pos = word.getPOS();
			p = new POS[]{pos};
		} else {
			p = new POS[]{POS.NOUN,POS.ADJECTIVE,POS.ADVERB,POS.VERB};
		}

		String value = "";
		if (word.getSenseValue() != null) {
			value = word.getSenseValue();
		} else {
			value = word.getValue();
		}
		
		for (POS pos : p) {
			IIndexWord idxWord = WordNetManager.SharedInstance.getDict().getIndexWord(value, pos);
			
			if (word.getSenseIndex() != JPWord.SenseIndexUnkown && idxWord !=null && word.getWordPOS()!=JPWord.JPWordPOS.JPWordPOSUnknown) {
				try {
					IWordID wordID = idxWord.getWordIDs().get(word.getSenseIndex()-1);
					return wordID;
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if(idxWord!=null){
				if(idxWord.getWordIDs().size()>0) {
					return idxWord.getWordIDs().get(0);
				}
			}
		}
		
		return null;
    }
	
	/**
	 * Gets the synonyms.
	 *
	 * @param wordID the word id
	 * @param layers the layers
	 * @return the synonyms
	 */
	public List<IWord> getSynonyms(IWordID wordID) {
		IWord iword = getDict().getWord(wordID);
		ISynset synset = iword.getSynset();
	
		if (synset!=null) {
			return synset.getWords();
		} else {
			return new ArrayList<IWord>();
		}
		
	}
	
	/**
	 * Gets the hypernyms.
	 *
	 * @param wordID the word id
	 * @param layers the layers
	 * @return the hypernyms
	 */
	public List<IWord> getHypernyms(IWordID wordID) {
		IWord iword = getDict().getWord(wordID);
		ISynset synset = iword.getSynset();

		List<IWord> hypernyms = new ArrayList<IWord>();
		
		List<ISynsetID> hypernymsSynset = synset.getRelatedSynsets(Pointer.HYPERNYM);

		for(ISynsetID sid : hypernymsSynset){
			hypernyms.addAll(getDict().getSynset(sid).getWords());
		}
		
		return hypernyms;
	}

	/**
	 * Gets the hyponyms.
	 *
	 * @param wordID the word id
	 * @param layers the layers
	 * @return the hyponyms
	 */
	public List<IWord> getHyponyms(IWordID wordID) {
		IWord iword = getDict().getWord(wordID);
		ISynset synset = iword.getSynset();

		List<IWord> hyponyms = new ArrayList<IWord>();
		
		
		List<ISynsetID> hypernymsSynset = synset.getRelatedSynsets(Pointer.HYPONYM);

		for(ISynsetID sid : hypernymsSynset){
			hyponyms.addAll(getDict().getSynset(sid).getWords());
		}
		
		return hyponyms;

	}
	
	/**
	 * Stem word.
	 *
	 * @param word the word
	 * @return the string
	 */
	public synchronized String stemWord(JPWord word) {
		String value = word.getValue();
		
		POS[] p;
		if (word.getWordPOS()!=JPWord.JPWordPOS.JPWordPOSUnknown) {
			POS pos = word.getPOS();
			p = new POS[]{pos};
		} else {
			p = new POS[]{POS.NOUN,POS.ADJECTIVE,POS.ADVERB,POS.VERB};
		}
		
		for (POS pos : p) {
			IIndexWord idxWord = getDict().getIndexWord(word.getValue(), pos);
			if (idxWord != null && idxWord.getWordIDs().size()>0) {
				IWord iWord = getDict().getWord(idxWord.getWordIDs().get(0));
				value = iWord.getLemma();
			}
		}
		
		return value;
	}
}
