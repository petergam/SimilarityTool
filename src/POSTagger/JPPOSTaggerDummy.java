package POSTagger;

import Objects.JPDocument;

// TODO: Auto-generated Javadoc
/**
 * The Class JPPOSTaggerDummy.
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
