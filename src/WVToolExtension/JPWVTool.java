package WVToolExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import Algorithms.Algorithm;
import WVToolExtension.JPProgress.JPProgressDelegate;

import edu.udo.cs.wvtool.config.WVTConfiguration;
import edu.udo.cs.wvtool.generic.charmapper.WVTCharConverter;
import edu.udo.cs.wvtool.generic.inputfilter.WVTInputFilter;
import edu.udo.cs.wvtool.generic.loader.WVTDocumentLoader;
import edu.udo.cs.wvtool.generic.stemmer.WVTStemmer;
import edu.udo.cs.wvtool.generic.tokenizer.WVTTokenizer;
import edu.udo.cs.wvtool.generic.wordfilter.WVTWordFilter;
import edu.udo.cs.wvtool.main.WVTool;
import edu.udo.cs.wvtool.util.WVToolException;

public class JPWVTool extends WVTool{

	static final int MAX_THREADS = 1;
	
	public JPWVTool(boolean arg0) {
		super(arg0);
	}
	
	public void loadFileInputList(final JPLoadData loadData, JPWVTConfiguration c, JPProgressDelegate callbackDelegate) {
		final JPProgress progress = new JPProgress(loadData.getDocuments().length+1, callbackDelegate);
		
        Iterator<?> inList = loadData.getFileInputList().getEntries();
        
		final ExecutorService engine = Executors.newFixedThreadPool(MAX_THREADS);
        
        // Go through the list
        while (inList.hasNext()) {

            final JPWVTDocumentInfo document = (JPWVTDocumentInfo) inList.next();
            final WVTConfiguration config = c; //.copy();
            
            engine.submit(new Runnable() {
				@Override
				public void run() {
			        SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progress.willLoadDocument();
						}
					});
			        
//					
			        WVTDocumentLoader loader = null;
			        WVTInputFilter infilter = null;
			        WVTCharConverter charConverter = null;
			        WVTTokenizer tokenizer = null;
			        WVTWordFilter wordFilter = null;
			        WVTStemmer stemmer = null;
					JPInclude include = null;
			        JPWordLoader wordLoader = null;
					
		            try {
		                loader = (WVTDocumentLoader) config.getComponentForStep(WVTConfiguration.STEP_LOADER, document);
		                infilter = (WVTInputFilter) config.getComponentForStep(WVTConfiguration.STEP_INPUT_FILTER, document);
		                charConverter = (WVTCharConverter) config.getComponentForStep(WVTConfiguration.STEP_CHAR_MAPPER, document);
		                tokenizer = (WVTTokenizer) config.getComponentForStep(WVTConfiguration.STEP_TOKENIZER, document);
		                wordFilter = (WVTWordFilter) config.getComponentForStep(WVTConfiguration.STEP_WORDFILTER, document);
		                stemmer = (WVTStemmer) config.getComponentForStep(WVTConfiguration.STEP_STEMMER, document);
		            	include = (JPInclude) config.getComponentForStep(JPWVTConfiguration.STEP_INCLUDE, document);
		                wordLoader = (JPWordLoader) config.getComponentForStep(JPWVTConfiguration.STEP_WORDLOADER, document);
		            	                
						include.include(wordLoader.load(stemmer.stem(wordFilter.filter(
								tokenizer.tokenize(
										charConverter.convertChars(
												infilter.convertToPlainText(
														loader.loadDocument(document), document), document),
														document), document), document), document), document);

						loader.close(document);
						
					} catch (WVToolException e) {

					}
		            
			        SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							System.out.println("Loadedddd " + document.sourceName);
							progress.didLoadDocument();
						}
					});
				}
			});
		}
        
		engine.shutdown();

		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					engine.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			        SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progress.willStartAlgorithm();
						}
					});
			        
					Algorithm algorithm = loadData.getAlgorithm();
					algorithm.compute(loadData.getMainDocument(), loadData.getDocuments());
			        
			        SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progress.didFinishAlgorithm();
						}
					});
					} catch (InterruptedException e) {
					  
					}				
			}
		}).start();
	}	
}
