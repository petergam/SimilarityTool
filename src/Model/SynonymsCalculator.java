package Model;

import java.util.ArrayList;

import Objects.JPWord;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

public class SynonymsCalculator {

	private JPCache cache;
	
	
//	public SynonymsCalculator() {
//		cache = new JPCache("SynonymsCalculator");
//	}
//	
//	public synchronized void findSynonyms(JPWord word, int layers, double score) {						
//		findSynonyms(word, layers, score, null);
//	}
//	
//	/**
//	 * Find synonyms.
//	 *
//	 * @param word the word
//	 * @param layers the layers
//	 */
//	public synchronized Arra findSynonyms(JPWord word, int layers, double score, JPWord ignoreWord) {						
//		POS[] p;
//		if (word.getWordPOS()!=JPWord.JPWordPOS.JPWordPOSUnknown) {
//			POS pos = word.getPOS();
//			p = new POS[]{pos};
//		} else {
//			p = new POS[]{POS.NOUN,POS.ADJECTIVE,POS.ADVERB,POS.VERB};
//		}
//		ArrayList<JPWord> synonyms = new ArrayList<JPWord>();
//
//		String value = "";
//		if (word.getSenseValue() != null) {
//			value = word.getSenseValue();
//		} else {
//			value = word.getValue();
//		}
//		
//		for (POS pos : p) {
//			IIndexWord idxWord = WordNetManager.SharedInstance.getDict().getIndexWord(value, pos);
//			
//			if (word.getSenseIndex() != JPWord.SenseIndexUnkown && idxWord !=null && word.getWordPOS()!=JPWord.JPWordPOS.JPWordPOSUnknown) {
//				try {
//					IWordID wordID = idxWord.getWordIDs().get(word.getSenseIndex()-1);
//					synonyms = WordNetManager.SharedInstance.getSynonyms(wordID, 0);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			} else if(idxWord!=null){
//				if(idxWord.getWordIDs().size()>0) {
//					synonyms.addAll(WordNetManager.SharedInstance.getSynonyms(idxWord.getWordIDs().get(0), 0));
//
//					break;
//				}
//			}
//		}
//		
//		ArrayList<JPWord> removeWords = new ArrayList<JPWord>();
//		ArrayList<JPWord> addWords = new ArrayList<JPWord>();
//
//		for(JPWord w : synonyms) {
//			if(ignoreWord!=null && w.getValue().equalsIgnoreCase(ignoreWord.getValue())) {
//				removeWords.add(w);
//			} else {
//				if (cache.containsKey(w.getValue()+w.getWordPOS()+w.getSenseIndex())) {
//					JPWord sameWord = (JPWord) cache.getCachedValue(w.getValue()+w.getWordPOS()+w.getSenseIndex());
//					addWords.add(sameWord);
//					removeWords.add(w);
//					
//					if(sameWord.getScore()<w.getScore()) {
//						sameWord.setScore(w.getScore());
//					}
//					
//				} else {
//					cache.setCachedValue(w.getValue()+w.getWordPOS()+w.getSenseIndex(), w);
//				}
//				
//				w.setScore(word.getScore()*score);
//				
//				if (layers>0) {
//					findSynonyms(w, layers-1, score,word);
//				}
//			}
//		}
//		for (JPWord w : removeWords) {
//			synonyms.remove(w);
//		}
//		
//		for (JPWord w : addWords) {
//			synonyms.add(w);
//		}
//		
//		
//		
//		word.setSynonyms(synonyms);
//	}
	
}
