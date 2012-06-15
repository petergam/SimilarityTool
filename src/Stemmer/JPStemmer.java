package Stemmer;

import Objects.JPDocument;

/**
 * The Interface JPStemmer.
 */
public interface JPStemmer {

	/**
	 * Stems a document using a stemmer
	 *
	 * @param document the document that should be stemmed
	 * @return the stemmed document
	 */
	public JPDocument stem(JPDocument document);
}
