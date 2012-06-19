package View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;

public abstract class AbstractAlgorithmPopupFrame extends JDialog  {
	
	private static final long serialVersionUID = 2658566720277470146L;
	AbstractAlgorithmPopupFrame pf;
	JButton close;
	HashMap<String, String> settings;
	
	
	public AbstractAlgorithmPopupFrame(HashMap<String, String> settings) {
		pf = this;
		this.settings = settings;
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
	
	public void setSettings(){
	}
	
	public void loadSettings(HashMap<String, String> settings){
		
	}
}
