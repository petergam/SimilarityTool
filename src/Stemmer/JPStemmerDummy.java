package Stemmer;

import Objects.JPDocument;

public class JPStemmerDummy extends JPAbstractStemmer {
	@Override
	public JPDocument stem(JPDocument document) {
		return document;
	}

}
