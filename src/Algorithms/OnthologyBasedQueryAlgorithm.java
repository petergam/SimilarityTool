package Algorithms;

import Objects.JPDocument;

/**
 * The Class OnthologyBasedQueryAlgorithm.
 * Implements the Onthology Based Query algorithm
 */
public class OnthologyBasedQueryAlgorithm extends JPAbstractAlgorithm {

	/* (non-Javadoc)
	 * @see Algorithms.JPAbstractAlgorithm#compute(Objects.JPDocument, Objects.JPDocument[], boolean, java.lang.Runnable)
	 */
	@Override
	public void compute(final JPDocument mainDocument, final JPDocument[] documents,
			boolean normalizeResult, final Runnable callbackDelegate) {
		
		Runnable backgroundRunnable = new Runnable() {
			@Override
			public void run() {
				
			}
		};
		
		Runnable doneRunnable = new Runnable() {
			@Override
			public void run() {
				callDelegate(callbackDelegate);
			}
		};
		
		run(backgroundRunnable, doneRunnable);
	}

	/* (non-Javadoc)
	 * @see Algorithms.JPAbstractAlgorithm#normalizeResult(double[])
	 */
	@Override
	public double[] normalizeResult(double[] resultArray) {
		return null;
	}

}
