package Objects;

import Objects.JPDocument.JPDocumentProgressType;
import Objects.JPProgress.JPProgressDelegate.JPProgressType;

/**
 * The Class JPProgress.
 * Handles progress and notifies delegate
 */
public class JPProgress {
	
	/**
	 * The Interface JPProgressDelegate.
	 */
	public interface JPProgressDelegate {
		
		/**
		 * The Enum JPProgressType.
		 */
		public enum JPProgressType {
			
			/** The JP progress type will load document. */
			JPProgressTypeWillLoadDocument,
			
			/** The JP progress type did load document. */
			JPProgressTypeDidLoadDocument,
			
			/** The JP progress type did start loading document. */
			JPProgressTypeDidStartLoadingDocument,
			
			/** The JP progress type did progress. */
			JPProgressTypeDidProgress,
			
			/** The JP progress type will start algorithm. */
			JPProgressTypeWillStartAlgorithm,
			
			/** The JP progress type did finish algorithm. */
			JPProgressTypeDidFinishAlgorithm,
			
			/** The JP progress type did finish algorithm for document. */
			JPProgressTypeDidFinishAlgorithmForDocument,
			
			/** The JP progress type will start algorithm for document. */
			JPProgressTypeWillStartAlgorithmForDocument,
		}
		
		/**
		 * Did start progress.
		 */
		public void didStartProgress();
		
		/**
		 * Did stop progress.
		 */
		public void didStopProgress();
		
		/**
		 * Did kill progress.
		 */
		public void didKillProgress();
		
		/**
		 * Did update progress.
		 *
		 * @param progressType the progress type
		 * @param percentDone the percent done
		 * @param document the document
		 */
		public void didUpdateProgress(JPProgressType progressType, float percentDone, JPDocument document);
	}
	
	/** Indicates if progress is running. */
	private boolean running = false;
	
	/** The number of documents. */
	private int numberOfDocuments;
	
	/** The number of loaded documents. */
	private int numberOfLoadedDocuments;

	/** The callback delegate. */
	private JPProgressDelegate callbackDelegate;
	
	/** The current percent. */
	private float currentPercent;
	
	/**
	 * Gets the number of documents.
	 *
	 * @return the number of documents
	 */
	public synchronized int getNumberOfDocuments() {
		return numberOfDocuments;
	}


	/**
	 * Sets the number of documents.
	 *
	 * @param numberOfDocuments the new number of documents
	 */
	public synchronized void setNumberOfDocuments(int numberOfDocuments) {
		this.numberOfDocuments = numberOfDocuments;
	}


	/**
	 * Gets the number of loaded documents.
	 *
	 * @return the number of loaded documents
	 */
	public synchronized int getNumberOfLoadedDocuments() {
		return numberOfLoadedDocuments;
	}


	/**
	 * Sets the number of loaded documents.
	 *
	 * @param numberOfLoadedDocuments the new number of loaded documents
	 */
	public synchronized void setNumberOfLoadedDocuments(int numberOfLoadedDocuments) {
		this.numberOfLoadedDocuments = numberOfLoadedDocuments;
	}


	/**
	 * Gets the callback delegate.
	 *
	 * @return the callback delegate
	 */
	public synchronized JPProgressDelegate getCallbackDelegate() {
		return callbackDelegate;
	}


	/**
	 * Sets the callback delegate.
	 *
	 * @param callbackDelegate the new callback delegate
	 */
	public synchronized void setCallbackDelegate(JPProgressDelegate callbackDelegate) {
		this.callbackDelegate = callbackDelegate;
	}


	/**
	 * Gets the current percent.
	 *
	 * @return the current percent
	 */
	public synchronized float getCurrentPercent() {
		return currentPercent;
	}


	/**
	 * Sets the current percent.
	 *
	 * @param currentPercent the new current percent
	 */
	public synchronized void setCurrentPercent(float currentPercent) {
		this.currentPercent = currentPercent;
	}


	/**
	 * Instantiates a new jP progress.
	 *
	 * @param numberOfDocuments the number of documents
	 * @param callbackDelegate the callback delegate
	 */
	public JPProgress(int numberOfDocuments, JPProgressDelegate callbackDelegate) {
		this.numberOfDocuments = numberOfDocuments;
		this.callbackDelegate = callbackDelegate;
		
		this.numberOfLoadedDocuments = 0;
		this.currentPercent = 0;
	}
	
	/**
	 * Start progress.
	 */
	public void startProgress() {
		running = true;
		callbackDelegate.didStartProgress();
	}
	
	/**
	 * Kill progress.
	 */
	public void killProgress() {
		running = false;
		callbackDelegate.didKillProgress();
	}
	
	/**
	 * Did load document.
	 *
	 * @param document the document
	 */
	public synchronized void didLoadDocument(JPDocument document) {
		if (running) {
			setNumberOfLoadedDocuments(getNumberOfLoadedDocuments()+1);
			
			float percent = (float) (20.0/numberOfDocuments);
			setCurrentPercent(getCurrentPercent()+percent);
			document.setProgressType(JPDocumentProgressType.JPDocumentProgressTypeLoaded);
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeDidLoadDocument, currentPercent, document);
		}
	}
	
	/**
	 * Did start loading document.
	 *
	 * @param document the document
	 */
	public synchronized void didStartLoadingDocument(JPDocument document) {
		if (running) {
			document.setProgressType(JPDocumentProgressType.JPDocumentProgressTypeLoading);
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeDidStartLoadingDocument, currentPercent, document);	
		}
	}
	
	/**
	 * Will load document.
	 *
	 * @param document the document
	 */
	public synchronized void willLoadDocument(JPDocument document) {
		if (running) {
			document.setProgressType(JPDocumentProgressType.JPDocumentProgressTypeWaiting);

			float percent = (float) (5.0/numberOfDocuments);
			setCurrentPercent(getCurrentPercent()+percent);
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeWillLoadDocument, currentPercent, document);
		}

	}
	
	/**
	 * Will start algorithm for document.
	 *
	 * @param document the document
	 */
	public synchronized void willStartAlgorithmForDocument(JPDocument document) {
		if (running) {
			document.setProgressType(JPDocumentProgressType.JPDocumentProgressTypeComputing);
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeWillStartAlgorithmForDocument, currentPercent, document);
		}
	}
	
	/**
	 * Did finish algorithm for document.
	 *
	 * @param document the document
	 */
	public synchronized void didFinishAlgorithmForDocument(JPDocument document) {
		if (running) {
			document.setProgressType(JPDocumentProgressType.JPDocumentProgressTypeComputed);
			float percent = (float) (75.0/numberOfDocuments);
			setCurrentPercent(getCurrentPercent()+percent);
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeDidFinishAlgorithmForDocument, currentPercent, document);	
		}
	}
	
	/**
	 * Will start algorithm.
	 */
	public synchronized void willStartAlgorithm() {
		if (running) {
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeWillStartAlgorithm, currentPercent, null);
		}
	}
	
	/**
	 * Did finish algorithm.
	 */
	public synchronized void didFinishAlgorithm() {
		if (running) {
			callbackDelegate.didUpdateProgress(JPProgressType.JPProgressTypeDidFinishAlgorithm, 100, null);
			callbackDelegate.didStopProgress();	
		}
	}
}
