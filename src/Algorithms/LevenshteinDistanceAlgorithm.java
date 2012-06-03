package Algorithms;

import java.util.ArrayList;

import javax.swing.SwingWorker;

import Model.DocumentRunnable;
import Utilities.Log;
import Utilities.UtilMethods;
import WVToolAdditions.JPWord;
import WVToolExtension.JPWVTDocumentInfo;

public class LevenshteinDistanceAlgorithm extends Algorithm {

	@Override
	//http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
	public void compute(final JPWVTDocumentInfo mainDocument,
			final JPWVTDocumentInfo[] documents, boolean normalizeResult, final Runnable callbackDelegate) {
		
		Runnable backgroundRunnable = new Runnable() {
			@Override
			public void run() {
				
				final ArrayList<JPWord> allWords = mainDocument.getAllWords();
				final int mainDocumentSize = allWords.size();
				final float updatePercent = (float) (100.0/documents.length);
				
				for (int documentIndex = 0; documentIndex < documents.length; documentIndex++) {
					DocumentRunnable runnable = new DocumentRunnable() {
						@Override
						public void run() {
							progressDelegate.willStartAlgorithmForDocument(document);

							
							ArrayList<JPWord> currentDocAllWords = document.getAllWords();

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
						
							document.setScore(distance[mainDocumentSize][currentDocumentSize]);
							
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


		
//		SwingWorker<Integer, Integer> worker = new SwingWorker<Integer, Integer>() {
//			protected Integer doInBackground() throws Exception {
//				
//				final ArrayList<JPWord> allWords = mainDocument.getAllWords();
//				final int mainDocumentSize = allWords.size();
//				final float updatePercent = (float) (100.0/documents.length);
//				
//				for (int documentIndex = 0; documentIndex < documents.length; documentIndex++) {
//					DocumentRunnable runnable = new DocumentRunnable() {
//						@Override
//						public void run() {
//							ArrayList<JPWord> currentDocAllWords = document.getAllWords();
//
//							int currentDocumentSize = currentDocAllWords.size();
//
//							int[][] distance = new int[mainDocumentSize + 1][currentDocumentSize + 1];
//
//							for (int i = 0; i <= mainDocumentSize; i++) {
//				                theadUpdate();
//								distance[i][0] = i;
//							}
//							for (int j = 0; j <= currentDocumentSize; j++) {
//				                theadUpdate();
//
//								distance[0][j] = j;
//							}
//							for (int i = 1; i <= mainDocumentSize; i++) {
//				                theadUpdate();
//
//								for (int j = 1; j <= currentDocumentSize; j++) {
//					                theadUpdate();
//
//									
//									distance[i][j] = UtilMethods.minimum(
//											distance[i - 1][j] + 1,   //deletion
//											distance[i][j - 1] + 1,   //insertion
//											distance[i - 1][j - 1] + ((allWords.get(i - 1).getValue().equals(currentDocAllWords.get(j - 1).getValue())) ? 0 : 1)); //substitution
//								}
//							}
//						
//							document.setScore(distance[mainDocumentSize][currentDocumentSize]);
//							
//							didProgress(updatePercent);							
//						}
//					};
//					
//					runnable.document = documents[documentIndex];
//					engine.submit(runnable);
//				}
//				
//				return null;
//			}
//			
//	        protected void done() {
//	    		callDelegate(callbackDelegate);
//	        }
//		};
//		
//		worker.execute();
		
//		
//		for (int documentIndex = 0; documentIndex < documents.length; documentIndex++) {
//			final int i = documentIndex;
//			engine.submit(new Runnable() {
//				@Override
//				public void run() {
//					JPWVTDocumentInfo currentDocument = documents[i];
//					ArrayList<JPWord> currentDocAllWords = currentDocument.getAllWords();
//
//					int currentDocumentSize = currentDocAllWords.size();
//
//					int[][] distance = new int[mainDocumentSize + 1][currentDocumentSize + 1];
//
//					for (int i = 0; i <= mainDocumentSize; i++) {
//		                theadUpdate();
//						distance[i][0] = i;
//					}
//					for (int j = 0; j <= currentDocumentSize; j++) {
//		                theadUpdate();
//
//						distance[0][j] = j;
//					}
//					for (int i = 1; i <= mainDocumentSize; i++) {
//		                theadUpdate();
//
//						for (int j = 1; j <= currentDocumentSize; j++) {
//			                theadUpdate();
//
//							
//							distance[i][j] = UtilMethods.minimum(
//									distance[i - 1][j] + 1,   //deletion
//									distance[i][j - 1] + 1,   //insertion
//									distance[i - 1][j - 1] + ((allWords.get(i - 1).getValue().equals(currentDocAllWords.get(j - 1).getValue())) ? 0 : 1)); //substitution
//						}
//					}
//					resultArray[i] = distance[mainDocumentSize][currentDocumentSize];
//				
//					Log.nLog("LevenshteinDistance is: " + distance[mainDocumentSize][currentDocumentSize]);
//
//					currentDocument.setScore(distance[mainDocumentSize][currentDocumentSize]);
//					
//					didProgress(updatePercent);
//				}
//			});
//			
//		}
//		
//		engine.shutdown();
//		callDelegate(callbackDelegate);	
	}

	@Override
	public double[] normalizeResult(double[] resultArray) {
		// TODO Auto-generated method stub
		return null;
	}

}