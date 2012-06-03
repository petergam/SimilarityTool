package Algorithms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import WVToolAdditions.JPProgress;
import WVToolExtension.JPWVTDocumentInfo;
import WVToolExtension.JPWVTool;

public abstract class Algorithm {

	public interface JPAlgorithmProgressDelegate {
		public void didUpdateProgress(float progress);
	}
	
	protected JPProgress progressDelegate;
	protected float percent = 0.0f;
	protected ExecutorService engine = Executors.newFixedThreadPool(JPWVTool.MAX_THREADS);
	
	public abstract void compute(JPWVTDocumentInfo mainDocument, JPWVTDocumentInfo[] documents, boolean normalizeResult, Runnable callbackDelegate);
	public void compute(JPWVTDocumentInfo mainDocument, JPWVTDocumentInfo[] documents, Runnable callbackDelegate) {
		compute(mainDocument, documents, true, callbackDelegate);
	}
	public abstract double[] normalizeResult(double[] resultArray);
	
	public void setAlgorithmProgressDelegate(JPProgress progressDelegate) {
		this.progressDelegate = progressDelegate;
	}
	
//	protected void didProgress(float updatePercent) {
//		percent +=updatePercent;
//		progressDelegate.didUpdateProgress(percent);		
//	}
	
	protected void theadUpdate() {
        if(Thread.interrupted()){
            try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	protected void callDelegate(final Runnable callbackDelegate) {
		engine.shutdown();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
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
}
