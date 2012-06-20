package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import Model.JPDocumentCallable;
import Objects.JPDocument;
import Objects.JPWord;
import Utilities.UtilMethods;

/**
 * The Class LevenshteinDistanceAlgorithm.
 * Implements the Levenshtein Distance algorithm
 */
public class LevenshteinDistanceAlgorithm extends JPAbstractAlgorithm {

	/* (non-Javadoc)
	 * @see Algorithms.JPAbstractAlgorithm#compute(Objects.JPDocument, Objects.JPDocument[], boolean, java.lang.Runnable)
	 */
	@Override
	//http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
	public void compute(final JPDocument mainDocument,
			final JPDocument[] documents, HashMap<String, Object> algorithmSettings, final boolean normalizeResult, final Runnable callbackDelegate) {
		
		Runnable backgroundRunnable = new Runnable() {
			@Override
			public void run() {
				
				final ArrayList<JPWord> allWords = mainDocument.getWords();
				final int mainDocumentSize = allWords.size();
				
				for (int documentIndex = 0; documentIndex < documents.length; documentIndex++) {
					JPDocumentCallable runnable = new JPDocumentCallable() {
						@Override
						public JPDocument call() {
							progressDelegate.willStartAlgorithmForDocument(document);

							
							ArrayList<JPWord> currentDocAllWords = document.getWords();

							int currentDocumentSize = currentDocAllWords.size();

							int[][] distance = new int[mainDocumentSize + 1][currentDocumentSize + 1];

							for (int i = 0; i <= mainDocumentSize; i++) {
				                theadUpdate();
								distance[i][0] = i;
							}
							for (int j = 0; j <= currentDocumentSize; j++) {
				                theadUpdate();

								distance[0][j] = j;
							}
							for (int i = 1; i <= mainDocumentSize; i++) {
				                theadUpdate();

								for (int j = 1; j <= currentDocumentSize; j++) {
					                theadUpdate();

									
									distance[i][j] = UtilMethods.minimum(
											distance[i - 1][j] + 1,   //deletion
											distance[i][j - 1] + 1,   //insertion
											distance[i - 1][j - 1] + ((allWords.get(i - 1).getValue().equals(currentDocAllWords.get(j - 1).getValue())) ? 0 : 1)); //substitution
								}
							}
						
							if (normalizeResult) {
								double normalizedResult = normalizeResult(mainDocument,document,distance[mainDocumentSize][currentDocumentSize]);
								document.setScore(normalizedResult);
							}else{
								document.setScore(distance[mainDocumentSize][currentDocumentSize]);
							}
							
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
		return (1-(score/Math.max(compareDoc.getNumberOfWords(),mainDoc.getNumberOfWords())));
	}

}