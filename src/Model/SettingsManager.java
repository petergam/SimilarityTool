package Model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * The Enum SettingsManager.
 * Loads the settings.xml file into a HashMap
 */
public enum SettingsManager {
    
    /** The Shared instance. */
    SharedInstance;
    
    /** The Constant PerlLibraryPathsKey. */
    public static final String PerlLibraryPathsKey = "PerlLibraryPathsKey";

    /** The settings HashMap. */
    private HashMap<String, Object> settings;
    
    /**
     * Instantiates a new settings manager.
     */
    private SettingsManager() {
    	setSettings(new HashMap<String, Object>());
    }
    
	/**
	 * Load settings from settings.xml
	 */
	public void loadSettings()  {		
		try {
	        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

			String path = "/settings.xml";
			InputStream is = this.getClass().getResourceAsStream(path); 
			
			Document doc = docBuilder.parse (is);
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

	/**
	 * Gets the settings.
	 *
	 * @return the settings
	 */
	public synchronized HashMap<String, Object> getSettings() {
		return settings;
	}

	/**
	 * Sets the settings.
	 *
	 * @param settings the settings
	 */
	public synchronized void setSettings(HashMap<String, Object> settings) {
		this.settings = settings;
	}
	
}
