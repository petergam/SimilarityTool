package POSTagger;

import Objects.JPDocument;

public class JPPOSTaggerDummy extends JPAbstractPOSTagger{

	@Override
	public JPDocument tag(JPDocument document) {
		return document;
	}

}
