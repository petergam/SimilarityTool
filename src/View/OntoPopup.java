package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OntoPopup extends AbstractAlgorithmPopupFrame {

	/** The hypernyms slider. */
	private JSlider hypernymsSlider;
	
	/** The hyponyms slider. */
	private JSlider hyponymsSlider;
	
	/** The synonyms slider. */
	private JSlider synonymsSlider;
	
	/** The chckbx hypernyms. */
	private JCheckBox chckbxHypernyms;
	
	/** The chckbx hyponyms. */
	private JCheckBox chckbxHyponyms;
	
	/** The chckbx synonyms. */
	private JCheckBox chckbxSynonyms;
	
	private JCheckBox chckbxThreshold;

	/** The all words radio button. */
	private JRadioButton allWordsRadioButton;
	
	/** The pos tagged words radio button. */
	private JRadioButton posTaggedWordsRadioButton;
	
	/** The sense related words radio button. */
	private JRadioButton senseRelatedWordsRadioButton;
	
	/** The pos tagger button group. */
	private final ButtonGroup posTaggerButtonGroup = new ButtonGroup();
	
	/** The sense relate button group. */
	private final ButtonGroup senseRelateButtonGroup = new ButtonGroup();
	
	/** The include button group. */
	private final ButtonGroup includeButtonGroup = new ButtonGroup();
	
	JRadioButton rdbtnNewRadioButton_2;
	
	JRadioButton rdbtnPerlWordnet;
	
	JRadioButton rdbtnNone_1;
	
	JRadioButton rdbtnStanfordPosTagger;
	
	JRadioButton rdbtnNewRadioButton_5;
	
	JSlider synoValueSlider;
	
	JSlider hypoValueSlider;
	
	JSlider hyperValueSlider;
	
	JSlider thres;
	
	public OntoPopup(HashMap<String, String> settings) {
		super(settings);
		setAlwaysOnTop(true);
		this.setSize(new Dimension(360, 541));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Ontology Based Query");
		System.out.println("Baam1");
		getContentPane().setLayout(null);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new TitledBorder(null, "POS tagger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox.setBounds(6, 16, 196, 102);
		getContentPane().add(verticalBox);

		rdbtnNone_1 = new JRadioButton("None");
		posTaggerButtonGroup.add(rdbtnNone_1);
		rdbtnNone_1.setSelected(true);
		verticalBox.add(rdbtnNone_1);

		rdbtnStanfordPosTagger = new JRadioButton("Stanford POS Tagger");
		posTaggerButtonGroup.add(rdbtnStanfordPosTagger);
		verticalBox.add(rdbtnStanfordPosTagger);

		rdbtnNewRadioButton_5 = new JRadioButton("Illinois POS Tagger");
		posTaggerButtonGroup.add(rdbtnNewRadioButton_5);
		verticalBox.add(rdbtnNewRadioButton_5);

		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new TitledBorder(null, "Sense relate", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_1.setBounds(207, 16, 145, 74);
		getContentPane().add(verticalBox_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Baseline");
		senseRelateButtonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setSelected(true);
		verticalBox_1.add(rdbtnNewRadioButton_2);

		rdbtnPerlWordnet = new JRadioButton("Perl WordNet");
		senseRelateButtonGroup.add(rdbtnPerlWordnet);
		verticalBox_1.add(rdbtnPerlWordnet);
		
		JButton btnClose = new JButton("Close and Save");
		super.close = btnClose;
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setSettings();
				setVisible(false);
				dispose();	 
			}
		});

		btnClose.setBounds(225, 484, 129, 29);
		getContentPane().add(btnClose);
		
		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Concept inclusion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		verticalBox_2.setBounds(6, 123, 346, 349);
		getContentPane().add(verticalBox_2);

		getContentPane().add(verticalBox_2);

		Box horizontalBox_7 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_7);

		Component horizontalGlue_5 = Box.createHorizontalGlue();
		horizontalBox_7.add(horizontalGlue_5);

		JLabel lblNewLabel = new JLabel("Layers");
		horizontalBox_7.add(lblNewLabel);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(60);
		horizontalBox_7.add(horizontalStrut_4);
		
		JLabel lblNewLabel_1 = new JLabel("Similarity");
		horizontalBox_7.add(lblNewLabel_1);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(35);
		horizontalBox_7.add(horizontalStrut_3);

		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_2);

		chckbxHypernyms = new JCheckBox("Hypernyms");
		chckbxHypernyms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				hypernymsSlider.setEnabled(chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled());
				hyperValueSlider.setEnabled(chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled());
			}
		});
		horizontalBox_2.add(chckbxHypernyms);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox_2.add(horizontalGlue_2);

		Component horizontalStrut_2 = Box.createHorizontalStrut(22);
		horizontalBox_2.add(horizontalStrut_2);

		hypernymsSlider = new JSlider();
		hypernymsSlider.setMinimum(1);
		hypernymsSlider.setMajorTickSpacing(1);
		hypernymsSlider.setSnapToTicks(true);
		hypernymsSlider.setPaintLabels(true);
		hypernymsSlider.setPaintTicks(true);
		hypernymsSlider.setAlignmentX(Component.RIGHT_ALIGNMENT);
		hypernymsSlider.setEnabled(false);
		hypernymsSlider.setMaximum(2);
		hypernymsSlider.setValue(0);

		horizontalBox_2.add(hypernymsSlider);
		
		hyperValueSlider = new JSlider();
		hyperValueSlider.setValue(40);
		hyperValueSlider.setMinorTickSpacing(10);
		hyperValueSlider.setPaintLabels(true);
		hyperValueSlider.setMajorTickSpacing(100);
		hyperValueSlider.setSnapToTicks(true);
		hyperValueSlider.setEnabled(false);
		hyperValueSlider.setPaintTicks(true);
		horizontalBox_2.add(hyperValueSlider);

		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("0.0"));
		labelTable.put(new Integer(100), new JLabel("1.0"));

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_3);

		chckbxHyponyms = new JCheckBox("Hyponyms");
		chckbxHyponyms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				hyponymsSlider.setEnabled(chckbxHyponyms.isSelected() && chckbxHyponyms.isEnabled());
				hypoValueSlider.setEnabled(chckbxHyponyms.isSelected() && chckbxHyponyms.isEnabled());
			}
		});
		horizontalBox_3.add(chckbxHyponyms);

		Component horizontalStrut_1 = Box.createHorizontalStrut(26);
		horizontalBox_3.add(horizontalStrut_1);

		hyponymsSlider = new JSlider();
		hyponymsSlider.setMinimum(1);
		hyponymsSlider.setMajorTickSpacing(1);
		hyponymsSlider.setPaintTicks(true);
		hyponymsSlider.setSnapToTicks(true);
		hyponymsSlider.setPaintLabels(true);
		hyponymsSlider.setAlignmentX(Component.RIGHT_ALIGNMENT);
		hyponymsSlider.setEnabled(false);
		horizontalBox_3.add(hyponymsSlider);
		hyponymsSlider.setValue(0);
		hyponymsSlider.setMaximum(2);
		
		hypoValueSlider = new JSlider();
		hypoValueSlider.setValue(90);
		hypoValueSlider.setSnapToTicks(true);
		hypoValueSlider.setPaintTicks(true);
		hypoValueSlider.setPaintLabels(true);
		hypoValueSlider.setMinorTickSpacing(10);
		hypoValueSlider.setMajorTickSpacing(100);
		hypoValueSlider.setEnabled(false);
		horizontalBox_3.add(hypoValueSlider);

		Box horizontalBox_4 = Box.createHorizontalBox();
		horizontalBox_4.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_4);

		chckbxSynonyms = new JCheckBox("Synonyms");
		chckbxSynonyms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				synonymsSlider.setEnabled(chckbxSynonyms.isSelected() && chckbxSynonyms.isEnabled());
				synoValueSlider.setEnabled(chckbxSynonyms.isSelected() && chckbxSynonyms.isEnabled());
			}
		});
		horizontalBox_4.add(chckbxSynonyms);

		Component horizontalStrut = Box.createHorizontalStrut(29);
		horizontalBox_4.add(horizontalStrut);

		synonymsSlider = new JSlider();
		synonymsSlider.setMinimum(1);
		synonymsSlider.setMajorTickSpacing(1);
		synonymsSlider.setSnapToTicks(true);
		synonymsSlider.setPaintTicks(true);
		synonymsSlider.setPaintLabels(true);
		synonymsSlider.setMaximum(2);
		synonymsSlider.setValue(0);
		synonymsSlider.setEnabled(false);
		horizontalBox_4.add(synonymsSlider);
		
		synoValueSlider = new JSlider();
		synoValueSlider.setValue(90);
		synoValueSlider.setSnapToTicks(true);
		synoValueSlider.setPaintTicks(true);
		synoValueSlider.setPaintLabels(true);
		synoValueSlider.setMinorTickSpacing(10);
		synoValueSlider.setMajorTickSpacing(100);
		synoValueSlider.setEnabled(false);
		horizontalBox_4.add(synoValueSlider);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox);
		
		chckbxThreshold = new JCheckBox("Threshold");
		horizontalBox.add(chckbxThreshold);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(29);
		horizontalBox.add(horizontalStrut_5);
		
		thres = new JSlider();
		thres.setSnapToTicks(true);
		thres.setPaintTicks(true);
		thres.setPaintLabels(true);
		thres.setMinorTickSpacing(5);
		thres.setMajorTickSpacing(50);
		thres.setEnabled(false);
		horizontalBox.add(thres);
		
		chckbxThreshold.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				thres.setEnabled(chckbxThreshold.isSelected() && chckbxThreshold.isEnabled());
			}
		});
		
		Hashtable<Integer, JLabel> thresLabelTable = new Hashtable<Integer, JLabel>();
		thresLabelTable.put(new Integer(0), new JLabel("0.0"));
		thresLabelTable.put(new Integer(50), new JLabel("0.5"));
		thresLabelTable.put(new Integer(100), new JLabel("1.0"));
		
		 thres.setLabelTable(thresLabelTable);

		
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
		
		 hyperValueSlider.setLabelTable(labelTable);
		 synoValueSlider.setLabelTable(labelTable);
		 hypoValueSlider.setLabelTable(labelTable);

		if (settings != null) {
			loadSettings(settings);
		}
	}
	
	@Override
	public void setSettings(){
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
		System.out.println(posTaggerIndex);
		super.settings.put("PosIndex", ""+posTaggerIndex);
		
		
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
		super.settings.put("SenseIndex", ""+ senseRelateIndex);
		
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
		
		super.settings.put("IncludeIndex", ""+ includeIndex);
		
		if (chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled()) {
			super.settings.put("HyperInclude", "true");
			super.settings.put("HyperLayers", ""+(hypernymsSlider.getValue()-1));
			super.settings.put("HyperScore", ""+(hyperValueSlider.getValue()));

		}
		if (chckbxHyponyms.isSelected() && chckbxHyponyms.isEnabled()) {
			super.settings.put("HypoInclude", "true");
			super.settings.put("HypoLayers", ""+(hyponymsSlider.getValue()-1));
			super.settings.put("HypoScore", ""+(hypoValueSlider.getValue()));
		}
		if (chckbxSynonyms.isSelected() && chckbxSynonyms.isEnabled()) {
			super.settings.put("SynoInclude", "true");
			super.settings.put("SynoLayers", ""+(synonymsSlider.getValue()-1));
			super.settings.put("SynoScore", ""+(synoValueSlider.getValue()));
		}
		if (chckbxThreshold.isSelected() && chckbxThreshold.isEnabled()) {
			super.settings.put("Threshold", ""+(thres.getValue()));
		}
		System.out.println(super.settings.toString());
	}	
	
	@Override
	public void loadSettings(HashMap<String, String> settings){
		if (settings.get("IncludeIndex") != null) {
			switch (Integer.parseInt(settings.get("IncludeIndex"))) {
			case 0:
				includeButtonGroup.setSelected(allWordsRadioButton.getModel(), true);
				break;
			case 1:
				includeButtonGroup.setSelected(posTaggedWordsRadioButton.getModel(), true);
				break;
			case 2:
				includeButtonGroup.setSelected(senseRelatedWordsRadioButton.getModel(), true);
				break;
			default:
				break;
			}
		}
		if (settings.get("HypoLayers") != null) {
			System.out.println("Is here");
			chckbxHyponyms.setSelected(true);
			hyponymsSlider.setEnabled(true);
			hyponymsSlider.setValue(Integer.parseInt(settings.get("HypoLayers"))+1);
			hypoValueSlider.setValue(Integer.parseInt(settings.get("HypoScore")));

		}
		if (settings.get("HyperLayers") != null) {
			chckbxHypernyms.setSelected(true);
			hypernymsSlider.setEnabled(true);
			hypernymsSlider.setValue(Integer.parseInt(settings.get("HyperLayers"))+1);
			hyperValueSlider.setValue(Integer.parseInt(settings.get("HyperScore")));
		}
		if (settings.get("SynoLayers") != null) {
			chckbxSynonyms.setSelected(true);
			synonymsSlider.setEnabled(true);
			synonymsSlider.setValue(Integer.parseInt(settings.get("SynoLayers"))+1);
			hyperValueSlider.setValue(Integer.parseInt(settings.get("HyperScore")));
			synoValueSlider.setValue(Integer.parseInt(settings.get("SynoScore")));
		}
		if (settings.get("SenseIndex") != null) {
			switch (Integer.parseInt(settings.get("SenseIndex"))) {
			case 0:
				senseRelateButtonGroup.setSelected(rdbtnNewRadioButton_2.getModel(), true);
				break;
			case 1:
				senseRelateButtonGroup.setSelected(rdbtnPerlWordnet.getModel(), true);
				break;
			default:
				break;
			}
		}
		if (settings.get("PosIndex") != null) {
			switch (Integer.parseInt(settings.get("PosIndex"))) {
			case 0:
				posTaggerButtonGroup.setSelected(rdbtnNone_1.getModel(), true);
				break;
			case 1:
				posTaggerButtonGroup.setSelected(rdbtnStanfordPosTagger.getModel(), true);
				break;
			case 2:
				posTaggerButtonGroup.setSelected(rdbtnNewRadioButton_5.getModel(), true);
				break;
			default:
				break;
			}
		}
		if (settings.get("Threshold") != null) {
			chckbxThreshold.setSelected(true);
			thres.setEnabled(true);
			thres.setValue(Integer.parseInt(settings.get("Threshold")));
		}
	}
}
