package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import Objects.JPWordIndex;

/**
 * The Class TFIDFAlgorithm.
 */
public class TFIDFAlgorithm extends JPAbstractAlgorithm {

	
	/* (non-Javadoc)
	 * @see Algorithms.JPAbstractAlgorithm#compute(Objects.JPDocument, Objects.JPDocument[], boolean, java.lang.Runnable)
	 */
	public void compute(final JPDocument mainDocument, final JPDocument[] documents,
			boolean normalizeResult, final Runnable callbackDelegate) {

		Runnable backgroundRunnable = new Runnable() {
			@Override
			public void run() {
				double numberOfDocuments = documents.length;
				HashMap<String, Integer> totalFreq = new HashMap<String, Integer>();
				float[][] docFreqs;

				//Add each unique word of main doc to the hashmap.
				for (ArrayList<JPWordIndex> value : mainDocument.getWordHashMap().values()) {
				    JPWordIndex index = value.get(0);

				    JPWord word = mainDocument.getSentenceArray().get(index.getSentenceIndex()).getWords().get(index.getWordIndex());

				    if (word.isStopWord() == false) {
					    totalFreq.put((String) word.getValue(), 1);
					}
				}
				
				//Add all other unique words to the hash map, or increase already known values by 1 for documents containing the term.
				for (int i = 0; i < documents.length; i++) {
					JPDocument curDoc = documents[i];
					ArrayList<JPSentence> curDocSentences = curDoc.getSentenceArray();
					for (Map.Entry<String, ArrayList<JPWordIndex>> entry : curDoc.getWordHashMap().entrySet()) {
					    ArrayList<JPWordIndex> value = entry.getValue();
					    JPWordIndex index = value.get(0);
				    	JPSentence sentence = curDocSentences.get(index.getSentenceIndex());
					    JPWord word = sentence.getWords().get(index.getWordIndex());
					    if (word.isStopWord() == false) {
							try {
								totalFreq
										.put((String) word.getValue(), (Integer) totalFreq
												.get((String) word.getValue()) + 1);
							} catch (Exception e) {
								totalFreq.put((String) word.getValue(), 1);
							}
						}
					}
				}
				
				//Word frequencies in the documents.
				docFreqs = new float[documents.length+1][totalFreq.size()];
				
				for (int i = 0; i < documents.length; i++) {
					JPDocument curDoc = documents[i];
					
					progressDelegate.willStartAlgorithmForDocument(curDoc);

					Iterator<Entry<String, Integer>> it3 = totalFreq.entrySet()
							.iterator();
					int index = 0;

					while (it3.hasNext()) {
						Map.Entry pairs = (Map.Entry) it3.next();
						float weight = 0.0f;
						
						if (curDoc.getWordHashMap().get(pairs.getKey()) != null) {
							weight = 1.0f * curDoc.getWordHashMap().get(pairs.getKey()).size()
									/ curDoc.getNumberOfWords();
							weight *= Math.log(numberOfDocuments
									/ (totalFreq.get(pairs.getKey()) + 1));
						} else {
							weight = 0;
						}

						docFreqs[i][index] = weight;
						index++;
					}
				}


				Iterator<Entry<String, Integer>> it3 = totalFreq.entrySet().iterator();
				int index = 0;
				while (it3.hasNext()) {
					Map.Entry pairs = (Map.Entry) it3.next();
					float weight;
					try {
						weight = 1.0f
								* mainDocument.getWordHashMap().get(pairs.getKey()).size()
								/ mainDocument.getNumberOfWords();
						weight *= Math.log(numberOfDocuments
								/ totalFreq.get(pairs.getKey()));
					} catch (Exception e) {
						weight = 0;
					}

					docFreqs[(docFreqs.length - 1)][index] = weight;
					index++;
				}

				float[] mainDocFreqs = docFreqs[(docFreqs.length - 1)];

				double mainDocLength = vectorLength(mainDocFreqs);
				System.out.println("Docs: " + (docFreqs.length-1));
				
				for (int i = 0; i < docFreqs.length-1 ; i++) {
					float[] d = docFreqs[i];

					float dotProd = dotProd(mainDocFreqs, d);

					float dLength = vectorLength(d);

					double result = dotProd / (mainDocLength * dLength);

					JPDocument document = documents[i];
					document.setScore(result);
					progressDelegate.didFinishAlgorithmForDocument(document);

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

	/**
	 * Dot prod.
	 *
	 * @param a the a
	 * @param b the b
	 * @return the double
	 */
	private static float dotProd(float[] a, float[] b){
		float sum = 0.0f;
		for(int i = 0; i < a.length; i++){
			sum += a[i] * b[i];
		}
		return sum;
	}
	
	/**
	 * Vector length.
	 *
	 * @param a the a
	 * @return the double
	 */
	private static float vectorLength(float[] a){
		double sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += (a[i]*a[i]);
		}
		
		return (float) Math.sqrt(sum);
	}

	/* (non-Javadoc)
	 * @see Algorithms.JPAbstractAlgorithm#normalizeResult(double[])
	 */
	@Override
	public double normalizeResult(JPDocument mainDoc, JPDocument compareDoc, double score) {
		
		return 0.0;
	}

}
