package Objects;

import Objects.JPDocument.JPDocumentProgressType;
import Objects.JPProgress.JPProgressDelegate.JPProgressType;

public class JPProgress {
	
	public interface JPProgressDelegate {
		
		public enum JPProgressType {
			JPProgressTypeWillLoadDocument,
			JPProgressTypeDidLoadDocument,
			JPProgressTypeDidStartLoadingDocument,
			JPProgressTypeDidProgress,
			JPProgressTypeWillStartAlgorithm,
			JPProgressTypeDidFinishAlgorithm,
			JPProgressTypeDidFinishAlgorithmForDocument,
			JPProgressTypeWillStartAlgorithmForDocument,
		}
		
		public void didStartProgress();
		public void didStopProgress();
		public void didKillProgress();
		public void didUpdateProgress(JPProgressType progressType, float percentDone, JPDocument document);
	}
	
	private boolean running = false;
	private int numberOfDocuments;
	private int numberOfLoadedDocuments;

	private JPProgressDelegate callbackDelegate;
	private float currentPercent;
	
	public synchronized int getNumberOfDocuments() {
		return numberOfDocuments;
	}


	public synchronized void setNumberOfDocuments(int numberOfDocuments) {
		this.numberOfDocuments = numberOfDocuments;
	}


	public synchronized int getNumberOfLoadedDocuments() {
		return numberOfLoadedDocuments;
	}


	public synchronized void setNumberOfLoadedDocuments(int numberOfLoadedDocuments) {
		this.numberOfLoadedDocuments = numberOfLoadedDocuments;
	}


	public synchronized JPProgressDelegate getCallbackDelegate() {
		return callbackDelegate;
	}


	public synchronized void setCallbackDelegate(JPProgressDelegate callbackDelegate) {
		this.callbackDelegate = callbackDelegate;
	}


	public synchronized float getCurrentPercent() {
		return currentPercent;
	}


	public synchronized void setCurrentPercent(float currentPercent) {
		this.currentPercent = currentPercent;
	}


	public JPProgress(int numberOfDocuments, JPProgressDelegate callbackDelegate) {
		this.numberOfDocuments = numberOfDocuments;
		this.callbackDelegate = callbackDelegate;
		
		this.numberOfLoadedDocuments = 0;
		this.currentPercent = 0;
	}
	
	public void startProgress() {
		running = true;
		callbackDelegate.didStartProgress();
	}
	
	public void killProgress() {
		running = false;
		callbackDelegate.didKillProgress();
	}
	
	public synchronized void didLoadDocument(JPDocument document) {
		if (running) {
			setNumberOfLoadedDocuments(getNumberOfLoadedDocuments()+1);
			
			float percent = (float) (20.0/numberOfDocuments);
			setCurrentPercent(getCurrentPercent()+percent);
			document.setProgressType(JPDocumentProgressType.JPDocumentProgressTypeLoaded);
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeDidLoadDocument, currentPercent, document);
		}
	}
	
	public synchronized void didStartLoadingDocument(JPDocument document) {
		if (running) {
			document.setProgressType(JPDocumentProgressType.JPDocumentProgressTypeLoading);
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeDidStartLoadingDocument, currentPercent, document);	
		}
	}
	
	public synchronized void willLoadDocument(JPDocument document) {
		if (running) {
			document.setProgressType(JPDocumentProgressType.JPDocumentProgressTypeWaiting);

			float percent = (float) (5.0/numberOfDocuments);
			setCurrentPercent(getCurrentPercent()+percent);
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeWillLoadDocument, currentPercent, document);
		}

	}
	
	public synchronized void willStartAlgorithmForDocument(JPDocument document) {
		if (running) {
			document.setProgressType(JPDocumentProgressType.JPDocumentProgressTypeComputing);
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeWillStartAlgorithmForDocument, currentPercent, document);
		}
	}
	
	public synchronized void didFinishAlgorithmForDocument(JPDocument document) {
		if (running) {
			document.setProgressType(JPDocumentProgressType.JPDocumentProgressTypeComputed);
			float percent = (float) (75.0/numberOfDocuments);
			setCurrentPercent(getCurrentPercent()+percent);
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeDidFinishAlgorithmForDocument, currentPercent, document);	
		}
	}
	
	public synchronized void willStartAlgorithm() {
		if (running) {
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeWillStartAlgorithm, currentPercent, null);
		}
	}
	
	public synchronized void didFinishAlgorithm() {
		if (running) {
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeDidFinishAlgorithm, 100, null);
			callbackDelegate.didStopProgress();	
		}
	}
}
