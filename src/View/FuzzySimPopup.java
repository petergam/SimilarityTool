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
import javax.swing.JSpinner;

public class FuzzySimPopup extends AbstractAlgorithmPopupFrame{
	
	/** The hypernyms slider. */
	private JSlider hypernymsSlider;
	
	private JSlider thres;
	
	/** The chckbx hypernyms. */
	private JCheckBox chckbxHypernyms;
	
	/** The chckbx synonyms. */
	private JCheckBox chckbxSynonyms;

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
	
	public FuzzySimPopup(HashMap<String, String> settings) {
		super(settings);
		setAlwaysOnTop(true);
		this.setSize(new Dimension(359, 494));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Fuzzy Similarity");
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
		verticalBox_1.setBounds(207, 16, 145, 102);
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

		btnClose.setBounds(223, 434, 129, 29);
		getContentPane().add(btnClose);
		
		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Concept inclusion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		verticalBox_2.setBounds(6, 207, 346, 215);
		getContentPane().add(verticalBox_2);

		getContentPane().add(verticalBox_2);

		Box horizontalBox_7 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_7);

		Component horizontalGlue_5 = Box.createHorizontalGlue();
		horizontalBox_7.add(horizontalGlue_5);

		JLabel lblNewLabel = new JLabel("Layers");
		horizontalBox_7.add(lblNewLabel);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(55);
		horizontalBox_7.add(horizontalStrut_3);

		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_2);

		chckbxHypernyms = new JCheckBox("Hyper/hyponyms");
		chckbxHypernyms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				hypernymsSlider.setEnabled(chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled());
			}
		});
		horizontalBox_2.add(chckbxHypernyms);

		Component horizontalStrut_2 = Box.createHorizontalStrut(85);
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

		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("0.0"));
		labelTable.put(new Integer(100), new JLabel("1.0"));

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_3);

		Box horizontalBox_4 = Box.createHorizontalBox();
		horizontalBox_4.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_4);

		chckbxSynonyms = new JCheckBox("Synonyms");
		horizontalBox_4.add(chckbxSynonyms);

		Component horizontalStrut = Box.createHorizontalStrut(240);
		horizontalBox_4.add(horizontalStrut);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox_2.add(verticalStrut);
		
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
		
		posTaggedWordsRadioButton = new JRadioButton("Include POS tagged words only");
		includeButtonGroup.add(posTaggedWordsRadioButton);
		horizontalBox_6.add(posTaggedWordsRadioButton);
		

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox_6.add(horizontalGlue_3);

		Box horizontalBox_5 = Box.createHorizontalBox();
		horizontalBox_5.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_2.add(horizontalBox_5);
		
		senseRelatedWordsRadioButton = new JRadioButton("Include sense related words only");
		includeButtonGroup.add(senseRelatedWordsRadioButton);
		horizontalBox_5.add(senseRelatedWordsRadioButton);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalBox_5.add(horizontalGlue_4);
		
		Box verticalBox_3 = Box.createVerticalBox();
		verticalBox_3.setBorder(new TitledBorder(null, "Threshold", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_3.setBounds(6, 121, 346, 74);
		getContentPane().add(verticalBox_3);
		
		thres = new JSlider();
		thres.setMinorTickSpacing(5);
		thres.setMajorTickSpacing(25);
		thres.setSnapToTicks(true);
		thres.setPaintTicks(true);
		thres.setPaintLabels(true);
		verticalBox_3.add(thres);
		
		Hashtable<Integer, JLabel> thresLabelTable = new Hashtable<Integer, JLabel>();
		thresLabelTable.put(new Integer(0), new JLabel("0.0"));
		thresLabelTable.put(new Integer(25), new JLabel("0.25"));
		thresLabelTable.put(new Integer(50), new JLabel("0.5"));
		thresLabelTable.put(new Integer(75), new JLabel("0.75"));
		thresLabelTable.put(new Integer(100), new JLabel("1.0"));
		
		 thres.setLabelTable(thresLabelTable);		
		
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
		
		super.settings.put("Threshold", ""+(thres.getValue()));
		
		if (chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled()) {
			super.settings.put("HyperHypoInclude", "true");
			super.settings.put("HyperHypoLayers", ""+(hypernymsSlider.getValue()-1));
		}
		else if (!chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled()) {
			super.settings.put("HyperHypoInclude", "false");
		}
		if (chckbxSynonyms.isSelected() && chckbxSynonyms.isEnabled()) {
			super.settings.put("SynoInclude", "true");
		}
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

		if (settings.get("HyperHypoInclude") != null) {
			if (settings.get("HyperHypoInclude").equals("true")) {
			chckbxHypernyms.setSelected(true);
			hypernymsSlider.setEnabled(true);
			hypernymsSlider.setValue(Integer.parseInt(settings.get("HyperHypoLayers"))+1);
			}
			else if (settings.get("HyperHypoInclude").equals("false")) {
				chckbxHypernyms.setSelected(false);
				hypernymsSlider.setEnabled(false);
			}
		}
		if (settings.get("SynoInclude") != null) {
			if (settings.get("SynoInclude").equals("true")) {
				chckbxSynonyms.setSelected(true);
			}
			else if (settings.get("SynoInclude").equals("false")) {
				chckbxSynonyms.setSelected(false);
			}
		}
		if (settings.get("Threshold") != null) {
			thres.setValue(Integer.parseInt(settings.get("Threshold")));
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
	}
}
