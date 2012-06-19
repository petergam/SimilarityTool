package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import Algorithms.JPAbstractAlgorithm;
import Include.JPInclude;
import Loader.JPDocumentLoader;
import Model.JPConfiguration.IncludeNeighbourWordsType;
import Model.JPConfiguration.IncludeType;
import Objects.JPDocument;
import Objects.JPProgress;
import Objects.JPProgress.JPProgressDelegate;
import POSTagger.JPPOSTagger;
import Parser.JPStringParser;
import SenseRelate.JPSenseRelate;
import Stemmer.JPStemmer;
import Trimmer.JPTrimmer;

/**
 * The Class JPWordTool.
 * Allow loading and calculations to be performed. Handles everything concerning threads.
 */
public class JPWordTool {

	/** The engine. */
	private ExecutorService engine;
	
	/** The worker. */
	private SwingWorker<JPDocument, JPDocument> worker;
	
	/** The algorithm. */
	private JPAbstractAlgorithm algorithm;
	
	/** The progress. */
	private JPProgress progress;
	
	/** The Constant ThreadPoolSize. */
	private static final int ThreadPoolSize = 2;
	
	/**
	 * Run.
	 *
	 * @param config the config for the computations
	 * @param callbackDelegate delegate that will be notified about progress
	 * @param callbackRunnable runnable that will be run when everything is done
	 */
	public void run(final JPConfiguration config, final JPProgressDelegate callbackDelegate, final Runnable callbackRunnable) {
				
		engine = Executors.newFixedThreadPool(ThreadPoolSize);
		
		final File mainDocumentFile = config.getMainDocumentFile();
		final JPDocument[] documents = new JPDocument[config.getDocumentFiles().length];
		
		
		progress = new JPProgress(documents.length+1, callbackDelegate);
		progress.startProgress();

		
      worker = new SwingWorker<JPDocument, JPDocument>() {
			@Override
			protected JPDocument doInBackground() throws Exception {
								
				final JPDocumentCallable callable = new JPDocumentCallable() {
					@Override
					public JPDocument call() {
				        SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								progress.didStartLoadingDocument(document);
							}
						});
						
						JPDocumentLoader loader = config.getDocumentLoaderForFile(file);
						JPPOSTagger posTagger = config.getPOSTagger();
						JPSenseRelate senseRelate = config.getSenseRelate();
						JPStemmer stemmer = config.getStemmer();
						JPTrimmer trimmer = config.getTrimmer();
						IncludeType includeType = config.getIncludeType();
						IncludeNeighbourWordsType includeNeighbourWordsType = config.getIncludeNeighbourWordsType();
						ArrayList<JPInclude> includes = config.getIncludeTypes();
						
						JPDocument mainDocument = loadDocument(document,file, loader, posTagger, senseRelate, stemmer, trimmer, includes,includeType, includeNeighbourWordsType);
						
				        SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								progress.didLoadDocument(document);
							}
				        });
						return mainDocument;
					}
				};
				
				callable.file = mainDocumentFile;
				callable.document = new JPDocument();
				callable.document.setDocumentTitle(mainDocumentFile.getName());
				
		        SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						progress.willLoadDocument(callable.document);
					}
				});
				
				
				ArrayList<Future<JPDocument>> futures = new ArrayList<Future<JPDocument>>();
				Future<JPDocument> mainFuture = engine.submit(callable);
				
				
				int index = 0;
				for (File currentDocumentFile : config.getDocumentFiles()) {
					
					final JPDocumentCallable runnable2 = new JPDocumentCallable() {
						@Override
						public JPDocument call() {
					        SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									progress.didStartLoadingDocument(document);
								}
							});
							
							JPDocumentLoader loader = config.getDocumentLoaderForFile(file);
							JPPOSTagger posTagger = config.getPOSTagger();
							JPSenseRelate senseRelate = config.getSenseRelate();
							JPStemmer stemmer = config.getStemmer();
							JPTrimmer trimmer = config.getTrimmer();

							
							document = loadDocument(document,file, loader, posTagger, senseRelate, stemmer, trimmer);

					        SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									progress.didLoadDocument(document);
								}
					        });
							
							return document;
						}
					};
					
					JPDocument document = new JPDocument();
					
					runnable2.document = document;
					runnable2.file = currentDocumentFile;
					runnable2.document.setDocumentTitle(currentDocumentFile.getName());

			        SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progress.willLoadDocument(runnable2.document);
						}
					});
					
					documents[index]= document;
					
					futures.add(engine.submit(runnable2));
					
					index++;
										
				}
				
				JPDocument mainDocument = mainFuture.get();
				
				int i = 0;
				for (Future<JPDocument> future : futures) {
					documents[i]=future.get();
					i++;
				}
				
				engine.shutdown();
				engine.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
										
				return mainDocument;				
			}
			
			@Override
	        protected void done() {
				algorithm = config.getAlgorithm();
				try {
					JPDocument mainDocument = get();
					
			        SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progress.willStartAlgorithm();
						}
					});
					algorithm.setAlgorithmProgressDelegate(progress);
					algorithm.compute(mainDocument, documents, config.getNormalized(), new Runnable() {
						@Override
						public void run() {
					        SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									progress.didFinishAlgorithm();
									callbackRunnable.run();
								}
							});
					        
					        callbackRunnable.run();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	catch (ExecutionException e) {

					e.printStackTrace();
				} catch (CancellationException e) {
				}
			}
      };
		
      worker.execute();

	}
	
	/**
	 * Load document.
	 *
	 * @param document the document
	 * @param file the file
	 * @param loader the loader
	 * @param posTagger the POS tagger
	 * @param senseRelate the sense relate
	 * @param stemmer the stemmer
	 * @param trimmer the trimmer
	 * @param includes the includes
	 * @param includeType the include type
	 * @return the loaded document
	 */
	private JPDocument loadDocument(JPDocument document, File file, JPDocumentLoader loader, JPPOSTagger posTagger, JPSenseRelate senseRelate, JPStemmer stemmer, JPTrimmer trimmer, ArrayList<JPInclude> includes, IncludeType includeType, IncludeNeighbourWordsType includeNeighbourWordsType) {
		try {
	        JPStringParser parser = new JPStringParser();
	        document.setDocumentTitle(file.getName());

			document = trimmer.trim(stemmer.stem(senseRelate.senseRelate(posTagger.tag(parser.parse(document,loader.load(file))))));
			for (JPInclude include : includes) {
				include.include(document, includeType,includeNeighbourWordsType);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return document;
	}
	
	/**
	 * Load document.
	 *
	 * @param document the document
	 * @param file the file
	 * @param loader the loader
	 * @param posTagger the POS tagger
	 * @param senseRelate the sense relate
	 * @param stemmer the stemmer
	 * @param trimmer the trimmer
	 * @param includes the includes
	 * @param includeType the include type
	 * @return the loaded document
	 */
	private JPDocument loadDocument(JPDocument document, File file, JPDocumentLoader loader, JPPOSTagger posTagger, JPSenseRelate senseRelate, JPStemmer stemmer, JPTrimmer trimmer) {
		try {
	        JPStringParser parser = new JPStringParser();
	        document.setDocumentTitle(file.getName());

			document = trimmer.trim(stemmer.stem(senseRelate.senseRelate(posTagger.tag(parser.parse(document,loader.load(file))))));

			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return document;
	}
	
	/**
	 * Stop the calculations
	 */
	public void stop(){
		engine.shutdownNow();
		worker.cancel(true);

		algorithm.stop();
		progress.killProgress();
	}
}
