package Controller;

import java.io.FileNotFoundException;

import net.didion.jwnl.JWNLException;
import Algorithms.Algorithm;
import Model.ComputeSetup;
import Model.ComputeSetup.AlgorithmIndex;
import Model.ComputeSetup.StemmerType;
import Model.ComputeSetup.WordFilterType;
import Utilities.Log;
import View.MainFrame;
import View.MainFrame.MainFrameDelegate;
import WVToolExtension.JPWVTDocumentInfo;
import WVToolExtension.JPWVTool;
import edu.udo.cs.wvtool.config.WVTConfiguration;
import edu.udo.cs.wvtool.config.WVTConfigurationFact;
import edu.udo.cs.wvtool.generic.stemmer.AbstractStemmer;
import edu.udo.cs.wvtool.generic.wordfilter.AbstractStopWordFilter;
import edu.udo.cs.wvtool.main.WVTFileInputList;

public class MainController implements MainFrameDelegate {
    private JPWVTool wvt;
    
	public MainController() throws FileNotFoundException, JWNLException {		
		//startup GUI
		MainFrameDelegate delegate = this;
		MainFrame mainFrame = new MainFrame(delegate);
		
		//initialize GUI logger
		Log.setupLogger(mainFrame.getPanel());
		
		//initialize Word Vector Tool
		boolean shouldSkipErrors = false;
		this.wvt = new JPWVTool(shouldSkipErrors);
	}

	@Override
	public void computeButtonPressed(ComputeSetup setup) {
        WVTConfiguration config = new WVTConfiguration();
         
        //set filter
        AbstractStopWordFilter filter = setup.getFilter();
        config.setConfigurationRule(WVTConfiguration.STEP_WORDFILTER, new WVTConfigurationFact(filter));
        
        //set stemmer
        AbstractStemmer stemmer = setup.getStemmer();
        config.setConfigurationRule(WVTConfiguration.STEP_STEMMER, new WVTConfigurationFact(stemmer));

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
		
		wvt.loadFileInputList(list, config);
        
        
        Algorithm algorithm = setup.getAlgorithm();
        algorithm.compute(mainDocument, documents);
	}
}
