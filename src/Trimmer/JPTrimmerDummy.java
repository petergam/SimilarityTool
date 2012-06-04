package Trimmer;

import Objects.JPDocument;

public class JPTrimmerDummy extends JPAbstractTrimmer {

	@Override
	public JPDocument trim(JPDocument document) {
		return document;
	}

}
