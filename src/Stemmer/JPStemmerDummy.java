package Stemmer;

import Objects.JPDocument;

/**
 * The Class JPStemmerDummy.
 * Dummy class. Does no change anything
 */
public class JPStemmerDummy extends JPAbstractStemmer {
	
	/* (non-Javadoc)
	 * @see Stemmer.JPStemmer#stem(Objects.JPDocument)
	 */
	@Override
	public JPDocument stem(JPDocument document) {
		return document;
	}

}
