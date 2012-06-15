package Loader;

import java.io.File;
import java.io.IOException;

import Objects.JPDocument;

/**
 * The Interface JPDocumentLoader.
 */
public interface JPDocumentLoader {

	/**
	 * Loads from a File to a JPDocument
	 *
	 * @param document the document
	 * @param file the file
	 * @return the loaded document
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public JPDocument load(JPDocument document, File file) throws IOException;
}
