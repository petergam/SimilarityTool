package Model;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Objects.JPWord;
import Objects.JPWord.JPWordType;
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
    
	/**
	 * Gets the synonyms.
	 *
	 * @param word the word
	 * @return the synonyms
	 */
	public synchronized IWord[] getSynonyms(IWord word){
		ISynset synset = word.getSynset();
		
		List<IWord> wordsList = synset.getWords();	
		IWord[] words = new IWord[wordsList.size()];
		wordsList.toArray(words);
		
		return words;
	}
	
	/**
	 * Find synonyms.
	 *
	 * @param word the word
	 * @param layers the layers
	 */
	public synchronized void findSynonyms(JPWord word, int layers) {						
		ArrayList<JPWord> synonyms = new ArrayList<JPWord>();
		
		POS[] p;
		if (word.getWordType()!=JPWord.JPWordType.JPWordTypeUnknown) {
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
			IIndexWord idxWord = getDict().getIndexWord(value, pos);
			if (word.getSenseIndex() != JPWord.SenseIndexUnkown && idxWord !=null && word.getWordType()!=JPWordType.JPWordTypeUnknown) {

				IWordID wordID = idxWord.getWordIDs().get(word.getSenseIndex()-1);
				synonyms = getSynonyms(wordID,layers);
			} else if(idxWord!=null){
				if(idxWord.getWordIDs().size()>0) {
					synonyms.addAll(getHyponyms(idxWord.getWordIDs().get(0), layers));
				}
			}
		}

		word.setSynonyms(synonyms);		
	}
	
	/**
	 * Gets the synonyms.
	 *
	 * @param wordID the word id
	 * @param layers the layers
	 * @return the synonyms
	 */
	private ArrayList<JPWord> getSynonyms(IWordID wordID, int layers) {
		IWord iword = getDict().getWord(wordID);
		ISynset synset = iword.getSynset();
		ArrayList<JPWord> synonyms = new ArrayList<JPWord>();
		
		if (synset != null) {
			for(IWord w : synset.getWords()) {
				JPWord newWord = new JPWord();
				newWord.setValue(w.getLemma());
				synonyms.add(newWord);
				
				if (layers>0) {
					newWord.setSynonyms(getSynonyms(w.getID(), layers-1));
				}
			}
		}
	
		
		return synonyms;
	}
	
	/**
	 * Find hypernyms.
	 *
	 * @param word the word
	 * @param layers the layers
	 */
	public synchronized void findHypernyms(JPWord word, int layers) {				
		ArrayList<JPWord> hypernyms = new ArrayList<JPWord>();
		
		POS[] p;
		if (word.getWordType()!=JPWord.JPWordType.JPWordTypeUnknown) {
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
			IIndexWord idxWord = getDict().getIndexWord(value, pos);
			
			if (word.getSenseIndex() != JPWord.SenseIndexUnkown && idxWord !=null && word.getWordType()!=JPWord.JPWordType.JPWordTypeUnknown) {
				
				IWordID wordID = idxWord.getWordIDs().get(word.getSenseIndex()-1);
				hypernyms = getHypernyms(wordID, layers);
			} else if(idxWord!=null){
				if(idxWord.getWordIDs().size()>0) {
					hypernyms.addAll(getHyponyms(idxWord.getWordIDs().get(0), layers));
				}
			}
		}
		
		word.setHypernyms(hypernyms);
	}
	
	/**
	 * Gets the hypernyms.
	 *
	 * @param wordID the word id
	 * @param layers the layers
	 * @return the hypernyms
	 */
	private ArrayList<JPWord> getHypernyms(IWordID wordID, int layers) {
		IWord iword = getDict().getWord(wordID);
		ISynset synset = iword.getSynset();

		ArrayList<JPWord> hypernyms = new ArrayList<JPWord>();
		
		List<ISynsetID> hypernymsSynset = synset.getRelatedSynsets(Pointer.HYPERNYM);

		List<IWord> words;
		for(ISynsetID sid : hypernymsSynset){
			words = getDict().getSynset(sid).getWords();
			for(Iterator<IWord> i = words.iterator(); i.hasNext();) {
				JPWord word = new JPWord();
				IWord iWord = i.next();
				word.setValue(iWord.getLemma());
				hypernyms.add(word);
				
				if (layers>0) {
					word.setHypernyms(getHypernyms(iWord.getID(), layers-1));
				}
			}
		}
		
		return hypernyms;
	}
	
	/**
	 * Find hyponyms.
	 *
	 * @param word the word
	 * @param layers the layers
	 */
	public synchronized void findHyponyms(JPWord word, int layers) {				
		ArrayList<JPWord> hyponyms = new ArrayList<JPWord>();
		
		POS[] p;
		if (word.getWordType()!=JPWord.JPWordType.JPWordTypeUnknown) {
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
			
			IIndexWord idxWord = getDict().getIndexWord(value, pos);
			
			if (word.getSenseIndex() != JPWord.SenseIndexUnkown && idxWord !=null && word.getWordType()!=JPWord.JPWordType.JPWordTypeUnknown) {				
				IWordID wordID = idxWord.getWordIDs().get(word.getSenseIndex()-1);
				hyponyms = getHyponyms(wordID, layers);
			} else if(idxWord!=null){
				if(idxWord.getWordIDs().size()>0) {
					hyponyms.addAll(getHyponyms(idxWord.getWordIDs().get(0), layers));
				}
			}
		}
		
		word.setHyponyms(hyponyms);
	}
	
	/**
	 * Gets the hyponyms.
	 *
	 * @param wordID the word id
	 * @param layers the layers
	 * @return the hyponyms
	 */
	private ArrayList<JPWord> getHyponyms(IWordID wordID, int layers) {
		IWord iword = getDict().getWord(wordID);
		ISynset synset = iword.getSynset();

		ArrayList<JPWord> hyponyms = new ArrayList<JPWord>();
		
		List<ISynsetID> hypernymsSynset = synset.getRelatedSynsets(Pointer.HYPONYM);

		List<IWord> words;
		for(ISynsetID sid : hypernymsSynset){
			words = getDict().getSynset(sid).getWords();
			for(Iterator<IWord> i = words.iterator(); i.hasNext();) {
				JPWord word = new JPWord();
				IWord iWord = i.next();
				word.setValue(iWord.getLemma());
				hyponyms.add(word);
				
				if (layers>0) {
					word.setHypernyms(getHypernyms(iWord.getID(), layers-1));
				}
			}
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
		if (word.getWordType()!=JPWord.JPWordType.JPWordTypeUnknown) {
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
