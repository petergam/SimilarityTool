package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import Objects.JPWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;

public class NeighbourWordsFactory {

	private JPCache synonymCache = new JPCache();
	private JPCache hypernymCache = new JPCache();
	private JPCache hyponymCache = new JPCache();

	private Double synonymScore = 0.9;
	
	public Double getSynonymScore() {
		return synonymScore;
	}

	public void setSynonymScore(Double synonymScore) {
		this.synonymScore = synonymScore;
	}

	public Double getHypernymScore() {
		return hypernymScore;
	}

	public void setHypernymScore(Double hypernymScore) {
		this.hypernymScore = hypernymScore;
	}

	public Double getHyponumScore() {
		return hyponumScore;
	}

	public void setHyponumScore(Double hyponumScore) {
		this.hyponumScore = hyponumScore;
	}

	private Double hypernymScore = 0.4;
	private Double hyponumScore = 0.8;
	
	public NeighbourWordsFactory() {
		synonymCache.loadCache("NeighbourWordsFactorySynonym");
		hypernymCache.loadCache("NeighbourWordsFactoryHypernym");
		hyponymCache.loadCache("NeighbourWordsFactoryHyponym");

	}
	
	public void findNeighbours(JPWord word, int layers, boolean includeSynonyms, boolean includeHypernymsHyponyms) {
		WordNetManager manager = WordNetManager.SharedInstance;

		
		HashMap<IWord, Object[]> scores = new HashMap<IWord, Object[]>();
		
		IWordID iWordID = manager.getWordID(word);
		
		if (includeSynonyms) {

	        Queue<IWordID> queue = new LinkedList<IWordID>();
	        queue.add(iWordID);
	        
	        HashMap<IWordID, Object[]> layersHashMap = new HashMap<IWordID, Object[]>();
	        layersHashMap.put(iWordID, new Object[] {1.0, 0});
			
			while(queue.isEmpty() == false) {
				IWordID currentWordID = queue.poll();
				
				if (currentWordID==null) {
					continue;
				}
				
				Double currentScore = (Double) layersHashMap.get(currentWordID)[0];
				
				List<IWord> synonyms = null;
				if (synonymCache.containsKey(currentWordID)) {
					synonyms = (List<IWord>) synonymCache.getCachedValue(currentWordID);
				} else {
					synonyms = manager.getSynonyms(currentWordID);
					if (synonyms == null) {
						synonyms = new ArrayList<IWord>();
					}

					synonymCache.setCachedValue(synonyms, currentWordID);
				}
				
				for (IWord iWord : synonyms) {
					Double score = currentScore*synonymScore;
					if (iWord.getID() != iWordID && (!scores.containsKey(iWord) || (scores.containsKey(iWord) && (Double)scores.get(iWord)[2] < score))) {
						scores.put(iWord, new Object[]{iWord.getLemma(),iWord.getID(),score});
					}
					int currentLayer = (Integer) layersHashMap.get(currentWordID)[1];
					if (currentLayer<layers) {
						queue.add(iWord.getID());

						layersHashMap.put(iWord.getID(), new Object[]{score ,currentLayer+1});
					}					
				}				
			}
		}
		
		
		if (includeHypernymsHyponyms) {
	        Queue<IWordID> queue = new LinkedList<IWordID>();
	        queue.add(iWordID);
	        
	        HashMap<IWordID, Object[]> layersHashMap = new HashMap<IWordID, Object[]>();
	        layersHashMap.put(iWordID, new Object[] {1.0, 0});
			
			while (queue.isEmpty() == false) {
				IWordID currentWordID = queue.poll();
				
				if (currentWordID==null) {
					continue;
				}
				Double currentScore = (Double) layersHashMap.get(currentWordID)[0];

				
				List<IWord> hypernyms = null;
				if (hypernymCache.containsKey(currentWordID)) {
					hypernyms = (List<IWord>) hypernymCache.getCachedValue(currentWordID);
				} else {
					hypernyms = manager.getHypernyms(currentWordID);
					if (hypernyms == null) {
						hypernyms = new ArrayList<IWord>();
					}
					hypernymCache.setCachedValue(hypernyms, currentWordID);
				}
				
				List<IWord> hyponyms = null;
				if (hyponymCache.containsKey(currentWordID)) {
					hyponyms = (List<IWord>) hyponymCache.getCachedValue(currentWordID);
				} else {
					hyponyms = manager.getHyponyms(currentWordID);
					if (hyponyms == null) {
						hyponyms = new ArrayList<IWord>();
					}
					hyponymCache.setCachedValue(hyponyms, currentWordID);
				}
				
				for (IWord iWord : hypernyms) {
					Double score = currentScore*hypernymScore;
					if (iWord.getID() != iWordID && (!scores.containsKey(iWord) || (scores.containsKey(iWord) && (Double)scores.get(iWord)[2] < score))) {
						scores.put(iWord, new Object[]{iWord.getLemma(),iWord.getID(),score});
					}
					int currentLayer = (Integer) layersHashMap.get(currentWordID)[1];
					if (currentLayer<layers) {
						queue.add(iWord.getID());
						layersHashMap.put(iWord.getID(), new Object[]{score ,currentLayer+1});
					}					
				}	
				
				for (IWord iWord : hyponyms) {
					Double score = currentScore*hyponumScore;
					if (iWord.getID() != iWordID && (!scores.containsKey(iWord) || (scores.containsKey(iWord) && (Double)scores.get(iWord)[2] < score))) {
						scores.put(iWord, new Object[]{iWord.getLemma(),iWord.getID(),score});
					}
					int currentLayer = (Integer) layersHashMap.get(currentWordID)[1];
					if (currentLayer<layers) {
						queue.add(iWord.getID());
						layersHashMap.put(iWord.getID(), new Object[]{score ,currentLayer+1});
					}					
				}	
			}
		}
		
		   // not yet sorted
	    ArrayList<Object[]> scoreArray = new ArrayList<Object[]>(scores.values());

	    Collections.sort(scoreArray, new Comparator<Object[]>() {

	        public int compare(Object[] o1, Object[] o2) {
	        	return (Double)o1[2]>(Double)o2[2] ? 0 : 1;
	        }
	    });
	    	    
	    
	    word.setNeighbourWord(scoreArray);		

	}
	
	
}
