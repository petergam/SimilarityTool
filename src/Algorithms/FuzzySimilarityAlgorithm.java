	package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import Model.JPDocumentCallable;
import Objects.JPDocument;
import Objects.JPWord;

/**
 * The Class FuzzySimilarityAlgorithm.
 * Implements the Fuzzy Similarity algorithm
 */
public class FuzzySimilarityAlgorithm extends JPAbstractAlgorithm {
	

	/**
	 * Membership function. Computes the relationship degree between two words. This is number in the interval [0-1].
	 *
	 * @param word1 the first word
	 * @param word2 the second word
	 * @return the score
	 */
	private static double membershipFunction(String word1, String word2){		
		double max = Math.max(word1.length(), word2.length());

		double sum = 0.0;
		for (int i = 0; i < word1.length(); i++) {
			for (int j = 0; j < (word1.length()-i); j++) {
					String subSeq = word1.substring(j, i+j+1);
					if (word2.contains(subSeq)) {
						sum++;
					}
			}
		}
		
		double relDeg = 1.0 * (2 / (max*max+max)) * sum;
		
		return relDeg;
	}

	/* (non-Javadoc)
	 * @see Algorithms.JPAbstractAlgorithm#compute(Objects.JPDocument, Objects.JPDocument[], boolean, java.lang.Runnable)
	 */
	@Override
	public void compute(final JPDocument mainDocument,
			final JPDocument[] documents, final HashMap<String, Object> algorithmSettings, boolean normalizeResult, final Runnable callbackDelegate) {

		Runnable backgroundRunnable = new Runnable() {
			
			@Override
			public void run() {
				final ArrayList<JPWord> mainDocWords = mainDocument.getWords();

				final int mainDocumentWordCount = mainDocWords.size();
				final double threshold;
				if ((Boolean)algorithmSettings.get("ThresholdInclude")) {
					threshold = Double.parseDouble((String) algorithmSettings.get("Threshold"));
				}
				else{
					threshold = 0.5;
				}
				
				
				
				for (int documentIndex = 0; documentIndex < documents.length; documentIndex++) {
					JPDocumentCallable runnable = new JPDocumentCallable() {
						@Override
						public JPDocument call() {
							progressDelegate.willStartAlgorithmForDocument(document);

							ArrayList<JPWord> currentDocWords = document.getWords();
							int currentDocumentWordCount = currentDocWords.size();

							double sim = 0;
							double max = Math.max(mainDocumentWordCount, currentDocWords.size());
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
									
									if (maxDegree != 1.0 && mainDocWords.get(i).getNeighbourWord() !=null) {
										for (Object[] neighbourWord : mainDocWords.get(i).getNeighbourWord()) {
//											System.out.println(neighbourWord[0]);
											msF = membershipFunction((String)neighbourWord[0], word2);
											if (msF > maxDegree) {
												maxDegree = msF;
												if (maxDegree == 1.0) {
													break;
												}
											}
										}
									}
									
									
								}
								if (maxDegree > threshold) {
									sum += maxDegree;
								}
							}
							
							sim = 1/max * sum;
							document.setScore(sim);

							progressDelegate.didFinishAlgorithmForDocument(document);
							
							return null;

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

	/* (non-Javadoc)
	 * @see Algorithms.JPAbstractAlgorithm#normalizeResult(double[])
	 */
	@Override
	public double normalizeResult(JPDocument mainDoc, JPDocument compareDoc, double score) {
		
		return score;
	}
	
}
