package Controller;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import net.didion.jwnl.JWNLException;
import Model.ComputeSetup;
import Utilities.Log;
import View.MainFrame;
import View.MainFrame.MainFrameDelegate;
import WVToolAdditions.AbstractInclude;
import WVToolAdditions.ExtendedTokenizer;
import WVToolAdditions.JPLoadData;
import WVToolAdditions.JPWordLoaderCustom;
import WVToolAdditions.JPProgress.JPProgressDelegate;
import WVToolExtension.JPWVTConfiguration;
import WVToolExtension.JPWVTDocumentInfo;
import WVToolExtension.JPWVTool;
import edu.udo.cs.wvtool.config.WVTConfiguration;
import edu.udo.cs.wvtool.config.WVTConfigurationFact;
import edu.udo.cs.wvtool.generic.stemmer.AbstractStemmer;
import edu.udo.cs.wvtool.generic.wordfilter.AbstractStopWordFilter;
import edu.udo.cs.wvtool.main.WVTFileInputList;

public class MainController implements MainFrameDelegate, JPProgressDelegate {
    private JPWVTool wvt;
    private MainFrame mainFrame;
    private long startTime;
    
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
		startTime = System.currentTimeMillis();

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

        ExtendedTokenizer tokenizer = new ExtendedTokenizer();
        config.setConfigurationRule(WVTConfiguration.STEP_TOKENIZER, new WVTConfigurationFact(tokenizer));
        
        //for now only English is supported
		String language = "english";
		
		File mainDocumentFile = setup.getMainDocumentFile();
		File[] documentFiles = setup.getDocumentFiles();
		
		WVTFileInputList list = new WVTFileInputList(1+documentFiles.length);
		final JPWVTDocumentInfo[] documents = new JPWVTDocumentInfo[documentFiles.length];
		JPWVTDocumentInfo mainDocument = new JPWVTDocumentInfo(mainDocumentFile.getPath(),"txt","", language, 0);
		mainDocument.setDocumentTitle(mainDocumentFile.getName());
		list.addEntry(mainDocument);
		
		for (int i = 0; i < documents.length; i++) {
			JPWVTDocumentInfo currentDocument = new JPWVTDocumentInfo(documentFiles[i].getPath(),"txt","",language,0);
			currentDocument.setDocumentTitle(documentFiles[i].getName());
			list.addEntry(currentDocument);
			documents[i] = currentDocument;
		}
		
		JPLoadData loadData = new JPLoadData(list, mainDocument, documents, setup.getAlgorithm());
		wvt.loadFileInputList(loadData, config, this, new Runnable() {
			@Override
			public void run() {
				// find the best results
//				double maxScore = 0.0;
//				JPWVTDocumentInfo bestDocument = null;
//				for (JPWVTDocumentInfo document : documents) {
//					System.out.println("Here");
//					if (document.getScore()>maxScore) {
//						maxScore = document.getScore();
//						bestDocument = document;
//					}
//				}
//				
//				Log.nLog("Article with best score is: " + bestDocument.getDocumentTitle() + " score: " + bestDocument.getScore());
			}
		});
	}

	@Override
	public void didUpdateProgress(JPProgressType progressType, float percentDone) {		
		switch (progressType) {
		case JPProgressTypeWillLoadDocument:
//			Log.nLog("Starting to load document");
			break;
		case JPProgressTypeDidLoadDocument:
//			Log.nLog("Did finish loading document");
			break;
		case JPProgressTypeWillStartAlgorithm:
//			Log.nLog("Starting algorithm");
			break;
		case JPProgressTypeDidFinishAlgorithm:
//			Log.nLog("Did finish algorithm");
			
			break;
		default:
			break;
		}
		
		final int percentInt = (int)percentDone;
				
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.setProgress(percentInt);		
			}
		});
	}

	@Override
	public void didStartProgress() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.enableStopButton();				
			}
		});
	}

	@Override
	public void didStopProgress() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.disableStopButton();
				mainFrame.setProgress(0);				
			}
		});
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Running time: " + totalTime);
	}

	@Override
	public void stopButtonPressed() {
		wvt.stopLoad();		
	}
}
