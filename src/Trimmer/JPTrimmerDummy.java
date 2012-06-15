package Trimmer;

import Objects.JPDocument;

/**
 * The Class JPTrimmerDummy.
 *  * Dummy class. Does no change anything
 */
public class JPTrimmerDummy extends JPAbstractTrimmer {

	/* (non-Javadoc)
	 * @see Trimmer.JPTrimmer#trim(Objects.JPDocument)
	 */
	@Override
	public JPDocument trim(JPDocument document) {
		return document;
	}

}
