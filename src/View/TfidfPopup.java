package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;

public class TfidfPopup extends AbstractAlgorithmPopupFrame {

	public TfidfPopup(HashMap<String, String> settings) {
		super(settings);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Term Frequency - Inverse Document Frequency");
		getContentPane().setLayout(null);
		this.setSize(new Dimension(395, 112));

		JLabel lblThereAreNo = new JLabel("There are no custom settings for this algorithm.");
		lblThereAreNo.setBounds(45, 6, 312, 36);
		getContentPane().add(lblThereAreNo);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();	
			}
		});
		btnClose.setBounds(153, 54, 88, 29);
		getContentPane().add(btnClose);
	}
}
