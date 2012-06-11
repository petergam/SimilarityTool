package Model;

import java.io.File;
import java.util.concurrent.Callable;

import Objects.JPDocument;

// TODO: Auto-generated Javadoc
/**
 * The Class JPDocumentCallable.
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
		// TODO Auto-generated method stub
		return null;
	}
	

}
