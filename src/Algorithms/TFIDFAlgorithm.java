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
				ArrayList<double[]> docFreqs;

//				Iterator<Entry<String, ArrayList<JPWordIndex>>> it = mainDocument.getWordHashMap()
//						.entrySet().iterator();

				for (ArrayList<JPWordIndex> value : mainDocument.getWordHashMap().values()) {
				    JPWordIndex index = value.get(0);

				    JPWord word = mainDocument.getSentenceArray().get(index.getSentenceIndex()).getWords().get(index.getWordIndex());

				    if (word.isStopWord() == false) {
					    totalFreq.put((String) word.getValue(), 1);

					}
				}


				for (int i = 0; i < documents.length; i++) {
					JPDocument curDoc = documents[i];

					
					
					for (Map.Entry<String, ArrayList<JPWordIndex>> entry : curDoc.getWordHashMap().entrySet()) {
					    String key = entry.getKey();
					    ArrayList<JPWordIndex> value = entry.getValue();

					    JPWordIndex index = value.get(0);

					    JPWord word = curDoc.getSentenceArray().get(index.getSentenceIndex()).getWords().get(index.getWordIndex());

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

				docFreqs = new ArrayList<double[]>();

				for (int i = 0; i < documents.length + 1; i++) {
					docFreqs.add(new double[totalFreq.size()]);
				}

				for (int i = 0; i < documents.length; i++) {
					JPDocument curDoc = documents[i];
					
					progressDelegate.willStartAlgorithmForDocument(curDoc);

					Iterator<Entry<String, Integer>> it3 = totalFreq.entrySet()
							.iterator();
					int index = 0;
					while (it3.hasNext()) {
						Map.Entry pairs = (Map.Entry) it3.next();
						double weight;
						try {
							weight = 1.0 * curDoc.getWordHashMap().get(pairs.getKey()).size()
									/ curDoc.getNumberOfWords();
							weight *= Math.log(numberOfDocuments
									/ (totalFreq.get(pairs.getKey()) + 1));
						} catch (Exception e) {
							weight = 0;
						}

						docFreqs.get(i)[index] = weight;
						index++;
					}
				}

				Iterator<Entry<String, Integer>> it3 = totalFreq.entrySet().iterator();
				int index = 0;
				while (it3.hasNext()) {
					Map.Entry pairs = (Map.Entry) it3.next();
					double weight;
					try {
						weight = 1.0
								* mainDocument.getWordHashMap().get(pairs.getKey()).size()
								/ mainDocument.getNumberOfWords();
						weight *= Math.log(numberOfDocuments
								/ totalFreq.get(pairs.getKey()));
					} catch (Exception e) {
						weight = 0;
					}

					docFreqs.get(docFreqs.size() - 1)[index] = weight;
					index++;
					;
				}

				double[] mainDocFreqs = docFreqs.get(docFreqs.size() - 1);

				double mainDocLength = vectorLength(mainDocFreqs);

				for (int i = 0; i < docFreqs.size() - 1; i++) {
					double[] d = docFreqs.get(i);

					double dotProd = dotProd(mainDocFreqs, d);

					double dLength = vectorLength(d);

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
	private static double dotProd(double[] a, double[] b){
		double sum = 0;
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
	private static double vectorLength(double[] a){
		double sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += (a[i]*a[i]);
		}
		
		return Math.sqrt(sum);
	}

	/* (non-Javadoc)
	 * @see Algorithms.JPAbstractAlgorithm#normalizeResult(double[])
	 */
	@Override
	public double[] normalizeResult(double[] resultArray) {
		return null;
	}

}
