package WVToolExtension;

import WVToolExtension.JPProgress.JPProgressDelegate.JPProgressType;

public class JPProgress {
	
	public interface JPProgressDelegate {
		
		public enum JPProgressType {
			JPProgressTypeWillLoadDocument,
			JPProgressTypeDidLoadDocument,
			JPProgressTypeWillStartAlgorithm,
			JPProgressTypeDidFinishAlgorithm,
		}
		
		public void didUpdateProgress(JPProgressType progressType, float percentDone);
	}
	
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
	
	
	public synchronized void didLoadDocument() {
		setNumberOfLoadedDocuments(getNumberOfLoadedDocuments()+1);
		
		float percent = 40/numberOfDocuments;
		setCurrentPercent(getCurrentPercent()+percent);
		callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeDidLoadDocument, currentPercent);
	}
	
	public synchronized void willLoadDocument() {
		float percent = 10/numberOfDocuments;
		setCurrentPercent(getCurrentPercent()+percent);
		callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeWillLoadDocument, currentPercent);
	}
	
	public synchronized void willStartAlgorithm() {
		setCurrentPercent(getCurrentPercent()+10);
		callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeWillStartAlgorithm, currentPercent);
	}
	
	public synchronized void didFinishAlgorithm() {
		callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeDidFinishAlgorithm, 100.0f);
	}
}
