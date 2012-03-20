package Algorithms;

import Model.Article;
import Utilities.Log;

public class FuzzySim {

	
	public static double computeFuzzySim(Article a1, Article a2){
		
		double sim = 0;
		
		double max = Math.max(a1.getWordsArrayList().size(), a2.getWordsArrayList().size());
		
		double sum = 0;
		
		for (int i = 0; i < a1.getWordsArrayList().size(); i++) {
			String word1 = a1.getWordsArrayList().get(i);
			double maxDegree = 0;
			for (int j = 0; j < a2.getWordsArrayList().size(); j++) {
				String word2 = a2.getWordsArrayList().get(j);
				double msF = membershipFunction(word1, word2);
				if (msF > maxDegree) {
					maxDegree = msF;
					if (maxDegree == 1.0) {
						break;
					}
				}
			}
			sum += maxDegree;
		}
		
		sim = 1/max * sum;
		
		
		Log.nLog("The similarity between the two documents according to Fuzzy similarity: " + sim);
		
		return sim;
	}
	
	//Compute the relationship degree between two words. This is number in the interval [0-1].
	private static double membershipFunction(String w1, String w2){
		
		double max = Math.max(w1.length(), w2.length());
		
		double sum = 0.0;
		for (int i = 0; i < w1.length(); i++) {
			for (int j = 0; j < (w1.length()-i); j++) {
					String subSeq = w1.substring(j, i+j+1);
					if (w2.contains(subSeq)) {
						sum++;
					}
			}
		}
		
		double relDeg = 1.0 * (2 / (max*max+max)) * sum;
		
		return relDeg;
	}
	
}
