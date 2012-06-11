import java.io.File;
import java.io.FileNotFoundException;

import Controller.MainController;
import Model.SettingsManager;

// TODO: Auto-generated Javadoc
/**
 * The Class JPSimilarityTool.
 */
public class JPSimilarityTool {

    /** The settings name. */
    private static String SETTINGS_NAME = "settings.xml";

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws FileNotFoundException the file not found exception
	 */
	public static void main(String[] args) throws FileNotFoundException {	
		if (isRequiredSoftwwareInstalled()) {
			
			//load settings.xml
			SettingsManager.SharedInstance.loadSettings();

			// start the main controller
			new MainController();
		}
	}
	
	/**
	 * Checks if is required softwware installed.
	 *
	 * @return true, if is required softwware installed
	 */
	private static boolean isRequiredSoftwwareInstalled() {
		boolean settingsFileExists = (new File(SETTINGS_NAME)).exists();
		boolean wordNetInstalled = (System.getenv("WNHOME") == null) ? false : true;
		
		if(!settingsFileExists) throw new RuntimeException("Unable to locate settings.xml file.");
		if(!wordNetInstalled) throw new RuntimeException("WordNet is not installed on computer or the PATH is not set.");

		return settingsFileExists && wordNetInstalled;
	}
}