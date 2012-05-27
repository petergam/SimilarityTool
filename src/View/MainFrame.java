package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import Model.ComputeSetup;
import Model.ComputeSetup.AlgorithmIndex;
import Model.ComputeSetup.IncludeType;
import Model.ComputeSetup.StemmerType;
import Model.ComputeSetup.WordFilterType;
import Utilities.Log;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.Box;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.JProgressBar;

public class MainFrame extends JFrame {

	
	public interface MainFrameDelegate {
		public void computeButtonPressed(ComputeSetup setup);
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Create a file chooser
	private JFileChooser fc = new JFileChooser();
	private JFileChooser sfc = new JFileChooser();
	
	private JTextPane panel;
	private JProgressBar progressBar;
	
	private MainFrameDelegate delegate = null;
	
	MainFrame mf;
	private JTextPane textPane;
	private final ButtonGroup filterButtonGroup = new ButtonGroup();
	private final ButtonGroup includeButtonGroup = new ButtonGroup();
	private final ButtonGroup algorithmButtonGroup = new ButtonGroup();
	private final ButtonGroup stemmerButtonGroup = new ButtonGroup();

	public MainFrame(MainFrameDelegate delegate) {
		setResizable(false);
		mf = this;
		this.delegate = delegate;
		fc.setMultiSelectionEnabled(true);
		fc.addChoosableFileFilter(new MyFilter());
		sfc.addChoosableFileFilter(new MyFilter());

		setTitle("Textual Similarities");
		getContentPane().setBackground(UIManager.getColor("Panel.background"));
		getContentPane().setLayout(null);
		
		JButton btnCompute = new JButton("Compute");
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame self = MainFrame.this;
				
				if(self.delegate != null) {
					ComputeSetup setup = new ComputeSetup();
										
					int algorithmIndex = 0;
				     for (Enumeration<AbstractButton> e = algorithmButtonGroup.getElements() ; e.hasMoreElements() ;) {
				    	 if(e.nextElement().isSelected()) {
				    		 break;
				    	 } else {
				    		 algorithmIndex++;
				    	 }
				     }										 
				     
					setup.setAlgorithmIndex(AlgorithmIndex.getAlgorithmIndexFromInt(algorithmIndex));
					
					int filterIndex = 0;
				     for (Enumeration<AbstractButton> e = filterButtonGroup.getElements() ; e.hasMoreElements() ;) {
				    	 if(e.nextElement().isSelected()) {
				    		 break;
				    	 } else {
				    		 filterIndex++;
				    	 }
				     }	
					
					setup.setFilterType(WordFilterType.getWordFilterTypeFromInt(filterIndex));
					
					int stemmerIndex = 0;
				     for (Enumeration<AbstractButton> e = stemmerButtonGroup.getElements() ; e.hasMoreElements() ;) {
				    	 if(e.nextElement().isSelected()) {
				    		 break;
				    	 } else {
				    		 stemmerIndex++;
				    	 }
				     }	
					
					setup.setStemmerType(StemmerType.getStemmerTypeFromInt(stemmerIndex));

					int includeIndex = 0;
				     for (Enumeration<AbstractButton> e = includeButtonGroup.getElements() ; e.hasMoreElements() ;) {
				    	 if(e.nextElement().isSelected()) {
				    		 break;
				    	 } else {
				    		 includeIndex++;
				    	 }
				     }	
					
				     setup.setIncludeType(IncludeType.getIncludeTypeFromInt(includeIndex));
				     
					File[] files = fc.getSelectedFiles();
					String[] documentsPaths = new String[files.length];
					for (int i = 0; i < documentsPaths.length; i++) {
						documentsPaths[i] = files[i].getPath();
					}
					setup.setDocumentPaths(documentsPaths);
					
					
					String mainDocumentPath = sfc.getSelectedFile().getPath();
					setup.setMainDocumentPath(mainDocumentPath);
					
					self.delegate.computeButtonPressed(setup);
				}else {
					System.out.println("Delegate not set");
				}				
			}
		});
		btnCompute.setBounds(502, 478, 89, 29);
		getContentPane().add(btnCompute);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new TitledBorder(null, "Stemmer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox.setToolTipText("");
		verticalBox.setBounds(412, 6, 179, 166);
		getContentPane().add(verticalBox);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("None");
		stemmerButtonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		verticalBox.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Lovins");
		stemmerButtonGroup.add(rdbtnNewRadioButton_1);
		verticalBox.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnPorter = new JRadioButton("Porter");
		stemmerButtonGroup.add(rdbtnPorter);
		verticalBox.add(rdbtnPorter);
		
		JRadioButton rdbtnToLoverCase = new JRadioButton("To lower case");
		stemmerButtonGroup.add(rdbtnToLoverCase);
		verticalBox.add(rdbtnToLoverCase);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Hypernym");
		stemmerButtonGroup.add(rdbtnNewRadioButton_2);
		verticalBox.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Synonym");
		stemmerButtonGroup.add(rdbtnNewRadioButton_3);
		verticalBox.add(rdbtnNewRadioButton_3);
		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		verticalBox_1.setBounds(412, 184, 179, 76);
		getContentPane().add(verticalBox_1);
		
		JRadioButton rdbtnNone = new JRadioButton("None");
		filterButtonGroup.add(rdbtnNone);
		rdbtnNone.setSelected(true);
		verticalBox_1.add(rdbtnNone);
		
		JRadioButton rdbtnRemoveStopWords = new JRadioButton("Remove stop words");
		filterButtonGroup.add(rdbtnRemoveStopWords);
		verticalBox_1.add(rdbtnRemoveStopWords);
		
		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBorder(new TitledBorder(null, "Include", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_2.setBounds(412, 272, 179, 97);
		getContentPane().add(verticalBox_2);
		
		JRadioButton rdbtnNone_1 = new JRadioButton("None");
		includeButtonGroup.add(rdbtnNone_1);
		rdbtnNone_1.setSelected(true);
		verticalBox_2.add(rdbtnNone_1);
		
		JRadioButton rdbtnHypernyms = new JRadioButton("Hypernyms");
		includeButtonGroup.add(rdbtnHypernyms);
		verticalBox_2.add(rdbtnHypernyms);
		
		JRadioButton rdbtnSynonyms = new JRadioButton("Synonyms");
		includeButtonGroup.add(rdbtnSynonyms);
		verticalBox_2.add(rdbtnSynonyms);
		
		Box verticalBox_3 = Box.createVerticalBox();
		verticalBox_3.setBorder(new TitledBorder(null, "Main document", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_3.setBounds(6, 6, 400, 85);
		getContentPane().add(verticalBox_3);
						
						textPane = new JTextPane();
						textPane.setEditable(false);
						verticalBox_3.add(textPane);
						JButton btnNewButton = new JButton("Choose File");
						verticalBox_3.add(btnNewButton);
						
						Box verticalBox_4 = Box.createVerticalBox();
						verticalBox_4.setBorder(new TitledBorder(null, "Documents to compare", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						verticalBox_4.setBounds(6, 103, 394, 226);
						getContentPane().add(verticalBox_4);
										
												final List list = new List();
												verticalBox_4.add(list);
												
												Box horizontalBox = Box.createHorizontalBox();
												verticalBox_4.add(horizontalBox);
												
														JButton btnChooseFiles = new JButton("Choose File(s)");
														horizontalBox.add(btnChooseFiles);
																
																Component horizontalGlue = Box.createHorizontalGlue();
																horizontalBox.add(horizontalGlue);
																
																		JButton btnClear = new JButton("Clear");
																		horizontalBox.add(btnClear);
																		
																		Box verticalBox_5 = Box.createVerticalBox();
																		verticalBox_5.setBorder(new TitledBorder(null, "Log", TitledBorder.LEADING, TitledBorder.TOP, null, null));
																		verticalBox_5.setBounds(6, 341, 394, 209);
																		getContentPane().add(verticalBox_5);
																		
																		Box horizontalBox_1 = Box.createHorizontalBox();
																		verticalBox_5.add(horizontalBox_1);
																		
																		JButton btnClearLog = new JButton("Clear log");
																		horizontalBox_1.add(btnClearLog);
																		
																		Component horizontalGlue_1 = Box.createHorizontalGlue();
																		horizontalBox_1.add(horizontalGlue_1);
																		btnClearLog.addActionListener(new ActionListener() {
																			public void actionPerformed(ActionEvent arg0) {
																				Log.clear();
																			}
																		});
																		
																		JScrollPane scrollPane = new JScrollPane();
																		verticalBox_5.add(scrollPane);
																		
																		panel = new JTextPane();
																		scrollPane.setViewportView(panel);
																		panel.setEditable(false);
																		
																		Box verticalBox_6 = Box.createVerticalBox();
																		verticalBox_6.setBorder(new TitledBorder(null, "Algorithm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
																		verticalBox_6.setBounds(412, 378, 179, 97);
																		getContentPane().add(verticalBox_6);
																		
																		JRadioButton rdbtnFuzzy = new JRadioButton("Fuzzy Similarity");
																		algorithmButtonGroup.add(rdbtnFuzzy);
																		rdbtnFuzzy.setSelected(true);
																		verticalBox_6.add(rdbtnFuzzy);
																		
																		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Levenshtein Distance");
																		algorithmButtonGroup.add(rdbtnNewRadioButton_4);
																		verticalBox_6.add(rdbtnNewRadioButton_4);
																		
																		JRadioButton rdbtnI = new JRadioButton("TF*IDF");
																		algorithmButtonGroup.add(rdbtnI);
																		verticalBox_6.add(rdbtnI);
																		
																		progressBar = new JProgressBar();
																		progressBar.setMaximum(100);
																		progressBar.setMinimum(0);
																		progressBar.setBounds(412, 519, 179, 20);
																		getContentPane().add(progressBar);
																		
																		JButton btnNewButton_1 = new JButton("Stop");
																		btnNewButton_1.setEnabled(false);
																		btnNewButton_1.addActionListener(new ActionListener() {
																			public void actionPerformed(ActionEvent arg0) {
																			}
																		});
																		btnNewButton_1.setBounds(412, 478, 89, 29);
																		getContentPane().add(btnNewButton_1);
																		btnClear.addActionListener(new ActionListener() {
																			public void actionPerformed(ActionEvent arg0) {
																				list.removeAll();
																				list.repaint();
																			}
																		});
								
										btnChooseFiles.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												int returnVal = fc.showOpenDialog(mf);
												if (returnVal == JFileChooser.APPROVE_OPTION) {
													// System.out.println("You chose to open this file: " +
													// fc.getSelectedFile().getName());
								
													File[] files = fc.getSelectedFiles();
								
													if (files.length == 1) {
														Log.nLog("Loading file to compare with:");
													}
													else if (files.length> 1) {
														Log.nLog("Loading files to compare with:");
													}
													
													for (int i = 0; i < files.length; i++) {
														list.add(files[i].getName());
														Log.nLog(files[i].getName());
													}
													list.repaint();
								
												}
											}
										});
						
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										int returnVal = sfc.showOpenDialog(mf);
										if (returnVal == JFileChooser.APPROVE_OPTION) {
											
											getTextPane().removeAll();
											getTextPane().setText(sfc.getSelectedFile().getName());

											Log.nLog("Loading file:");
											Log.nLog(sfc.getSelectedFile().getName());
						
										}
									}
								});
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.setLocation(200, 100);
		this.setSize(600, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnNewMenu.add(mntmQuit);

		this.setVisible(true);
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
	
	public JTextPane getPanel() {
		return panel;
	}
	public JTextPane getTextPane() {
		return textPane;
	}
	
	public void setProgress(int progress) {
		progressBar.setValue(progress);
	}
}
