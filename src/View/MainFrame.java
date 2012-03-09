package View;

import java.awt.Color;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3623611609911397035L;
	// Create a file chooser
	final JFileChooser fc = new JFileChooser();
	final JFileChooser sfc = new JFileChooser();

	MainFrame mf;

	public MainFrame() {
		mf = this;
		setResizable(false);
		fc.setMultiSelectionEnabled(true);
		fc.addChoosableFileFilter(new MyFilter());
		sfc.addChoosableFileFilter(new MyFilter());

		setTitle("Textual Similarities");
		getContentPane().setBackground(new Color(220, 220, 220));
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(6, 430, 888, 142);
		getContentPane().add(panel);

		JLabel lblTextOne = new JLabel("Text one");
		lblTextOne.setBounds(6, 6, 61, 16);
		getContentPane().add(lblTextOne);
		JButton btnNewButton = new JButton("Choose File");

		btnNewButton.setBounds(6, 25, 143, 27);
		getContentPane().add(btnNewButton);

		JLabel lblTextsToCompare = new JLabel("Texts to compare");
		lblTextsToCompare.setBounds(6, 103, 122, 16);
		getContentPane().add(lblTextsToCompare);

		JButton btnChooseFiles = new JButton("Choose File(s)");

		btnChooseFiles.setBounds(6, 123, 162, 25);
		getContentPane().add(btnChooseFiles);

		final List list = new List();
		list.setBounds(6, 154, 250, 171);
		getContentPane().add(list);

		final List list_1 = new List();
		list_1.setMultipleMode(false);
		list_1.setBounds(6, 58, 250, 25);
		getContentPane().add(list_1);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list.removeAll();
				list.repaint();
			}
		});
		btnClear.setBounds(180, 121, 76, 29);
		getContentPane().add(btnClear);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.setLocation(200, 100);
		this.setSize(900, 600);

		this.setVisible(true);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = sfc.showOpenDialog(mf);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					list_1.removeAll();

					list_1.add(sfc.getSelectedFile().getName());
			
					list_1.repaint();

				}
			}
		});

		btnChooseFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(mf);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// System.out.println("You chose to open this file: " +
					// fc.getSelectedFile().getName());

					File[] files = fc.getSelectedFiles();

					for (int i = 0; i < files.length; i++) {
						list.add(files[i].getName());
					}
					list.repaint();

				}
			}
		});
	}
	
	class MyFilter extends javax.swing.filechooser.FileFilter {
	    public boolean accept(File file) {
	        String filename = file.getName();
	        return filename.endsWith(".txt");
	    }
	    public String getDescription() {
	        return "*.txt";
	    }
	}
}
