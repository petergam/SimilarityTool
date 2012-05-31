package Model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gwt.dev.util.collect.HashMap;

public enum SettingsManager {
    SharedInstance;
    
    public static final String PerlLibraryPathsKey = "PerlLibraryPathsKey";

    private HashMap<String, Object> settings;
    
    private SettingsManager() {
    	setSettings(new HashMap<String, Object>());
    }
    
	public void loadSettings()  {
		try {
	        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			
			Document doc = docBuilder.parse (new File("settings.xml"));
	        doc.getDocumentElement ().normalize();

	        NodeList listOfLibraries = doc.getElementsByTagName("library_folder");
	        String[] paths = new String[listOfLibraries.getLength()];
	        
	        for(int s=0; s<listOfLibraries.getLength() ; s++){
	            Node node = listOfLibraries.item(s);	            
	            paths[s] = node.getAttributes().getNamedItem("path").getNodeValue(); 
	        }
	        
	        settings.put(PerlLibraryPathsKey, paths);
	        
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        
	}

	public synchronized HashMap<String, Object> getSettings() {
		return settings;
	}

	public synchronized void setSettings(HashMap<String, Object> settings) {
		this.settings = settings;
	}
	
}
