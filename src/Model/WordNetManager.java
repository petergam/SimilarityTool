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


public enum WordNetManager {
    SharedInstance;
    
    private IDictionary dict;
    
    public synchronized IDictionary getDict() {
		return dict;
	}

	public synchronized void setDict(IDictionary dict) {
		this.dict = dict;
	}

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
    
    public synchronized String[] getStemmedWords(String word, POS pos) {
		WordnetStemmer stemmer = new WordnetStemmer(dict);
		List<String> stem = stemmer.findStems(word, pos);
    	
		 String[] words = new String[stem.size()];
		 stem.toArray(words);
		
		return words;		
    }
    
	public synchronized IWord[] getSynonyms(IWord word){
		ISynset synset = word.getSynset();
		
		List<IWord> wordsList = synset.getWords();	
		IWord[] words = new IWord[wordsList.size()];
		wordsList.toArray(words);
		
		return words;
	}
	
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

				System.out.println("syno " + value);
				IWordID wordID = idxWord.getWordIDs().get(word.getSenseIndex()-1);
				synonyms = getSynonyms(wordID,layers);
			} else if(idxWord!=null){
				for (IWordID wordID : idxWord.getWordIDs()) {
					synonyms.addAll(getSynonyms(wordID, layers));					
				}
			}
		}

		word.setSynonyms(synonyms);		
	}
	
	private ArrayList<JPWord> getSynonyms(IWordID wordID, int layers) {
		IWord iword = getDict().getWord(wordID);
		ISynset synset = iword.getSynset();
		ArrayList<JPWord> synonyms = new ArrayList<JPWord>();
		
		for(IWord w : synset.getWords()) {
			JPWord newWord = new JPWord();
			newWord.setValue(w.getLemma());
			synonyms.add(newWord);
			
			if (layers>1) {
				newWord.setSynonyms(getSynonyms(w.getID(), layers-1));
			}
		}
		
		return synonyms;
	}
	
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
				
				System.out.println("hyper " + value);
				IWordID wordID = idxWord.getWordIDs().get(word.getSenseIndex()-1);
				hypernyms = getHypernyms(wordID, layers);
			} else if(idxWord!=null){
				for (IWordID wordID : idxWord.getWordIDs()) {
					hypernyms.addAll(getHypernyms(wordID, layers));
				}
			}
		}
		
		word.setHypernyms(hypernyms);
	}
	
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
				for (IWordID wordID : idxWord.getWordIDs()) {
					hyponyms.addAll(getHyponyms(wordID, layers));
				}
			}
		}
		
		word.setHyponyms(hyponyms);
	}
	
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
