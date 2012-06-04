package Algorithms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import Objects.JPDocument;
import Objects.JPProgress;

public abstract class Algorithm {

	public interface JPAlgorithmProgressDelegate {
		public void didUpdateProgress(float progress);
	}
	
	protected JPProgress progressDelegate;
	protected float percent = 0.0f;
	protected ExecutorService engine = Executors.newFixedThreadPool(6);
	private boolean shutdown = false;
	
	public abstract void compute(JPDocument mainDocument, JPDocument[] documents, boolean normalizeResult, Runnable callbackDelegate);
	public void compute(JPDocument mainDocument, JPDocument[] documents, Runnable callbackDelegate) {
		compute(mainDocument, documents, true, callbackDelegate);
	}
	public abstract double[] normalizeResult(double[] resultArray);
	
	public void setAlgorithmProgressDelegate(JPProgress progressDelegate) {
		this.progressDelegate = progressDelegate;
	}
	
	protected void theadUpdate() {
        if(shutdown){
            try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
        }
	}
	
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
	
	public void stop() {
		engine.shutdownNow();
		setShutdown(true);
	}
	
	private synchronized void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}
}
