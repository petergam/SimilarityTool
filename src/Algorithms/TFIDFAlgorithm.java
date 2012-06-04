package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.vecmath.Vector4d;

import WVToolExtension.JPWVTDocumentInfo;

public class TFIDFAlgorithm extends Algorithm {

	HashMap<String, Integer> totalFreq = new HashMap<String, Integer>();
	double D;
	ArrayList<double[]> docFreqs;
	
	public void compute(JPWVTDocumentInfo mainDocument,
			JPWVTDocumentInfo[] documents, boolean normalizeResult,
			Runnable callbackDelegate) {

		D = documents.length;
		
		Iterator<Entry<String, Integer>> it = mainDocument.getWordHashMap()
				.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			totalFreq.put(
					(String) pairs.getKey(), 1);
			// it.remove(); // avoids a ConcurrentModificationException
		}

		for (int i = 0; i < documents.length; i++) {
			JPWVTDocumentInfo curDoc = documents[i];
			Iterator<Entry<String, Integer>> it2 = curDoc.getWordHashMap()
					.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry pairs = (Map.Entry) it2.next();
				try {
					totalFreq.put((String) pairs.getKey(),
							(Integer) totalFreq.get((String) pairs.getKey())
									+ 1);

				} catch (Exception e) {
					totalFreq.put((String) pairs.getKey(), 1);
				}
				// it2.remove(); // avoids a ConcurrentModificationException
			}
		}
		
		docFreqs = new ArrayList<double[]>();
		
		for (int i = 0; i < documents.length+1; i++) {
			docFreqs.add(new double[totalFreq.size()]);
		}
		
		for (int i = 0; i < documents.length; i++) {
			JPWVTDocumentInfo curDoc = documents[i];
			Iterator<Entry<String, Integer>> it3 = totalFreq
					.entrySet().iterator();
			int index = 0;
			while (it3.hasNext()) {
				Map.Entry pairs = (Map.Entry) it3.next();
				double weight;
				try {
					weight = 1.0 * curDoc.getWordHashMap().get(pairs.getKey()) / curDoc.getNumberOfWords();
					weight *= Math.log(D / (totalFreq.get(pairs.getKey())+1));
				} catch (Exception e) {
					weight = 0;
				}
				
				docFreqs.get(i)[index] = weight;
				index++;
			}
		}
		
		Iterator<Entry<String, Integer>> it3 = totalFreq
				.entrySet().iterator();
		int index = 0;
		while (it3.hasNext()) {
			Map.Entry pairs = (Map.Entry) it3.next();
			double weight;
			try {
				weight = 1.0 * mainDocument.getWordHashMap().get(pairs.getKey()) / mainDocument.getNumberOfWords();
				weight *= Math.log(D / totalFreq.get(pairs.getKey()));
			} catch (Exception e) {
				weight = 0;
			}
			
			docFreqs.get(docFreqs.size()-1)[index] = weight;
			index++;;
		}

		double[] mainDocFreqs = docFreqs.get(docFreqs.size()-1);
		
		double mainDocLength = vectorLength(mainDocFreqs);

		for (int i = 0; i < docFreqs.size()-1; i++) {
			double[] d = docFreqs.get(i);
			
			double dotProd = dotProd(mainDocFreqs, d);

			double dLength = vectorLength(d);
			
			double result = dotProd / (mainDocLength*dLength);
			System.out.println("Result " + result);
			
			JPWVTDocumentInfo document = documents[i];
			document.setScore(result);
			progressDelegate.didFinishAlgorithmForDocument(document);
		}
		
			callbackDelegate.run();
		
	}
	
	public static double dotProd(double[] a, double[] b){
		if(a.length != b.length){
			throw new IllegalArgumentException("The dimensions have to be equal!");
		}
		double sum = 0;
		for(int i = 0; i < a.length; i++){
			sum += a[i] * b[i];
		}
		return sum;
	}
	
	public static double vectorLength(double[] a){
		double sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += (a[i]*a[i]);
		}
		
		return Math.sqrt(sum);
	}

	@Override
	public double[] normalizeResult(double[] resultArray) {
		// TODO Auto-generated method stub
		return null;
	}

}
