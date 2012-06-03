package WVToolExtension;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import Algorithms.Algorithm;
import Algorithms.Algorithm.JPAlgorithmProgressDelegate;
import Model.DocumentRunnable;
import WVToolAdditions.ExtendedTokenizer;
import WVToolAdditions.JPInclude;
import WVToolAdditions.JPLoadData;
import WVToolAdditions.JPProgress;
import WVToolAdditions.JPProgress.JPProgressDelegate;
import WVToolAdditions.JPWordLoader;
import edu.udo.cs.wvtool.config.WVTConfiguration;
import edu.udo.cs.wvtool.generic.charmapper.WVTCharConverter;
import edu.udo.cs.wvtool.generic.inputfilter.WVTInputFilter;
import edu.udo.cs.wvtool.generic.loader.WVTDocumentLoader;
import edu.udo.cs.wvtool.generic.stemmer.WVTStemmer;
import edu.udo.cs.wvtool.generic.tokenizer.WVTTokenizer;
import edu.udo.cs.wvtool.generic.wordfilter.WVTWordFilter;
import edu.udo.cs.wvtool.main.WVTool;
import edu.udo.cs.wvtool.util.WVToolException;

public class JPWVTool extends WVTool implements JPAlgorithmProgressDelegate {

	public static final int MAX_THREADS = 10;
	JPProgress progress;
	ExecutorService engine;
	Thread loadAlgorithmThread;
	
	public JPWVTool(boolean arg0) {
		super(arg0);
	}
	
	public void loadFileInputList(final JPLoadData loadData, final JPWVTConfiguration c, JPProgressDelegate callbackDelegate, final Runnable callbackRunnable) {		
		progress = new JPProgress(loadData.getDocuments().length+1, callbackDelegate);
		
        final Iterator<?> inList = loadData.getFileInputList().getEntries();
		engine = Executors.newFixedThreadPool(1);
		
		progress.startProgress();
		
		
        SwingWorker<Integer, Integer> worker = new SwingWorker<Integer, Integer>() {
			@Override
			protected Integer doInBackground() throws Exception {
	            final WVTConfiguration config = c; //.copy();

				while (inList.hasNext()) {

					final JPWVTDocumentInfo d = (JPWVTDocumentInfo) inList.next();
					
			        SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progress.willLoadDocument(d);
						}
					});
			        
					
				DocumentRunnable runnable = new DocumentRunnable() {
					public void run() {
				        SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								progress.didStartLoadingDocument(document);
							}
						});
				        
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
//			                tokenizer = (WVTTokenizer) config.getComponentForStep(WVTConfiguration.STEP_TOKENIZER, document);
			                wordFilter = (WVTWordFilter) config.getComponentForStep(WVTConfiguration.STEP_WORDFILTER, document);
			                stemmer = (WVTStemmer) config.getComponentForStep(WVTConfiguration.STEP_STEMMER, document);
			            	include = (JPInclude) config.getComponentForStep(JPWVTConfiguration.STEP_INCLUDE, document);
			                wordLoader = (JPWordLoader) config.getComponentForStep(JPWVTConfiguration.STEP_WORDLOADER, document);
			                tokenizer = new ExtendedTokenizer();
			                
							include.include(wordLoader.load(stemmer.stem(wordFilter.filter(
									tokenizer.tokenize(
											charConverter.convertChars(
													infilter.convertToPlainText(
															loader.loadDocument(document), document), document),
															document), document), document), document), document);

							loader.close(document);
							
					        SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									progress.didLoadDocument(document);
								}
					        });
							
						} catch (WVToolException e) {


					}
		            

//			            
//				        SwingUtilities.invokeLater(new Runnable() {
//							@Override
//							public void run() {
//								progress.didLoadDocument();
//							}
//						});
					}
				};
					runnable.document = d;
					engine.submit(runnable);
				}
				
				return null;
			}
			
			@Override
	        protected void done() {
				loadAlgorithmThread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							engine.shutdown();
							engine.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
					        SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									progress.willStartAlgorithm();
								}
							});
					        
							Algorithm algorithm = loadData.getAlgorithm();
							algorithm.setAlgorithmProgressDelegate(progress);
							algorithm.compute(loadData.getMainDocument(), loadData.getDocuments(), new Runnable() {
								@Override
								public void run() {
							        SwingUtilities.invokeLater(new Runnable() {
										@Override
										public void run() {
											progress.didFinishAlgorithm();
											callbackRunnable.run();
										}
									});
								}
							});
							

							} catch (InterruptedException e) {
							}				
					}
				});
				loadAlgorithmThread.start();
				
	        }
		};
		
		worker.execute();		
	}	
	
	public void stopLoad() {
		engine.shutdownNow();
		if (loadAlgorithmThread!=null) {
			loadAlgorithmThread.interrupt();
		}
	}

	@Override
	public void didUpdateProgress(float percent) {
//		int finalPercent = (int) (percent/4)*3;
//		progress.didUpdateProcess(finalPercent);
	}
}
