package Algorithms;

import Objects.JPDocument;

public class OnthologyBasedQueryAlgorithm extends JPAbstractAlgorithm {

	@Override
	public void compute(final JPDocument mainDocument, final JPDocument[] documents,
			boolean normalizeResult, final Runnable callbackDelegate) {
		
		Runnable backgroundRunnable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
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

	@Override
	public double[] normalizeResult(double[] resultArray) {
		return null;
	}

}
