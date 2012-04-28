package Algorithms;

import Utilities.UtilMethods;
import WVToolExtension.JPWVTDocumentInfo;

public class LevenshteinDistanceAlgorithm extends Algorithm {

	@Override
	//http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
	public double[] compute(JPWVTDocumentInfo mainDocument,
			JPWVTDocumentInfo[] documents, boolean normalizeResult) {

		int mainDocumentSize = mainDocument.getWordsArrayList().size();
		double[] resultArray = new double[documents.length];

		for (int documentIndex = 0; documentIndex < documents.length; documentIndex++) {
			JPWVTDocumentInfo currentDocument = documents[documentIndex];
			int currentDocumentSize = currentDocument.getWordsArrayList().size();

			int[][] distance = new int[mainDocumentSize + 1][currentDocumentSize + 1];

			for (int i = 0; i <= mainDocumentSize; i++)
				distance[i][0] = i;
			for (int j = 0; j <= currentDocumentSize; j++)
				distance[0][j] = j;

			for (int i = 1; i <= mainDocumentSize; i++)
				for (int j = 1; j <= currentDocumentSize; j++)
					distance[i][j] = UtilMethods.minimum(
							distance[i - 1][j] + 1,   //deletion
							distance[i][j - 1] + 1,   //insertion
							distance[i - 1][j - 1] + ((mainDocument.getWordsArrayList().get(i - 1).equals(currentDocument.getWordsArrayList().get(j - 1))) ? 0 : 1)); //substitution
		
			resultArray[documentIndex] = distance[mainDocumentSize][currentDocumentSize];
		
		}
		
		if (normalizeResult) {
			return normalizeResult(resultArray);
		} else {
			return resultArray;
		}
	}

	@Override
	public double[] normalizeResult(double[] resultArray) {
		// TODO Auto-generated method stub
		return null;
	}
}