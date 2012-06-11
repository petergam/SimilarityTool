package Loader;

import java.io.File;
import java.io.IOException;

import Objects.JPDocument;

// TODO: Auto-generated Javadoc
/**
 * The Interface JPDocumentLoader.
 */
public interface JPDocumentLoader {

	/**
	 * Load.
	 *
	 * @param document the document
	 * @param file the file
	 * @return the jP document
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public JPDocument load(JPDocument document, File file) throws IOException;
}
