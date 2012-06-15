package SenseRelate;

import Objects.JPDocument;

/**
 * The Class JPSenseRelateDummy.
 * Dummy class. Does no change anything
 */
public class JPSenseRelateDummy extends JPAbstractSenseRelate {

	/* (non-Javadoc)
	 * @see SenseRelate.JPSenseRelate#senseRelate(Objects.JPDocument)
	 */
	@Override
	public JPDocument senseRelate(JPDocument document) {
		return document;
	}

}
