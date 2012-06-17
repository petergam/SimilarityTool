package Driver;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import Controller.MainController;
import Model.JPCache;
import Model.SettingsManager;

/**
 * The Class JPSimilarityTool.
 */
public class JPSimilarityTool {


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws FileNotFoundException the file not found exception
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws FileNotFoundException, URISyntaxException, MalformedURLException {					
		if (isRequiredSoftwwareInstalled()) {
			//load settings.xml
			SettingsManager.SharedInstance.loadSettings();
			
			//start cache
			JPCache.SharedCache.loadCache(JPCache.sDefaultCacheName);

			// start the main controller
			new MainController();
		}
	}
	
	/**
	 * Checks if is required softwware installed.
	 * Required software is WordNet and the settings.xml file
	 *
	 * @return true, if is required softwware installed
	 */
	private static boolean isRequiredSoftwwareInstalled() {
		boolean wordNetInstalled = (System.getenv("WNHOME") == null) ? false : true;
		if(!wordNetInstalled) throw new RuntimeException("WordNet is not installed on computer or the PATH is not set.");

		return wordNetInstalled;

	}
}