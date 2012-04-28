import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import Controller.MainController;
import edu.udo.cs.wvtool.util.WVToolException;

public class Driver {

    private static String CONFIG_PATH = "Library/";
    private static String FILE_CONFIG_NAME = "file_properties.xml";
	
	public static void main(String[] args) throws WVToolException, FileNotFoundException, JWNLException {	
		if (isRequiredSoftwwareInstalled()) {
			//initialize Java WordNet Library
			JWNL.initialize(new FileInputStream(CONFIG_PATH + FILE_CONFIG_NAME));

			// start the main controller
			new MainController();
		}
	}
	
	private static boolean isRequiredSoftwwareInstalled() {
		boolean configFileExists = (new File(CONFIG_PATH + FILE_CONFIG_NAME)).exists();
		boolean wordNetInstalled = (System.getenv("WNHOME") == null) ? false : true;
		
		if(!configFileExists) throw new RuntimeException("Unable to locate WordNet properties file.");
		if(!wordNetInstalled) throw new RuntimeException("WordNet is not installed on computer or the PATH is not set.");

		return configFileExists && wordNetInstalled;
	}
}