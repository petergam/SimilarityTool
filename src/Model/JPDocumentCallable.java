package Model;

import java.io.File;
import java.util.concurrent.Callable;

import Objects.JPDocument;

/**
 * The Class JPDocumentCallable.
 * Callable that can hold a document and a file object
 */
public class JPDocumentCallable implements Callable<JPDocument> {
	
	/** The document. */
	public JPDocument document;
	
	/** The file. */
	public File file;
	
	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public JPDocument call() throws Exception {
		return null;
	}
	

}
