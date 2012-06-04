package Algorithms;

import java.util.ArrayList;

import Model.DocumentRunnable;
import WVToolAdditions.JPWord;
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
	public void compute(final JPWVTDocumentInfo mainDocument,
			final JPWVTDocumentInfo[] documents, boolean normalizeResult, final Runnable callbackDelegate) {

		Runnable backgroundRunnable = new Runnable() {
			@Override
			public void run() {
				final ArrayList<JPWord> mainDocWords = mainDocument.getAllWords();
				final int mainDocumentWordCount = mainDocWords.size();
				
				for (int documentIndex = 0; documentIndex < documents.length; documentIndex++) {
					DocumentRunnable runnable = new DocumentRunnable() {
						@Override
						public void run() {
							progressDelegate.willStartAlgorithmForDocument(document);
							
							ArrayList<JPWord> currentDocWords = document.getAllWords();
							int currentDocumentWordCount = currentDocWords.size();
							
							double sim = 0;
							double max = Math.max(mainDocumentWordCount, currentDocumentWordCount);
							double sum = 0;

							for (int i = 0; i < mainDocumentWordCount; i++) {
				                theadUpdate();
								String word1 = mainDocWords.get(i).getValue();
								double maxDegree = 0;
								for (int j = 0; j < currentDocumentWordCount; j++) {
					                theadUpdate();

									String word2 = currentDocWords.get(j).getValue();
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
							document.setScore(sim);
							
							progressDelegate.didFinishAlgorithmForDocument(document);
						}
					};
					
					runnable.document = documents[documentIndex];
					engine.submit(runnable);
				}				
			}
		};
		
		Runnable doneRunnable = new Runnable() {
			@Override
			public void run() {
	    		callDelegate(callbackDelegate);				
			}
		};
		
		run(backgroundRunnable, doneRunnable);
			
	}

	@Override
	public double[] normalizeResult(double[] resultArray) {
		return resultArray;
	}
	
}
