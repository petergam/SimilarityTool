package Loader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Model.JPCache;
import Objects.JPDocument;
import Parser.JPStringParser;
import Utilities.GUILog;

/**
 * The Class JPDocumentLoaderPlainText.
 */
public class JPDocumentLoaderPlainText extends JPAbstractDocumentLoader {

	/**
	 * Instantiates a new jP document loader plain text.
	 */
	public JPDocumentLoaderPlainText() {
		fileExtension = "txt";
	}
	
	/* (non-Javadoc)
	 * @see Loader.JPDocumentLoader#load(Objects.JPDocument, java.io.File)
	 */
	@Override
	public String load(File file) throws IOException {
        String result = null;
        DataInputStream in = null;

        try {
            byte[] buffer = new byte[(int) file.length()];
            in = new DataInputStream(new FileInputStream(file));
            in.readFully(buffer);
            result = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException("IO problem in fileToString", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) { /* ignore it */
            }
        }
        return result;

	}
}
