package Loader;

import java.io.File;
import java.io.IOException;

import Objects.JPDocument;

public interface JPDocumentLoader {

	public JPDocument load(JPDocument document, File file) throws IOException;
}
