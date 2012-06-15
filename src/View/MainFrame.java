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
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Include.JPIncludeHypernyms;
import Include.JPIncludeHyponyms;
import Include.JPIncludeSynonyms;
import Model.JPCache;
import Model.JPConfiguration;
import Model.JPConfiguration.AlgorithmIndex;
import Model.JPConfiguration.IncludeType;
import Model.JPConfiguration.POSTaggerType;
import Model.JPConfiguration.SenseRelateType;
import Model.JPConfiguration.StemmerType;
import Model.JPConfiguration.TrimmerType;
import Objects.JPDocument;
import Objects.JPDocument.JPDocumentProgressType;
import Utilities.GUILog;

/**
 * The Class MainFrame.
 * Represents the view. Most of this class is generated with Windows Builder
 */
public class MainFrame extends JFrame {

	/**
	 * The Interface MainFrameDelegate.
	 */
	public interface MainFrameDelegate {
		
		/**
		 * Compute button pressed.
		 *
		 * @param setup the setup
		 */
		public void computeButtonPressed(JPConfiguration setup);
		
		/**
		 * Stop button pressed.
		 */
		public void stopButtonPressed();

		//Remove later
		public void computeAllButtonPressed(JPConfiguration setup, int i);
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	// Create a file chooser
	/** The main document file chooser. */
	private JFileChooser fc = new JFileChooser();
	
	/** The other documents file chooser. */
	private JFileChooser sfc = new JFileChooser();

	/** The panel. */
	private JTextPane panel;
	
	/** The progress bar. */
	private JProgressBar progressBar;
	
	/** The hypernyms slider. */
	private JSlider hypernymsSlider;
	
	/** The hyponyms slider. */
	private JSlider hyponymsSlider;
	
	/** The synonyms slider. */
	private JSlider synonymsSlider;
	
	/** The hyper slider value. */
	private JSlider hyperSliderValue;
	
	/** The hypo slider value. */
	private JSlider hypoSliderValue;
	
	/** The syno slider value. */
	private JSlider synoSliderValue;
	
	/** The chckbx hypernyms. */
	private JCheckBox chckbxHypernyms;
	
	/** The chckbx hyponyms. */
	private JCheckBox chckbxHyponyms;
	
	/** The chckbx synonyms. */
	private JCheckBox chckbxSynonyms;
	
	/** The sense related words radio button. */
	private JRadioButton senseRelatedWordsRadioButton;
	
	/** The pos tagged words radio button. */
	private JRadioButton posTaggedWordsRadioButton;
	
	/** The all words radio button. */
	private JRadioButton allWordsRadioButton;
	
	/** The delegate. */
	private MainFrameDelegate delegate = null;

	/** The mf. */
	MainFrame mf;
	
	/** The text pane. */
	private JTextPane textPane;
	
	/** The compute button. */
	private JButton computeButton;
	
	/** The stop button. */
	private JButton stopButton;
	
	/** The filter button group. */
	private final ButtonGroup filterButtonGroup = new ButtonGroup();
	
	/** The algorithm button group. */
	private final ButtonGroup algorithmButtonGroup = new ButtonGroup();
	
	/** The stemmer button group. */
	private final ButtonGroup stemmerButtonGroup = new ButtonGroup();
	
	/** The btn remove files. */
	JButton btnRemoveFiles;
	
	/** The table. */
	public JTable table;
	
	/** The model. */
	public SortTableModel model;
	
	/** The row indices. */
	int[] rowIndices;
	
	/** The pos tagger button group. */
	private final ButtonGroup posTaggerButtonGroup = new ButtonGroup();
	
	/** The sense relate button group. */
	private final ButtonGroup senseRelateButtonGroup = new ButtonGroup();
	
	/** The include button group. */
	private final ButtonGroup includeButtonGroup = new ButtonGroup();

	/** The selected algo index. */
	private int selectedAlgoIndex = 0;
	
	/** The algo listener. */
	final ChangeListener algoListener;

