package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.FileUtils;

import Objects.JPDocument;

public class JPCache {
	
	public void cacheDocument(JPDocument document) {

		try {
			FileOutputStream fos = new FileOutputStream("cache/" + document.getDocumentTitle() + ".jp");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(document); 
			oos.flush(); 
			oos.close(); 
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} 
	}
	
	public JPDocument loadCachedDocument(String fileName) {
		
		try {
			FileInputStream fis = new FileInputStream("cache/"+ fileName + ".jp");
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
	
	public void clear() {
		try {
			FileUtils.cleanDirectory(new File("cache"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
