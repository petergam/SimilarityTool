package View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * The Class AbstractAlgorithmPopupFrame.
 */
public abstract class AbstractAlgorithmPopupFrame extends JDialog  {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2658566720277470146L;
	
	/** The popup frame. */
	AbstractAlgorithmPopupFrame pf;
	
	/** The close button. */
	JButton close;
	
	/** The settings. */
	HashMap<String, Object> settings;
	
	
	/**
	 * Instantiates a new abstract algorithm popup frame.
	 *
	 * @param adjustSettings the adjust settings
	 */
	public AbstractAlgorithmPopupFrame(HashMap<String, Object> adjustSettings) {
		pf = this;
		this.settings = adjustSettings;
		setResizable(false);
		this.setLocation(500, 120);
		this.addWindowListener(new WindowAdapter() 
		{

			  public void windowClosing(WindowEvent e)
			  {
				  setSettings();
			  }
			});

	}
	
	/**
	 * Sets the settings.
	 */
	public void setSettings(){
		settings = new HashMap<String, Object>();

	}
	
	/**
	 * Load settings.
	 *
	 * @param settings the settings
	 */
	public void loadSettings(HashMap<String, Object> settings){
		
	}
}
