package Controller;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.didion.jwnl.JWNLException;
import Model.JPConfiguration;
import Model.JPWordTool;
import Objects.JPDocument;
import Objects.JPProgress.JPProgressDelegate;
import Utilities.Log;
import View.MainFrame;
import View.MainFrame.MainFrameDelegate;

public class MainController implements MainFrameDelegate, JPProgressDelegate {
//    private JPWVTool wvt;
    private MainFrame mainFrame;
    private long startTime;
    private JPWordTool wordTool;
    
	public MainController() throws FileNotFoundException, JWNLException {
		//Try to make the Java App feel native
		makeNativeLookAndFeel();
		
		//startup GUI
		MainFrameDelegate delegate = this;
		mainFrame = new MainFrame(delegate);
		
		//initialize GUI logger
		Log.setupLogger(mainFrame.getPanel());
		
		wordTool = new JPWordTool();
	}

	@Override
	public void computeButtonPressed(JPConfiguration config) {
		startTime = System.currentTimeMillis();
		
		wordTool.run(config, this, new Runnable() {
			@Override
			public void run() {
			}
		});
	}

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

	@Override
	public void didStartProgress() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.enableStopButton();				
			}
		});
	}

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
		System.out.println("Running time: " + totalTime);
	}

	@Override
	public void stopButtonPressed() {
		wordTool.stop();		
	}

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
