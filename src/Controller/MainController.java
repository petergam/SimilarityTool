package Controller;

import java.io.FileNotFoundException;

import net.didion.jwnl.JWNLException;
import Model.ComputeSetup;
import Utilities.Log;
import View.MainFrame;
import View.MainFrame.MainFrameDelegate;
import WVToolExtension.AbstractInclude;
import WVToolExtension.JPLoadData;
import WVToolExtension.JPProgress.JPProgressDelegate;
import WVToolExtension.JPWVTConfiguration;
import WVToolExtension.JPWVTDocumentInfo;
import WVToolExtension.JPWVTool;
import WVToolExtension.JPWordLoaderCustom;
import edu.udo.cs.wvtool.config.WVTConfiguration;
import edu.udo.cs.wvtool.config.WVTConfigurationFact;
import edu.udo.cs.wvtool.generic.stemmer.AbstractStemmer;
import edu.udo.cs.wvtool.generic.wordfilter.AbstractStopWordFilter;
import edu.udo.cs.wvtool.main.WVTFileInputList;

public class MainController implements MainFrameDelegate, JPProgressDelegate {
    private JPWVTool wvt;
    private MainFrame mainFrame;
    
	public MainController() throws FileNotFoundException, JWNLException {		
		//startup GUI
		MainFrameDelegate delegate = this;
		mainFrame = new MainFrame(delegate);
		
		//initialize GUI logger
		Log.setupLogger(mainFrame.getPanel());
		
		//initialize Word Vector Tool
		boolean shouldSkipErrors = false;
		this.wvt = new JPWVTool(shouldSkipErrors);
	}

	@Override
	public void computeButtonPressed(ComputeSetup setup) {
        JPWVTConfiguration config = new JPWVTConfiguration();
         
    	config.setConfigurationRule(JPWVTConfiguration.STEP_WORDLOADER, new WVTConfigurationFact(new JPWordLoaderCustom()));
        
        //set filter
        AbstractStopWordFilter filter = setup.getFilter();
        config.setConfigurationRule(WVTConfiguration.STEP_WORDFILTER, new WVTConfigurationFact(filter));
        
        //set stemmer
        AbstractStemmer stemmer = setup.getStemmer();
        config.setConfigurationRule(WVTConfiguration.STEP_STEMMER, new WVTConfigurationFact(stemmer));

        AbstractInclude include = setup.getInclude();
        config.setConfigurationRule(JPWVTConfiguration.STEP_INCLUDE, new WVTConfigurationFact(include));

        //for now only English is supported
		String language = "english";

		String mainDocumentPath = setup.getMainDocumentPath();
		String[] documentsPath = setup.getDocumentPaths();
		WVTFileInputList list = new WVTFileInputList(1+documentsPath.length);
		JPWVTDocumentInfo[] documents = new JPWVTDocumentInfo[documentsPath.length];
		JPWVTDocumentInfo mainDocument = new JPWVTDocumentInfo(mainDocumentPath,"txt","", language, 0);
		list.addEntry(mainDocument);
		
		for (int i = 0; i < documents.length; i++) {
			JPWVTDocumentInfo currentDocument = new JPWVTDocumentInfo(documentsPath[i],"txt","",language,0);
			list.addEntry(currentDocument);
			documents[i] = currentDocument;
		}
		
		JPLoadData loadData = new JPLoadData(list, mainDocument, documents, setup.getAlgorithm());
		wvt.loadFileInputList(loadData, config, this);
	}

	@Override
	public void didUpdateProgress(JPProgressType progressType, float percentDone) {		
		switch (progressType) {
		case JPProgressTypeWillLoadDocument:
//			Log.nLog("Starting to load document");
			break;
		case JPProgressTypeDidLoadDocument:
			
//			mainFrame.model.setValueAt("Loaded", row, column);
			Log.nLog("Did finish loading document");
			break;
		case JPProgressTypeWillStartAlgorithm:
			Log.nLog("Starting algorithm");
			break;
		case JPProgressTypeDidFinishAlgorithm:
//			Log.nLog("Did finish algorithm");
			
			break;
		default:
			break;
		}
		
		int percentInt = (int)percentDone;
		mainFrame.setProgress(percentInt);		
	}
}
