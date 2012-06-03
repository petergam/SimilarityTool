package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Model.ComputeSetup;
import Model.ComputeSetup.AlgorithmIndex;
import Model.ComputeSetup.IncludeType;
import Model.ComputeSetup.StemmerType;
import Model.ComputeSetup.WordFilterType;
import Utilities.Log;
import WVToolExtension.JPWVTDocumentInfo;
import WVToolExtension.JPWVTDocumentInfo.JPDocumentProgressType;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

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
	private JSlider hypernymsSlider;
	private JSlider hyponymsSlider;
	private JSlider synonymsSlider;

	
	private MainFrameDelegate delegate = null;

	MainFrame mf;
	private JTextPane textPane;
	private final ButtonGroup filterButtonGroup = new ButtonGroup();
	private final ButtonGroup includeButtonGroup = new ButtonGroup();
	private final ButtonGroup algorithmButtonGroup = new ButtonGroup();
	private final ButtonGroup stemmerButtonGroup = new ButtonGroup();
	JButton btnRemoveFiles;
	public JTable table;
	public SortTableModel model;
	int[] rowIndices;

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

				if (self.delegate != null) {
					ComputeSetup setup = new ComputeSetup();

					int algorithmIndex = 0;
					for (Enumeration<AbstractButton> e = algorithmButtonGroup
							.getElements(); e.hasMoreElements();) {
						if (e.nextElement().isSelected()) {
							break;
						} else {
							algorithmIndex++;
						}
					}

					setup.setAlgorithmIndex(AlgorithmIndex
							.getAlgorithmIndexFromInt(algorithmIndex));

					int filterIndex = 0;
					for (Enumeration<AbstractButton> e = filterButtonGroup
							.getElements(); e.hasMoreElements();) {
						if (e.nextElement().isSelected()) {
							break;
						} else {
							filterIndex++;
						}
					}

					setup.setFilterType(WordFilterType
							.getWordFilterTypeFromInt(filterIndex));

					int stemmerIndex = 0;
					for (Enumeration<AbstractButton> e = stemmerButtonGroup
							.getElements(); e.hasMoreElements();) {
						if (e.nextElement().isSelected()) {
							break;
						} else {
							stemmerIndex++;
						}
					}

					setup.setStemmerType(StemmerType
							.getStemmerTypeFromInt(stemmerIndex));

					int includeIndex = 0;
					for (Enumeration<AbstractButton> e = includeButtonGroup
							.getElements(); e.hasMoreElements();) {
						if (e.nextElement().isSelected()) {
							break;
						} else {
							includeIndex++;
						}
					}

					setup.setIncludeType(IncludeType
							.getIncludeTypeFromInt(includeIndex));

					File[] files = fc.getSelectedFiles();
					setup.setDocumentFiles(files);
					
					setup.setMainDocumentFile(sfc.getSelectedFile());
					

					self.delegate.computeButtonPressed(setup);
				} else {
					System.out.println("Delegate not set");
				}
			}
		});
		btnCompute.setBounds(780, 568, 89, 29);
		getContentPane().add(btnCompute);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new TitledBorder(null, "Stemming",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox.setToolTipText("");
		verticalBox.setBounds(606, 138, 263, 97);
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

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Filter",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		verticalBox_1.setBounds(606, 247, 263, 76);
		getContentPane().add(verticalBox_1);

		JRadioButton rdbtnNone = new JRadioButton("None");
		filterButtonGroup.add(rdbtnNone);
		rdbtnNone.setSelected(true);
		verticalBox_1.add(rdbtnNone);

		JRadioButton rdbtnRemoveStopWords = new JRadioButton(
				"Remove stop words");
		filterButtonGroup.add(rdbtnRemoveStopWords);
		verticalBox_1.add(rdbtnRemoveStopWords);

		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBorder(new TitledBorder(null, "Include",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_2.setBounds(606, 335, 263, 120);
		getContentPane().add(verticalBox_2);

		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_2);

		final JCheckBox chckbxHypernyms = new JCheckBox("Hypernyms");
		chckbxHypernyms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				MainFrame self = MainFrame.this;
				self.hypernymsSlider.setEnabled(chckbxHypernyms.isSelected());
			}
		});
		horizontalBox_2.add(chckbxHypernyms);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox_2.add(horizontalGlue_2);

		Component horizontalStrut_2 = Box.createHorizontalStrut(22);
		horizontalBox_2.add(horizontalStrut_2);

		hypernymsSlider = new JSlider();
		hypernymsSlider.setAlignmentX(Component.RIGHT_ALIGNMENT);
		hypernymsSlider.setEnabled(false);
		hypernymsSlider.setMaximum(3);
		hypernymsSlider.setValue(0);
		
		horizontalBox_2.add(hypernymsSlider);

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_3);

		final JCheckBox chckbxHyponyms = new JCheckBox("Hyponyms");
		chckbxHyponyms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				MainFrame self = MainFrame.this;
				self.hyponymsSlider.setEnabled(chckbxHyponyms.isSelected());
			}
		});
		horizontalBox_3.add(chckbxHyponyms);

		Component horizontalStrut_1 = Box.createHorizontalStrut(26);
		horizontalBox_3.add(horizontalStrut_1);

		hyponymsSlider = new JSlider();
		hyponymsSlider.setAlignmentX(Component.RIGHT_ALIGNMENT);
		hyponymsSlider.setEnabled(false);
		horizontalBox_3.add(hyponymsSlider);
		hyponymsSlider.setValue(0);
		hyponymsSlider.setMaximum(3);

		Box horizontalBox_4 = Box.createHorizontalBox();
		horizontalBox_4.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_4);

		final JCheckBox chckbxSynonyms = new JCheckBox("Synonyms");
		chckbxSynonyms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				MainFrame self = MainFrame.this;
				self.synonymsSlider.setEnabled(chckbxSynonyms.isSelected());
			}
		});
		horizontalBox_4.add(chckbxSynonyms);

		Component horizontalStrut = Box.createHorizontalStrut(29);
		horizontalBox_4.add(horizontalStrut);

		synonymsSlider = new JSlider();
		synonymsSlider.setMaximum(3);
		synonymsSlider.setValue(0);
		synonymsSlider.setEnabled(false);
		horizontalBox_4.add(synonymsSlider);

		Box verticalBox_3 = Box.createVerticalBox();
		verticalBox_3.setBorder(new TitledBorder(null, "Main document",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_3.setBounds(6, 6, 385, 76);
		getContentPane().add(verticalBox_3);

		textPane = new JTextPane();
		textPane.setEditable(false);
		verticalBox_3.add(textPane);
		JButton btnNewButton = new JButton("Choose File");
		verticalBox_3.add(btnNewButton);

		Box verticalBox_4 = Box.createVerticalBox();
		verticalBox_4.setBorder(new TitledBorder(null, "Documents to compare",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_4.setBounds(6, 103, 588, 276);
		getContentPane().add(verticalBox_4);

		model = new SortTableModel();
		model.addColumn("Article");
		model.addColumn("Status");
		model.addColumn("Score");
		model.addColumn("Sim");



		table = new JTable(model);
		table.setGridColor(new Color(150, 150, 200));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);

		SelectionListener listener = new SelectionListener(table);
		table.getSelectionModel().addListSelectionListener(listener);
		table.getColumnModel().getSelectionModel()
		    .addListSelectionListener(listener);

		TableColumn col = table.getColumnModel().getColumn(0);
		int width = 250;
		col.setPreferredWidth(width);


		JTableHeader header = table.getTableHeader();
		header.setUpdateTableInRealTime(true);
		header.addMouseListener(model.new ColumnListener(table));
		header.setReorderingAllowed(false);


		JScrollPane scrollPane_1 = new JScrollPane(table);
		verticalBox_4.add(scrollPane_1);

		final List list = new List();

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox_4.add(horizontalBox);

		JButton btnChooseFiles = new JButton("Choose File(s)");
		horizontalBox.add(btnChooseFiles);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);

		btnRemoveFiles = new JButton("Remove file(s)");
		btnRemoveFiles.setEnabled(false);
		horizontalBox.add(btnRemoveFiles);

		JButton btnClear = new JButton("Clear all");
		horizontalBox.add(btnClear);

		Box verticalBox_5 = Box.createVerticalBox();
		verticalBox_5.setBorder(new TitledBorder(null, "Log",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_5.setBounds(6, 380, 588, 239);
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

		btnRemoveFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for (int i = rowIndices.length-1; i >= 0; i--) {
					model.removeRow(rowIndices[i]);
				}
				btnRemoveFiles.setEnabled(false);
				table.repaint();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		verticalBox_5.add(scrollPane);

		panel = new JTextPane();
		scrollPane.setViewportView(panel);
		panel.setEditable(false);

		Box verticalBox_6 = Box.createVerticalBox();
		verticalBox_6.setBorder(new TitledBorder(null, "Algorithm",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_6.setBounds(606, 6, 263, 120);
		getContentPane().add(verticalBox_6);

		JRadioButton rdbtnFuzzy = new JRadioButton("Fuzzy Similarity");
		algorithmButtonGroup.add(rdbtnFuzzy);
		rdbtnFuzzy.setSelected(true);
		verticalBox_6.add(rdbtnFuzzy);

		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton(
				"Levenshtein Distance");
		algorithmButtonGroup.add(rdbtnNewRadioButton_4);
		verticalBox_6.add(rdbtnNewRadioButton_4);

		JRadioButton rdbtnI = new JRadioButton("TF*IDF");
		algorithmButtonGroup.add(rdbtnI);
		verticalBox_6.add(rdbtnI);

		JRadioButton rdbtnNewRadioButton_onthology = new JRadioButton(
				"Onthology Based Query");
		algorithmButtonGroup.add(rdbtnNewRadioButton_onthology);
		verticalBox_6.add(rdbtnNewRadioButton_onthology);

		progressBar = new JProgressBar();
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		progressBar.setBounds(606, 599, 263, 20);
		getContentPane().add(progressBar);

		JButton btnNewButton_1 = new JButton("Stop");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(606, 568, 89, 29);
		getContentPane().add(btnNewButton_1);

		Box verticalBox_7 = Box.createVerticalBox();
		verticalBox_7.setBorder(new TitledBorder(null, "Additional features", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_7.setBounds(606, 477, 263, 55);
		getContentPane().add(verticalBox_7);

		JCheckBox chckbxNormalizeStopwords = new JCheckBox("Normalize result");
		verticalBox_7.add(chckbxNormalizeStopwords);

		// verticalBox_7.add(table);

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowCount = model.getRowCount();
				for (int i = rowCount-1; i >= 0 ; i--) {
					model.removeRow(i);
				}
				table.repaint();
			}
		});

		btnChooseFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(mf);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					File[] files = fc.getSelectedFiles();

					if (files.length == 1) {
						Log.nLog("Loading file to compare with:");
					} else if (files.length > 1) {
						Log.nLog("Loading files to compare with:");
					}

					for (int i = 0; i < files.length; i++) {
						model.addRow(new Object[] { files[i].getName(),
								"", "-", "-" });
						Log.nLog(files[i].getName());
					}
					table.repaint();
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
		this.setSize(877, 669);

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
	
	public void updateStatusForDocument(JPWVTDocumentInfo document) {
		Vector<Object> vector = model.getDataVector();
				
		for (int i = 0; i < vector.size(); i++) {
			Vector<Object> v = (Vector<Object>) vector.get(i);//enumeration.nextElement();
			if(document != null && v.get(0).equals(document.getDocumentTitle())) {
				JPDocumentProgressType progressType = document.getProgressType();
				
				switch (progressType) {
				case JPDocumentProgressTypeNotLoaded:
					v.set(1, "");
					break;
				case JPDocumentProgressTypeWaiting:
					v.set(1, "Waiting");
					break;
				case JPDocumentProgressTypeLoading:
					v.set(1, "Loading");
					break;
				case JPDocumentProgressTypeComputing:
					v.set(1, "Computing");
					break;
				case JPDocumentProgressTypeLoaded:
					v.set(1, "Loaded");
					break;
				case JPDocumentProgressTypeComputed:
					v.set(1, "Computed");
					v.set(2, "" + document.getScore());
					break;
				default:
					break;
				}
				
				vector.set(i, v);
				((AbstractTableModel)table.getModel()).fireTableDataChanged();
				
				break;
			}
		}

	}

	public void enableStopButton() {
//		stopButton.setEnabled(true);
	}
	
	public void disableStopButton() {
//		stopButton.setEnabled(false);
	}
	
	private class SelectionListener implements ListSelectionListener {
	    JTable table;

	    // It is necessary to keep the table since it is not possible
	    // to determine the table from the event's source
	    SelectionListener(JTable table) {
	        this.table = table;
	    }

	    public void valueChanged(ListSelectionEvent e) {
	        // If cell selection is enabled, both row and column change events are fired
	        if (e.getSource() == table.getSelectionModel()
	              && table.getRowSelectionAllowed()) {
	            // Column selection changed
	        	btnRemoveFiles.setEnabled(true);
	        	  rowIndices = table.getSelectedRows();
	        } else if (e.getSource() == table.getColumnModel().getSelectionModel()
	               && table.getColumnSelectionAllowed() ){
	        }

	        if (e.getValueIsAdjusting()) {
	            // The mouse button has not yet been released
	        }
	    }
	}

	class MyComparator implements Comparator {
	  protected String column;
	  protected int index;

	  public MyComparator(String column, int index) {
	    this.column = column;
	    this.index = index;
	  }

	  public int compare(Object o1, Object o2) {
		  Vector v1 = (Vector) o1;
		  Vector v2 = (Vector) o2;
		  int result = 0;

		  if (column.equals("Article") || column.equals("Status")) {
			String s1 = (String) v1.get(index);
			String s2 = (String) v2.get(index);
			return s1.compareTo(s2);
		  }

		  else if (column.equals("Score") || column.equals("Sim")) {
				Double s1 = Double.parseDouble((String) v1.get(index));
				Double s2 = Double.parseDouble((String) v2.get(index));
				return s1.compareTo(s2);
		  }

		  return result;
	  }
	}

	  class SortTableModel extends DefaultTableModel {
		  protected int sortCol = 0;

		  protected boolean isSortAsc = true;

		  protected int m_result = 0;
		  protected int columnsCount = 1;
		  public SortTableModel() {
		  }

		  class ColumnListener extends MouseAdapter {
		    protected JTable table;

		    public ColumnListener(JTable t) {
		      table = t;
		    }

		    public void mouseClicked(MouseEvent e) {
		      TableColumnModel colModel = table.getColumnModel();
		      int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
		      String column = table.getColumnName(columnModelIndex);
		      int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();

		      Vector<Object> tempVector = (Vector<Object>) model.getDataVector().clone();
		      Collections.sort(model.getDataVector(),new MyComparator(column,modelIndex));
		      if (tempVector.equals(model.getDataVector())) {
				Collections.reverse(model.getDataVector());
		      }
		      table.tableChanged(new TableModelEvent(SortTableModel.this));
		      table.repaint();
		    }
		  }
	  }
}
