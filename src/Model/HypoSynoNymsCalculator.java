package Model;

import java.util.ArrayList;
import java.util.HashMap;

import Objects.JPWord;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

public class HypoSynoNymsCalculator {
//	
//	private JPCache cache;
//	
//	public HypoSynoNymsCalculator() {
//		cache = new JPCache("HypoSynoNymsCalculator");
//	}
//	
//	public synchronized void findHypoHyperNyms(JPWord word, int layers, double hypernymScore, double hyponymScore) {
//		findHypoHyperNyms(word, layers, hypernymScore, hyponymScore, null);
//	}
//	
//
//
//	/**
//	 * Find hyponyms and hypernyms for a word.
//	 *
//	 * @param word the word
//	 * @param layers the layers
//	 * @param hypernymScore the hypernym score
//	 * @param hyponymScore the hyponym score
//	 */
//	public synchronized void findHypoHyperNyms(JPWord word, int layers, double hypernymScore, double hyponymScore, JPWord ignoreWord) {
//		POS[] p;
//		if (word.getWordPOS()!=JPWord.JPWordPOS.JPWordPOSUnknown) {
//			POS pos = word.getPOS();
//			p = new POS[]{pos};
//		} else {
//			p = new POS[]{POS.NOUN,POS.ADJECTIVE,POS.ADVERB,POS.VERB};
//		}
//		ArrayList<JPWord> hypernyms = new ArrayList<JPWord>();
//		ArrayList<JPWord> hyponyms = new ArrayList<JPWord>();
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
//					hypernyms = WordNetManager.SharedInstance.getHypernyms(wordID, 0);
//					hyponyms = WordNetManager.SharedInstance.getHyponyms(wordID, 0);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			} else if(idxWord!=null){
//				if(idxWord.getWordIDs().size()>0) {
//					hypernyms.addAll(WordNetManager.SharedInstance.getHypernyms(idxWord.getWordIDs().get(0), 0));
//					hyponyms.addAll(WordNetManager.SharedInstance.getHyponyms(idxWord.getWordIDs().get(0), 0));
//
//					break;
//				}
//			}
//		}
//		
//		ArrayList<JPWord> removeWords = new ArrayList<JPWord>();
//		ArrayList<JPWord> addWords = new ArrayList<JPWord>();
//
//		for(JPWord w : hypernyms) {
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
//					cache.setCachedValue(w,w.getValue()+w.getWordPOS()+w.getSenseIndex());
//				}
//				
//				w.setScore(word.getScore()*hypernymScore);
//				
//				if (layers>0) {
//					findHypoHyperNyms(w, layers-1, hypernymScore, hyponymScore,word);
//				}
//			}
//		}
//		for (JPWord w : removeWords) {
//			hypernyms.remove(w);
//		}
//		
//		for (JPWord w : addWords) {
//			hypernyms.add(w);
//		}
//		
//		removeWords.clear();
//		addWords.clear();
//		
//		for (JPWord w : hyponyms) {
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
//					cache.setCachedValue(w,w.getValue()+w.getWordPOS()+w.getSenseIndex());
//				}
//				
//				w.setScore(word.getScore()*hyponymScore);
//				if (layers>0) {
//					findHypoHyperNyms(w, layers-1, hypernymScore, hyponymScore, word);
//				}
//			}
//
//		}
//		
//		for (JPWord w : removeWords) {
//			hyponyms.remove(w);
//		}
//		
//		for (JPWord w : addWords) {
//			hyponyms.add(w);
//		}
//		
//		word.setHypernyms(hypernyms);
//		word.setHyponyms(hyponyms);
//	}
	
}
