package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import Model.JPDocumentCallable;
import Objects.JPDocument;
import Objects.JPWord;
import Objects.JPWordIndex;

/**
 * The Class OnthologyBasedQueryAlgorithm.
 * Implements the Onthology Based Query algorithm
 */
public class OntologyBasedQueryAlgorithm extends JPAbstractAlgorithm {

	/* (non-Javadoc)
	 * @see Algorithms.JPAbstractAlgorithm#compute(Objects.JPDocument, Objects.JPDocument[], boolean, java.lang.Runnable)
	 */
	@Override
	public void compute(final JPDocument mainDocument, final JPDocument[] documents, final HashMap<String, Object> algorithmSettings,
			final boolean normalizeResult, final Runnable callbackDelegate) {
		
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
					threshold = 0;
				}
				
				for (int documentIndex = 0; documentIndex < documents.length; documentIndex++) {
					JPDocumentCallable runnable = new JPDocumentCallable() {
						@Override
						public JPDocument call() {
							progressDelegate.willStartAlgorithmForDocument(document);

							double sum = 0.0;
							
							for (int i = 0; i < mainDocumentWordCount; i++) {
				                theadUpdate();

								JPWord currentWord = mainDocWords.get(i);
								
								ArrayList<JPWordIndex> docWordIndexes = document.getWordHashMap().get(currentWord.getValue());
								double score = 0.0;	
								try {
									if (docWordIndexes != null) {
										for (JPWordIndex index : docWordIndexes) {
											JPWord anotherWord = document.getSentenceArray().get(index.getSentenceIndex()).getWords().get(index.getWordIndex());
											if(algorithmSettings.get("MatchIndex").equals(1)) {
												if ((anotherWord.wordNetID != null && currentWord.wordNetID != null) && anotherWord.wordNetID.equals(currentWord.wordNetID)) {
													score = 1.0;
													break;
												}
											} else {
												if (currentWord.getValue().equals(anotherWord.getValue())) {
													score = 1.0;
													break;
												}
											}
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								if (score == 0.0) {
									try {
										if (currentWord.getNeighbourWord() != null) 
										{
											for (Object[] w : currentWord.getNeighbourWord()) {
												ArrayList<JPWordIndex> indexes = document.getWordHashMap().get(w[0]);

												if (indexes != null) {
													for (JPWordIndex index : indexes) {
														JPWord anotherWord = document.getSentenceArray().get(index.getSentenceIndex()).getWords().get(index.getWordIndex());
														if(algorithmSettings.get("MatchIndex").equals(1)) {
															if (anotherWord.wordNetID.equals(w[1])) {
																score = (Double) w[2];
																break;
															}
														} else {
															if (w[0].equals(anotherWord.getValue())) {
																score = (Double) w[2];
																break;
															}
														}
													}		
												}
												
												if (score != 0.0) {
													break;
												}
											}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}

								}
								
								if (score>threshold) {
									sum+=score;
								}	
							}

							if (normalizeResult) {
								sum = normalizeResult(mainDocument, document, sum);
							}

							document.setScore(sum);
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
		int maxLength = Math.max(mainDoc.getNumberOfWords(), compareDoc.getNumberOfWords());
		
		return score/maxLength;
	}

}
