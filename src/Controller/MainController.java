package Controller;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Model.JPConfiguration;
import Model.JPWordTool;
import Objects.JPDocument;
import Objects.JPProgress.JPProgressDelegate;
import Utilities.GUILog;
import View.MainFrame;
import View.MainFrame.MainFrameDelegate;

/**
 * The Class MainController.
 */
public class MainController implements MainFrameDelegate, JPProgressDelegate {
//    private JPWVTool wvt;
    /** The main frame. */
private MainFrame mainFrame;
    
    /** The start time. Used when calculating the running time of an algorithm*/
    private long startTime;
    
    /** The word tool. */
    private JPWordTool wordTool;
    
	/**
	 * Instantiates a new main controller.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	public MainController() throws FileNotFoundException {
		//Try to make the Java App feel native
		makeNativeLookAndFeel();
		
		//startup GUI
		MainFrameDelegate delegate = this;
		mainFrame = new MainFrame(delegate);
		
		//initialize GUI logger
		GUILog.setupLogger(mainFrame.getPanel());
		
		wordTool = new JPWordTool();
	}

	/* (non-Javadoc)
	 * @see View.MainFrame.MainFrameDelegate#computeButtonPressed(Model.JPConfiguration)
	 */
	@Override
	public void computeButtonPressed(JPConfiguration config) {
		startTime = System.currentTimeMillis();
		
		wordTool.run(config, this, new Runnable() {
			@Override
			public void run() {
			}
		});
	}

	/* (non-Javadoc)
	 * @see Objects.JPProgress.JPProgressDelegate#didUpdateProgress(Objects.JPProgress.JPProgressDelegate.JPProgressType, float, Objects.JPDocument)
	 */
	@Override
	public void didUpdateProgress(JPProgressType progressType, float percentDone, final JPDocument document) {		
		
		final int percentInt = (int)percentDone;
				
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.setProgress(percentInt);
				mainFrame.updateStatusForDocument(document);
			}
		});
	}

	/* (non-Javadoc)
	 * @see Objects.JPProgress.JPProgressDelegate#didStartProgress()
	 */
	@Override
	public void didStartProgress() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.enableStopButton();				
			}
		});
	}

	/* (non-Javadoc)
	 * @see Objects.JPProgress.JPProgressDelegate#didStopProgress()
	 */
	@Override
	public void didStopProgress() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.disableStopButton();
				mainFrame.setProgress(0);	
			}
		});
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		GUILog.nLog("Running time: " + totalTime);
		System.out.println("Running time: " + totalTime);
	}

	/* (non-Javadoc)
	 * @see View.MainFrame.MainFrameDelegate#stopButtonPressed()
	 */
	@Override
	public void stopButtonPressed() {
		wordTool.stop();		
	}

	/* (non-Javadoc)
	 * @see Objects.JPProgress.JPProgressDelegate#didKillProgress()
	 */
	@Override
	public void didKillProgress() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.disableStopButton();
				mainFrame.setProgress(0);	
				mainFrame.clearModel();
			}
		});		
	}
	
	/**
	 * Make a native look and feel
	 */
	private void makeNativeLookAndFeel() {
        try {
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (System.getProperty("os.name").contains("Mac")) {
			  System.setProperty("apple.laf.useScreenMenuBar", "true");
		}
	}

}