	/**
	 * Instantiates a new main frame.
	 *
	 * @param delegate the delegate
	 */
	public MainFrame(MainFrameDelegate delegate) {
		setResizable(false);
		mf = this;
		this.delegate = delegate;
		fc.setMultiSelectionEnabled(true);
		fc.addChoosableFileFilter(new MyFilter());
		sfc.addChoosableFileFilter(new MyFilter());

		setTitle("Similarity Tool");
		getContentPane().setBackground(UIManager.getColor("Panel.background"));
		getContentPane().setLayout(null);

		computeButton = new JButton("Compute");
		computeButton.setEnabled(false);

		computeButton.setBounds(825, 621, 89, 29);
		getContentPane().add(computeButton);


		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new TitledBorder(null, "Stemming",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox.setToolTipText("");
		verticalBox.setBounds(769, 6, 145, 120);
		getContentPane().add(verticalBox);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("None");
		stemmerButtonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		verticalBox.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Wordnet");
		stemmerButtonGroup.add(rdbtnNewRadioButton_1);
		verticalBox.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Porter");
		stemmerButtonGroup.add(rdbtnNewRadioButton_3);
		verticalBox.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnLovins = new JRadioButton("Lovins");
		stemmerButtonGroup.add(rdbtnLovins);
		verticalBox.add(rdbtnLovins);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Filter",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		verticalBox_1.setBounds(568, 548, 180, 76);
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
		verticalBox_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Concept inclusion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		verticalBox_2.setBounds(568, 234, 346, 302);

		getContentPane().add(verticalBox_2);

		Box horizontalBox_7 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_7);

		Component horizontalGlue_5 = Box.createHorizontalGlue();
		horizontalBox_7.add(horizontalGlue_5);

		JLabel lblNewLabel = new JLabel("Layers");
		horizontalBox_7.add(lblNewLabel);

		Component horizontalStrut_3 = Box.createHorizontalStrut(65);
		horizontalBox_7.add(horizontalStrut_3);

		JLabel lblNewLabel_1 = new JLabel("Sim-value");
		horizontalBox_7.add(lblNewLabel_1);

		Component horizontalStrut_4 = Box.createHorizontalStrut(30);
		horizontalBox_7.add(horizontalStrut_4);

		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_2);

