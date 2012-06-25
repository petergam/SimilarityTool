package Loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see Loader.JPDocumentLoader#load(Objects.JPDocument, java.io.File)
	 */
	@Override
	public String load(File file) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		try {

			BufferedReader i = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF8"));

			String str1;
			while ((str1 = i.readLine()) != null)
				stringBuilder.append(str1);
		} catch (UnsupportedEncodingException ue) {
		} catch (IOException e) {
		}
		
		return replaceJunk(stringBuilder.toString());
	}

	private String replaceJunk(String string) {
		string = string.replace('�', 'e');
		string = string.replace('�', 'a');
		string = string.replace('�', 'e');
		string = string.replace('�', 'a');
		
		return string;
	}
}
