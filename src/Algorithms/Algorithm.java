package Algorithms;

import WVToolExtension.JPWVTDocumentInfo;

public abstract class Algorithm {

	public abstract double[] compute(JPWVTDocumentInfo mainDocument, JPWVTDocumentInfo[] documents, boolean normalizeResult);
	public double[] compute(JPWVTDocumentInfo mainDocument, JPWVTDocumentInfo[] documents) {
		return compute(mainDocument, documents, true);
	}
	public abstract double[] normalizeResult(double[] resultArray);
}