		chckbxHypernyms = new JCheckBox("Hypernyms");
		chckbxHypernyms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				MainFrame self = MainFrame.this;
				self.hypernymsSlider.setEnabled(chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled());
				self.hyperSliderValue.setEnabled(chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled() && selectedAlgoIndex==3);
			}
		});
		horizontalBox_2.add(chckbxHypernyms);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox_2.add(horizontalGlue_2);

		Component horizontalStrut_2 = Box.createHorizontalStrut(22);
		horizontalBox_2.add(horizontalStrut_2);

		hypernymsSlider = new JSlider();
		hypernymsSlider.setMajorTickSpacing(1);
		hypernymsSlider.setSnapToTicks(true);
		hypernymsSlider.setPaintLabels(true);
		hypernymsSlider.setPaintTicks(true);
		hypernymsSlider.setAlignmentX(Component.RIGHT_ALIGNMENT);
		hypernymsSlider.setEnabled(false);
		hypernymsSlider.setMaximum(3);
		hypernymsSlider.setValue(0);

		horizontalBox_2.add(hypernymsSlider);

		hyperSliderValue = new JSlider();
		hyperSliderValue.setMinorTickSpacing(10);
		hyperSliderValue.setEnabled(false);
		hyperSliderValue.setValue(90);
		hyperSliderValue.setPaintLabels(true);
		hyperSliderValue.setPaintTicks(true);
		hyperSliderValue.setSnapToTicks(true);
		hyperSliderValue.setMajorTickSpacing(100);
		horizontalBox_2.add(hyperSliderValue);

		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("0.0"));
		labelTable.put(new Integer(100), new JLabel("1.0"));
		hyperSliderValue.setLabelTable(labelTable);

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_3);

		chckbxHyponyms = new JCheckBox("Hyponyms");
		chckbxHyponyms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				MainFrame self = MainFrame.this;
				self.hyponymsSlider.setEnabled(chckbxHyponyms.isSelected() && chckbxHyponyms.isEnabled());
				self.hypoSliderValue.setEnabled(chckbxHyponyms.isSelected() && chckbxHyponyms.isEnabled() && selectedAlgoIndex==3);
			}
		});
		horizontalBox_3.add(chckbxHyponyms);

		Component horizontalStrut_1 = Box.createHorizontalStrut(26);
		horizontalBox_3.add(horizontalStrut_1);

		hyponymsSlider = new JSlider();
		hyponymsSlider.setMajorTickSpacing(1);
		hyponymsSlider.setPaintTicks(true);
		hyponymsSlider.setSnapToTicks(true);
		hyponymsSlider.setPaintLabels(true);
		hyponymsSlider.setAlignmentX(Component.RIGHT_ALIGNMENT);
		hyponymsSlider.setEnabled(false);
		horizontalBox_3.add(hyponymsSlider);
		hyponymsSlider.setValue(0);
		hyponymsSlider.setMaximum(3);

		hypoSliderValue = new JSlider();
		hypoSliderValue.setMinorTickSpacing(10);
		hypoSliderValue.setValue(40);
		hypoSliderValue.setSnapToTicks(true);
		hypoSliderValue.setPaintTicks(true);
		hypoSliderValue.setPaintLabels(true);
		hypoSliderValue.setMajorTickSpacing(100);
		hypoSliderValue.setEnabled(false);
		horizontalBox_3.add(hypoSliderValue);

		Box horizontalBox_4 = Box.createHorizontalBox();
		horizontalBox_4.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_4);

		chckbxSynonyms = new JCheckBox("Synonyms");
		chckbxSynonyms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				MainFrame self = MainFrame.this;
				self.synonymsSlider.setEnabled(chckbxSynonyms.isSelected() && chckbxSynonyms.isEnabled());
				self.synoSliderValue.setEnabled(chckbxSynonyms.isSelected() && chckbxSynonyms.isEnabled() && selectedAlgoIndex==3);

			}
		});
		horizontalBox_4.add(chckbxSynonyms);

		Component horizontalStrut = Box.createHorizontalStrut(29);
		horizontalBox_4.add(horizontalStrut);

		synonymsSlider = new JSlider();
		synonymsSlider.setMajorTickSpacing(1);
		synonymsSlider.setSnapToTicks(true);
		synonymsSlider.setPaintTicks(true);
		synonymsSlider.setPaintLabels(true);
		synonymsSlider.setMaximum(3);
		synonymsSlider.setValue(0);
		synonymsSlider.setEnabled(false);
		horizontalBox_4.add(synonymsSlider);

		synoSliderValue = new JSlider();
		synoSliderValue.setMinorTickSpacing(10);
		synoSliderValue.setValue(90);
		synoSliderValue.setSnapToTicks(true);
		synoSliderValue.setPaintTicks(true);
		synoSliderValue.setPaintLabels(true);
		synoSliderValue.setMajorTickSpacing(100);
		synoSliderValue.setEnabled(false);
		horizontalBox_4.add(synoSliderValue);
		
		synoSliderValue.setLabelTable( labelTable );
		hypoSliderValue.setLabelTable( labelTable );
		
		Box horizontalBox_8 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_8);
		
		allWordsRadioButton = new JRadioButton("Include all words");
		allWordsRadioButton.setSelected(true);
		includeButtonGroup.add(allWordsRadioButton);
		horizontalBox_8.add(allWordsRadioButton);
		
		Component horizontalGlue_6 = Box.createHorizontalGlue();
		horizontalBox_8.add(horizontalGlue_6);
		
		Box horizontalBox_6 = Box.createHorizontalBox();
		horizontalBox_6.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_6);
		
		posTaggedWordsRadioButton = new JRadioButton("Include POS tagged words");
		includeButtonGroup.add(posTaggedWordsRadioButton);
		horizontalBox_6.add(posTaggedWordsRadioButton);
		

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox_6.add(horizontalGlue_3);

		Box horizontalBox_5 = Box.createHorizontalBox();
		horizontalBox_5.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_5);
		
		senseRelatedWordsRadioButton = new JRadioButton("Include sense related words");
		includeButtonGroup.add(senseRelatedWordsRadioButton);
		horizontalBox_5.add(senseRelatedWordsRadioButton);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalBox_5.add(horizontalGlue_4);

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
		verticalBox_4.setBounds(6, 103, 550, 276);
		getContentPane().add(verticalBox_4);

		model = new SortTableModel();
		model.addColumn("Article");
		model.addColumn("Status");
		model.addColumn("Score");
		model.addColumn("Sim");

		table = new JTable(model);
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(false);
		table.setGridColor(new Color(150, 150, 200));
		table.setDefaultEditor(Object.class, null);

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
		verticalBox_5.setBounds(6, 380, 550, 302);
		getContentPane().add(verticalBox_5);

		btnRemoveFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for (int i = rowIndices.length - 1; i >= 0; i--) {
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

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox_5.add(horizontalBox_1);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_1);

		JButton btnClearLog = new JButton("Clear log");
		horizontalBox_1.add(btnClearLog);
		btnClearLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUILog.clear();
			}
		});

		Box verticalBox_6 = Box.createVerticalBox();
		verticalBox_6.setBorder(new TitledBorder(null, "Algorithm",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_6.setBounds(568, 6, 196, 120);
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

		algoListener = new ChangeListener() {

			public void stateChanged(ChangeEvent changEvent) {
				AbstractButton aButton = (AbstractButton) changEvent
						.getSource();
				ButtonModel aModel = aButton.getModel();
				boolean armed = aModel.isArmed();
				boolean pressed = aModel.isPressed();
				boolean selected = aModel.isSelected();
				if (selected && !armed && !pressed) {
					int algorithmIndex = 0;
					for (Enumeration<AbstractButton> e = algorithmButtonGroup
							.getElements(); e.hasMoreElements();) {
						if (e.nextElement().isSelected()) {
							break;
						} else {
							algorithmIndex++;
						}
					}
					
					selectedAlgoIndex = algorithmIndex;

					switch (algorithmIndex) {
					case 0:
						setFlagsForFuzzy();
						break;
					case 1:
						setFlagsForLeven();

						break;
					case 2:
						setFlagsForOntho();

						break;
					case 3:
						setFlagsForOntho();

						break;

					default:
						break;
					}
				}
			}
		};
		rdbtnFuzzy.addChangeListener(algoListener);
		rdbtnNewRadioButton_4.addChangeListener(algoListener);
		rdbtnI.addChangeListener(algoListener);
		rdbtnNewRadioButton_onthology.addChangeListener(algoListener);

		progressBar = new JProgressBar();
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		progressBar.setBounds(568, 652, 346, 20);
		getContentPane().add(progressBar);

		stopButton = new JButton("Stop");
		stopButton.setEnabled(false);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame self = MainFrame.this;
				self.delegate.stopButtonPressed();
			}
		});
		stopButton.setBounds(568, 621, 89, 29);
		getContentPane().add(stopButton);

		Box verticalBox_7 = Box.createVerticalBox();
		verticalBox_7.setBorder(new TitledBorder(null, "Additional features", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_7.setBounds(760, 548, 154, 76);

		getContentPane().add(verticalBox_7);

		JCheckBox chckbxNormalizeStopwords = new JCheckBox("Normalize result");
		verticalBox_7.add(chckbxNormalizeStopwords);
		
		Box verticalBox_8 = Box.createVerticalBox();
		verticalBox_8.setBorder(new TitledBorder(null, "Sense relate", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_8.setBounds(769, 128, 145, 94);
		getContentPane().add(verticalBox_8);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Baseline");
		senseRelateButtonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setSelected(true);
		verticalBox_8.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnPerlWordnet = new JRadioButton("Perl WordNet");
		senseRelateButtonGroup.add(rdbtnPerlWordnet);
		verticalBox_8.add(rdbtnPerlWordnet);
		
		Box verticalBox_9 = Box.createVerticalBox();
		verticalBox_9.setBorder(new TitledBorder(null, "POS tagger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_9.setBounds(568, 128, 196, 94);
		getContentPane().add(verticalBox_9);
		
		JRadioButton rdbtnNone_1 = new JRadioButton("None");
		posTaggerButtonGroup.add(rdbtnNone_1);
		rdbtnNone_1.setSelected(true);
		verticalBox_9.add(rdbtnNone_1);
		
		JRadioButton rdbtnStanfordPosTagger = new JRadioButton("Stanford POS Tagger");
		posTaggerButtonGroup.add(rdbtnStanfordPosTagger);
		verticalBox_9.add(rdbtnStanfordPosTagger);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("Illinois POS Tagger");
		posTaggerButtonGroup.add(rdbtnNewRadioButton_5);
		verticalBox_9.add(rdbtnNewRadioButton_5);

		// verticalBox_7.add(table);

		// algorithmButtonGroup.

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				table.repaint();
			}
		});

		btnChooseFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(mf);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					model.setRowCount(0);
					File[] files = fc.getSelectedFiles();

					for (int i = 0; i < files.length; i++) {
						model.addRow(new Object[] { files[i].getName(),
								"", "-", "-" });

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
					computeButton.setEnabled(true);

				}
			}
		});

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.setLocation(200, 100);
		this.setSize(927, 740);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmClearCache = new JMenuItem("Clear cache");
		mntmClearCache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPCache cache = new JPCache();
				cache.clear();
			}
		});
		mnNewMenu.add(mntmClearCache);
		mnNewMenu.add(mntmQuit);

		computeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame self = MainFrame.this;

				clearModel();
				
				if (self.delegate != null) {
					JPConfiguration setup = new JPConfiguration();

					int algorithmIndex = 0;
					for (Enumeration<AbstractButton> e = algorithmButtonGroup
							.getElements(); e.hasMoreElements();) {
						AbstractButton button = e.nextElement();
						if (button.isSelected() && button.isEnabled()) {
							break;
						} else {
							algorithmIndex++;
						}
					}

					setup.setAlgorithmIndex(AlgorithmIndex
							.getAlgorithmIndexFromInt(algorithmIndex));
					
					int posTaggerIndex = 0;
					for (Enumeration<AbstractButton> e = posTaggerButtonGroup
							.getElements(); e.hasMoreElements();) {
						AbstractButton button = e.nextElement();
						if (button.isSelected() && button.isEnabled()) {
							break;
						} else {
							posTaggerIndex++;
						}
					}

					setup.setPosTaggerType(POSTaggerType
							.getPOSTaggerTypeFromInt(posTaggerIndex));
					
					int senseRelateIndex = 0;
					for (Enumeration<AbstractButton> e = senseRelateButtonGroup
							.getElements(); e.hasMoreElements();) {
						AbstractButton button = e.nextElement();
						if (button.isSelected() && button.isEnabled()) {
							break;
						} else {
							senseRelateIndex++;
						}
					}
					
					setup.setSenseRelateType(SenseRelateType
							.getSenseRelateTypeFromInt(senseRelateIndex));	
					
					
					if (chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled()) {
						JPIncludeHypernyms include = new JPIncludeHypernyms();
						include.setLayers(self.hypernymsSlider.getValue());
						setup.getIncludeTypes().add(include);
					}
					
					if (chckbxHyponyms.isSelected() && chckbxHyponyms.isEnabled()) {
						JPIncludeHyponyms include = new JPIncludeHyponyms();
						include.setLayers(self.hyponymsSlider.getValue());
						setup.getIncludeTypes().add(include);
					}
					
					if (chckbxSynonyms.isSelected() && chckbxSynonyms.isEnabled()) {
						JPIncludeSynonyms include = new JPIncludeSynonyms();
						include.setLayers(self.synonymsSlider.getValue());
						setup.getIncludeTypes().add(include);
					}
					
					int trimmerIndex = 0;
					for (Enumeration<AbstractButton> e = filterButtonGroup
							.getElements(); e.hasMoreElements();) {
						AbstractButton button = e.nextElement();
						if (button.isSelected() && button.isEnabled()) {
							break;
						} else {
							trimmerIndex++;
						}
					}
					
					setup.setTrimmerType(TrimmerType.getTrimmerTypeFromInt(trimmerIndex));


					int stemmerIndex = 0;
					for (Enumeration<AbstractButton> e = stemmerButtonGroup
							.getElements(); e.hasMoreElements();) {
						AbstractButton button = e.nextElement();
						if (button.isSelected() && button.isEnabled()) {
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
						AbstractButton button = e.nextElement();
						if (button.isSelected() && button.isEnabled()) {
							break;
						} else {
							includeIndex++;
						}
					}
					
					setup.setIncludeType(IncludeType.getIncludeTypeFromInt(includeIndex));
					
					
					File[] files = fc.getSelectedFiles();
					setup.setDocumentFiles(files);
					
					setup.setMainDocumentFile(sfc.getSelectedFile());
			
					self.delegate.computeAllButtonPressed(setup,0);
				} else {
					System.out.println("Delegate not set");
				}
			}
		});
		
		this.setVisible(true);
	}

	/**
	 * The Class MyFilter.
	 */
	class MyFilter extends javax.swing.filechooser.FileFilter {
		
		/* (non-Javadoc)
		 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
		 */
		public boolean accept(File file) {
			String filename = file.getName();
			return filename.endsWith(".txt");
		}

		/* (non-Javadoc)
		 * @see javax.swing.filechooser.FileFilter#getDescription()
		 */
		public String getDescription() {
			return "*.txt";
		}
	}

	/**
	 * Gets the panel.
	 *
	 * @return the panel
	 */
	public JTextPane getPanel() {
		return panel;
	}

	/**
	 * Gets the text pane.
	 *
	 * @return the text pane
	 */
	public JTextPane getTextPane() {
		return textPane;
	}

	/**
	 * Sets the progress.
	 *
	 * @param progress the new progress
	 */
	public void setProgress(int progress) {
		progressBar.setValue(progress);
	}
	
	/**
	 * Update status for document.
	 *
	 * @param document the document
	 */
	public void updateStatusForDocument(JPDocument document) {

		Vector<Object> vector = model.getDataVector();

		for (int i = 0; i < vector.size(); i++) {
			Vector<Object> v = (Vector<Object>) vector.get(i);

			if (document != null
					&& v.get(0).equals(document.getDocumentTitle())) {
				JPDocumentProgressType progressType = document
						.getProgressType();

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
				((AbstractTableModel) table.getModel()).fireTableDataChanged();

				break;
			}
		}

	}

	/**
	 * Enable stop button.
	 */
	public void enableStopButton() {
		stopButton.setEnabled(true);
		computeButton.setEnabled(false);
	}

	/**
	 * Disable stop button.
	 */
	public void disableStopButton() {
		stopButton.setEnabled(false);
		computeButton.setEnabled(true);
	}
	
	/**
	 * Clear model.
	 */
	public void clearModel() {
		Vector<Object> vector = model.getDataVector();
		
		for (int i = 0; i < vector.size(); i++) {
			Vector<Object> v = (Vector<Object>) vector.get(i);
			v.set(1, "");
			v.set(2, "-");
			v.set(3, "-");

			vector.set(i, v);
		}
		
		((AbstractTableModel)table.getModel()).fireTableDataChanged();

	}

	/**
	 * The listener interface for receiving selection events.
	 * The class that is interested in processing a selection
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSelectionListener<code> method. When
	 * the selection event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SelectionEvent
	 */
	private class SelectionListener implements ListSelectionListener {
		
		/** The table. */
		JTable table;

		// It is necessary to keep the table since it is not possible
		// to determine the table from the event's source
		/**
		 * Instantiates a new selection listener.
		 *
		 * @param table the table
		 */
		SelectionListener(JTable table) {
			this.table = table;
		}

		/* (non-Javadoc)
		 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e) {
			// If cell selection is enabled, both row and column change events
			// are fired
			if (e.getSource() == table.getSelectionModel()
					&& table.getRowSelectionAllowed()) {
				// Column selection changed
				btnRemoveFiles.setEnabled(true);
				rowIndices = table.getSelectedRows();
			} else if (e.getSource() == table.getColumnModel()
					.getSelectionModel() && table.getColumnSelectionAllowed()) {
			}

			if (e.getValueIsAdjusting()) {
				// The mouse button has not yet been released
			}
		}
	}

	/**
	 * The Class MyComparator.
	 */
	class MyComparator implements Comparator {
		
		/** The column. */
		protected String column;
		
		/** The index. */
		protected int index;

		/**
		 * Instantiates a new my comparator.
		 *
		 * @param column the column
		 * @param index the index
		 */
		public MyComparator(String column, int index) {
			this.column = column;
			this.index = index;
		}

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
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

	/**
	 * The Class SortTableModel.
	 */
	class SortTableModel extends DefaultTableModel {
		
		/** The sort col. */
		protected int sortCol = 0;

		/** The is sort asc. */
		protected boolean isSortAsc = true;

		/** The m_result. */
		protected int m_result = 0;
		
		/** The columns count. */
		protected int columnsCount = 1;

		/**
		 * Instantiates a new sort table model.
		 */
		public SortTableModel() {
		}

		/**
		 * The listener interface for receiving column events.
		 * The class that is interested in processing a column
		 * event implements this interface, and the object created
		 * with that class is registered with a component using the
		 * component's <code>addColumnListener<code> method. When
		 * the column event occurs, that object's appropriate
		 * method is invoked.
		 *
		 * @see ColumnEvent
		 */
		class ColumnListener extends MouseAdapter {
			
			/** The table. */
			protected JTable table;

			/**
			 * Instantiates a new column listener.
			 *
			 * @param t the t
			 */
			public ColumnListener(JTable t) {
				table = t;
			}

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			public void mouseClicked(MouseEvent e) {
				TableColumnModel colModel = table.getColumnModel();
				int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
				String column = table.getColumnName(columnModelIndex);
				int modelIndex = colModel.getColumn(columnModelIndex)
						.getModelIndex();

				Vector<Object> tempVector = (Vector<Object>) model
						.getDataVector().clone();
				Collections.sort(model.getDataVector(), new MyComparator(
						column, modelIndex));
				if (tempVector.equals(model.getDataVector())) {
					Collections.reverse(model.getDataVector());
				}
				table.tableChanged(new TableModelEvent(SortTableModel.this));
				table.repaint();
			}
		}
	}

	/**
	 * Sets the flags for leven.
	 */
	private void setFlagsForLeven() {
		chckbxHypernyms.setEnabled(false);
		chckbxHyponyms.setEnabled(false);
		chckbxSynonyms.setEnabled(false);
		senseRelatedWordsRadioButton.setEnabled(false);
		allWordsRadioButton.setEnabled(false);
		posTaggedWordsRadioButton.setEnabled(false);
	}

	/**
	 * Sets the flags for fuzzy.
	 */
	private void setFlagsForFuzzy() {
		chckbxHypernyms.setEnabled(true);
		chckbxHyponyms.setEnabled(true);
		chckbxSynonyms.setEnabled(true);
		senseRelatedWordsRadioButton.setEnabled(true);
		allWordsRadioButton.setEnabled(true);
		posTaggedWordsRadioButton.setEnabled(true);
	}

	/**
	 * Sets the flags for term f.
	 */
	private void setFlagsForTermF() {
		chckbxHypernyms.setEnabled(true);
		chckbxHyponyms.setEnabled(true);
		chckbxSynonyms.setEnabled(true);
		senseRelatedWordsRadioButton.setEnabled(true);
		allWordsRadioButton.setEnabled(true);
		posTaggedWordsRadioButton.setEnabled(true);
	}

	/**
	 * Sets the flags for ontho.
	 */
	private void setFlagsForOntho() {
		chckbxHypernyms.setEnabled(true);
		chckbxHyponyms.setEnabled(true);
		chckbxSynonyms.setEnabled(true);
		senseRelatedWordsRadioButton.setEnabled(true);
		allWordsRadioButton.setEnabled(true);
		posTaggedWordsRadioButton.setEnabled(true);
	}
}
