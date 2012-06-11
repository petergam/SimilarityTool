package Algorithms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import Objects.JPDocument;
import Objects.JPProgress;

// TODO: Auto-generated Javadoc
/**
 * The Class JPAbstractAlgorithm.
 */
public abstract class JPAbstractAlgorithm {

	/** The Constant ThreadPoolSize. */
	private static final int ThreadPoolSize = 6;

	
	/**
	 * The Interface JPAlgorithmProgressDelegate.
	 */
	public interface JPAlgorithmProgressDelegate {
		
		/**
		 * Did update progress.
		 *
		 * @param progress the progress
		 */
		public void didUpdateProgress(float progress);
	}
	
	/** The progress delegate. */
	protected JPProgress progressDelegate;
	
	/** The percent. */
	protected float percent = 0.0f;
	
	/** The engine. */
	protected ExecutorService engine = Executors.newFixedThreadPool(ThreadPoolSize);
	
	/** The shutdown. */
	private boolean shutdown = false;
	
	/**
	 * Compute.
	 *
	 * @param mainDocument the main document
	 * @param documents the documents
	 * @param normalizeResult the normalize result
	 * @param callbackDelegate the callback delegate
	 */
	public abstract void compute(JPDocument mainDocument, JPDocument[] documents, boolean normalizeResult, Runnable callbackDelegate);
	
	/**
	 * Compute.
	 *
	 * @param mainDocument the main document
	 * @param documents the documents
	 * @param callbackDelegate the callback delegate
	 */
	public void compute(JPDocument mainDocument, JPDocument[] documents, Runnable callbackDelegate) {
		compute(mainDocument, documents, true, callbackDelegate);
	}
	
	/**
	 * Normalize result.
	 *
	 * @param resultArray the result array
	 * @return the double[]
	 */
	public abstract double[] normalizeResult(double[] resultArray);
	
	/**
	 * Sets the algorithm progress delegate.
	 *
	 * @param progressDelegate the new algorithm progress delegate
	 */
	public void setAlgorithmProgressDelegate(JPProgress progressDelegate) {
		this.progressDelegate = progressDelegate;
	}
	
	/**
	 * Thead update.
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
	 * Run.
	 *
	 * @param backgroundRunnable the background runnable
	 * @param doneRunnable the done runnable
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
	 * Stop.
	 */
	public void stop() {
		engine.shutdownNow();
		setShutdown(true);
	}
	
	/**
	 * Sets the shutdown.
	 *
	 * @param shutdown the new shutdown
	 */
	private synchronized void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}
}
