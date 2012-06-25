package View;

import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// TODO: Auo-generated Javadoc
/**
 * The Class LevenshteinPopup.
 */
public class LevenshteinPopup extends AbstractAlgorithmPopupFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2367831512417358302L;

	/**
	 * Instantiates a new levenshtein popup.
	 *
	 * @param settings the settings
	 */
	public LevenshteinPopup(HashMap<String, Object> settings) {
		super(settings);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Levenshtein Distance");
		getContentPane().setLayout(null);
		this.setSize(new Dimension(395, 112));

		JLabel lblThereAreNo = new JLabel("There are no custom settings for this algorithm.");
		lblThereAreNo.setBounds(47, 6, 312, 36);
		getContentPane().add(lblThereAreNo);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();	
			}
		});
		btnClose.setBounds(152, 54, 88, 29);
		getContentPane().add(btnClose);
	}
}
