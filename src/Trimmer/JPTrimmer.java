package Trimmer;

import Objects.JPDocument;

/**
 * The Interface JPTrimmer.
 */
public interface JPTrimmer {
	
	/**
	 * Trim. Trims a document by removing information.
	 *
	 * @param document the document that should be trimmed
	 * @return the trimmed document
	 */
	public JPDocument trim(JPDocument document);
}
