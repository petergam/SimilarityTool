package Stemmer;

import Objects.JPDocument;

// TODO: Auto-generated Javadoc
/**
 * The Class JPStemmerDummy.
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
