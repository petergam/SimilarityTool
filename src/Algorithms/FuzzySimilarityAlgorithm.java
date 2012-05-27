package Algorithms;

import Utilities.Log;
import WVToolExtension.JPWVTDocumentInfo;

public class FuzzySimilarityAlgorithm extends Algorithm {
	
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

	@Override
	public double[] compute(JPWVTDocumentInfo mainDocument,
			JPWVTDocumentInfo[] documents, boolean normalizeResult) {

		// one result for each document
		double[] resultArray = new double[documents.length];
		
		for (int documentIndex = 0; documentIndex < resultArray.length; documentIndex++) {
			JPWVTDocumentInfo currentDocument = documents[documentIndex];
			
			double sim = 0;
			
			double max = Math.max(mainDocument.getWordsArrayList().size(), currentDocument.getWordsArrayList().size());
			
			double sum = 0;
			
			for (int i = 0; i < mainDocument.getWordsArrayList().size(); i++) {
				String word1 = mainDocument.getWordsArrayList().get(i);
				double maxDegree = 0;
				for (int j = 0; j < currentDocument.getWordsArrayList().size(); j++) {
					String word2 = currentDocument.getWordsArrayList().get(j);
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
			
			String simString = "" + sim;
			simString = simString.substring(0, 4);
			Log.nLog("Fuzzi sim ["+ mainDocument.getSourceName() +" & " + currentDocument.sourceName + "]: " + simString);


			resultArray[documentIndex] = sim;
			
		}
		
		if (normalizeResult) {
			return normalizeResult(resultArray);
		}else {
			return resultArray;
		}
		
		
	}

	@Override
	public double[] normalizeResult(double[] resultArray) {
		return resultArray;
	}
	
}
