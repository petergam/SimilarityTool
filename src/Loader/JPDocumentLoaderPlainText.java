package Loader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Model.JPCache;
import Objects.JPDocument;
import Parser.JPStringParser;
import Utilities.Log;

public class JPDocumentLoaderPlainText extends JPAbstractDocumentLoader {

	public JPDocumentLoaderPlainText() {
		fileExtension = "txt";
	}
	
	@Override
	public JPDocument load(JPDocument document, File file) throws IOException {
		JPCache cache = new JPCache();
		
		JPDocument anotherDocument = cache.loadCachedDocument(file.getName());
		if (anotherDocument != null) {
			Log.nLog("Using cached " + anotherDocument.getDocumentTitle());
			return anotherDocument;
		}
		
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
        
        JPStringParser parser = new JPStringParser();
        
        document = parser.parse(result);
        document.setDocumentTitle(file.getName());
        return document;
	}
}
