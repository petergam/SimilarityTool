package POSTagger;

import Objects.JPDocument;

/**
 * The Class JPPOSTaggerDummy.
 * Dummy class. Does no change anything
 */
public class JPPOSTaggerDummy extends JPAbstractPOSTagger{

	/* (non-Javadoc)
	 * @see POSTagger.JPPOSTagger#tag(Objects.JPDocument)
	 */
	@Override
	public JPDocument tag(JPDocument document) {
		return document;
	}

}
