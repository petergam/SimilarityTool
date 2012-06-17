package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import Objects.JPDocument;

/**
 * The Class JPCache.
 * Cache a document on disk.
 */
public class JPCache {
	
	/**
	 * Cache a document on disk
	 *
	 * @param document the document that should be cached on disk
	 */
	public void cacheDocument(JPDocument document) {

		try {
			FileOutputStream fos = new FileOutputStream("cache/");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(document); 
			oos.flush(); 
			oos.close(); 
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} 
	}
	
	/**
	 * Load cached document.
	 *
	 * @param fileName the file name of the document
	 * @return the cached document (return null if not found)
	 */
	public JPDocument loadCachedDocument(String fileName) {
		
		try {
			FileInputStream fis = new FileInputStream("cache/" + fileName + ".jp");
			ObjectInputStream ois = new ObjectInputStream(fis); 
			JPDocument document = (JPDocument)ois.readObject(); 
			ois.close(); 
			
			return document;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} 

		
		return null;
	}
	
	/**
	 * Clear the cache.
	 */
	public void clear() {
		try {
			URL url = this.getClass().getResource("/Cache/");
			FileUtils.cleanDirectory(new File("cache"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
