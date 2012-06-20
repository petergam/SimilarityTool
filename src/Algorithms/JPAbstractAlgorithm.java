package Algorithms;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import Objects.JPDocument;
import Objects.JPProgress;

/**
 * The Class JPAbstractAlgorithm.
 * Abstract class the represents an algorithm.
 * All algorithms should extend this class.
 */
public abstract class JPAbstractAlgorithm {

	/** The Constant ThreadPoolSize. The number of threads available */
	private static final int ThreadPoolSize = 6;

	
	/**
	 * The Interface JPAlgorithmProgressDelegate. Updates a delegate with progress from 0-100.
	 */
	public interface JPAlgorithmProgressDelegate {
		
		/**
		 * Did update progress.
		 *
		 * @param the new progress value (0-100).
		 */
		public void didUpdateProgress(float progress);
	}
	
	/** The progress delegate. */
	protected JPProgress progressDelegate;
	
	/** The current percent loaded */
	protected float percent = 0.0f;
	
	/** The engine used to control threads. */
	protected ExecutorService engine = Executors.newFixedThreadPool(ThreadPoolSize);
	
	/** Indicating whenever the computations has been shutdown. */
	private boolean shutdown = false;
	
	/**
	 * Compute.
	 *
	 * @param mainDocument the main document
	 * @param documents the other documents
	 * @param algorithmSettings 
	 * @param normalizeResult whenever the result should be normalized
	 * @param callbackDelegate the callback delegate
	 */
	public abstract void compute(JPDocument mainDocument, JPDocument[] documents, HashMap<String, Object> algorithmSettings, boolean normalizeResult, Runnable callbackDelegate);
	
	/**
	 * Compute.
	 *
	 * @param mainDocument the main document
	 * @param documents the other documents
	 * @param callbackDelegate the callback delegate
	 */
	public void compute(JPDocument mainDocument, JPDocument[] documents, HashMap<String, Object> algorithmSettings,  Runnable callbackDelegate) {
		compute(mainDocument, documents, algorithmSettings, true, callbackDelegate);
	}
	
	/**
	 * Normalize result.
	 * Normalizes the result so it is between 0.0 and 1.0
	 *
	 * @param resultArray the results that should be normalized
	 * @return the normalized result
	 */
	protected abstract double normalizeResult(JPDocument mainDoc, JPDocument compareDoc, double score);
	
	/**
	 * Sets the algorithm progress delegate.
	 *
	 * @param progressDelegate the new algorithm progress delegate
	 */
	public void setAlgorithmProgressDelegate(JPProgress progressDelegate) {
		this.progressDelegate = progressDelegate;
	}
	
	/**
	 * Should be called once in a while if a task is performed that block the thread for a long time in order to support shutdowns.
	 */
	protected void theadUpdate() {
        if(shutdown){
            try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
        }
	}
	
	/**
	 * Call delegate.
	 *
	 * @param callbackDelegate the callback delegate
	 */
	protected void callDelegate(final Runnable callbackDelegate) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					engine.shutdown();
					engine.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

			        SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							callbackDelegate.run();
						}
					});
					} catch (InterruptedException e) {
					}				
			}
		}).start();
	}
	
	/**
	 * Run makes sure everything runs on seperate threads.
	 *
	 * @param backgroundRunnable will be run on a background thread
	 * @param doneRunnable will be run after the background thread has finished
	 */
	protected void run(final Runnable backgroundRunnable, final Runnable doneRunnable) {	
		SwingWorker<Integer, Integer> worker = new SwingWorker<Integer, Integer>() {
			protected Integer doInBackground() throws Exception {
				backgroundRunnable.run();
				
				return null;
			}
			
			protected void done() {
				doneRunnable.run();
			}
		};
		
		worker.execute();	
	}
	
	/**
	 * Stops the algorithm from running
	 */
	public void stop() {
		engine.shutdownNow();
		setShutdown(true);
	}
	
	/**
	 * Shuts down the calculations
	 *
	 * @param whenever or not to shutdown.
	 */
	private synchronized void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}
}
