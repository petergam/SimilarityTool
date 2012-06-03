import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import Controller.MainController;
import Model.SettingsManager;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.udo.cs.wvtool.util.WVToolException;

public class Driver {

    private static String CONFIG_PATH = "Library/";
    private static String FILE_CONFIG_NAME = "file_properties.xml";
    private static String SETTINGS_NAME = "settings.xml";

	public static void main(String[] args) throws WVToolException, FileNotFoundException, JWNLException {	
		if (isRequiredSoftwwareInstalled()) {
			//initialize Java WordNet Library
			JWNL.initialize(new FileInputStream(CONFIG_PATH + FILE_CONFIG_NAME));
			
			//load settings.xml
			SettingsManager.SharedInstance.loadSettings();

//			MaxentTagger tagger = null;
//			
//			try {
//				 tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				System.out.println("Tagger failed");
//			}
//			
//			
//			System.out.println("Test");
//			System.out.println(tagger.tagTokenizedString("This is a test"));
//			System.out.println(tagger.tagString("windows or running"));
//			List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new StringReader(""));
//			
//			for (List<HasWord> sentence : sentences) {
//				ArrayList<TaggedWord> taggedWords = tagger.tagSentence(sentence);
//				for(TaggedWord word : taggedWords){
//					System.out.println(word.tag());
//				}
//			}
//			
			// start the main controller
			new MainController();
			
			
			
//			JPWord word = new JPWord();
//			word.setWordType(JPWordType.JPWordTypeNoun);
//			word.setValue("dog");
//			word.setSenseIndex(1);
//			WordNetManager.SharedInstance.findHypernyms(word, 0);
//			for (JPWord w : word.getAllHypernyms()) {
//				System.out.println(w.getValue());
//			}
		}
	}
	
	private static boolean isRequiredSoftwwareInstalled() {
		boolean configFileExists = (new File(CONFIG_PATH + FILE_CONFIG_NAME)).exists();
		boolean settingsFileExists = (new File(SETTINGS_NAME)).exists();
		boolean wordNetInstalled = (System.getenv("WNHOME") == null) ? false : true;
		
		if(!configFileExists) throw new RuntimeException("Unable to locate WordNet properties file.");
		if(!settingsFileExists) throw new RuntimeException("Unable to locate settings.xml file.");
		if(!wordNetInstalled) throw new RuntimeException("WordNet is not installed on computer or the PATH is not set.");

		return configFileExists && wordNetInstalled;
	}
}