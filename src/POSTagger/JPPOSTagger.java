package POSTagger;

import Objects.JPDocument;

// TODO: Auto-generated Javadoc
/**
 * The Interface JPPOSTagger.
 */
public interface JPPOSTagger {

	/**
	 * Tag.
	 *
	 * @param document the document
	 * @return the jP document
	 */
	public JPDocument tag(JPDocument document);
}
