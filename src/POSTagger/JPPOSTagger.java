package POSTagger;

import Objects.JPDocument;

/**
 * The Interface JPPOSTagger.
 */
public interface JPPOSTagger {

	/**
	 * Tag.Tags a document using a POS tagger
	 *
	 * @param document the document that should be POS tagged
	 * @return the POS tagged document
	 */
	public JPDocument tag(JPDocument document);
}
