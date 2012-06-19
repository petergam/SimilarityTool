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
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class OntoPopup extends AbstractAlgorithmPopupFrame {

	/** The hypernyms slider. */
	private JSlider hypernymsSlider;
	
	/** The synonyms slider. */
	private JSlider synonymsSlider;
	
	/** The chckbx hypernyms. */
	private JCheckBox chckbxHypernyms;
	
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
		this.setSize(new Dimension(354, 686));
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

		btnClose.setBounds(223, 629, 129, 29);
		getContentPane().add(btnClose);
		
		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Concept inclusion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		verticalBox_2.setBounds(6, 123, 346, 494);
		getContentPane().add(verticalBox_2);

		getContentPane().add(verticalBox_2);

		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("0.0"));
		labelTable.put(new Integer(100), new JLabel("1.0"));
		
		Box verticalBox_5 = Box.createVerticalBox();
		verticalBox_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_2.add(verticalBox_5);
								
										Box horizontalBox_2 = Box.createHorizontalBox();
										verticalBox_5.add(horizontalBox_2);
										horizontalBox_2.setBorder(new EmptyBorder(0, 0, 0, 0));
										
												chckbxHypernyms = new JCheckBox("Hypo/hypernym");
												chckbxHypernyms.addChangeListener(new ChangeListener() {
													public void stateChanged(ChangeEvent arg0) {
														hypernymsSlider.setEnabled(chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled());
														hyperValueSlider.setEnabled(chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled());
														hypoValueSlider.setEnabled(chckbxHypernyms.isSelected() && chckbxHypernyms.isEnabled());
													}
												});
												horizontalBox_2.add(chckbxHypernyms);
												
												Component horizontalGlue_1 = Box.createHorizontalGlue();
												horizontalBox_2.add(horizontalGlue_1);
																						
																						Box horizontalBox_7 = Box.createHorizontalBox();
																						verticalBox_5.add(horizontalBox_7);
																										
																										Component horizontalStrut_2 = Box.createHorizontalStrut(25);
																										horizontalBox_7.add(horizontalStrut_2);
																										
																										JLabel lblNewLabel = new JLabel("Layers");
																										horizontalBox_7.add(lblNewLabel);
																										
																										Component horizontalStrut_4 = Box.createHorizontalStrut(175);
																										horizontalBox_7.add(horizontalStrut_4);
																								
																										hypernymsSlider = new JSlider();
																										horizontalBox_7.add(hypernymsSlider);
																										hypernymsSlider.setMinimum(1);
																										hypernymsSlider.setMajorTickSpacing(1);
																										hypernymsSlider.setSnapToTicks(true);
																										hypernymsSlider.setPaintLabels(true);
																										hypernymsSlider.setPaintTicks(true);
																										hypernymsSlider.setAlignmentX(Component.RIGHT_ALIGNMENT);
																										hypernymsSlider.setEnabled(false);
																										hypernymsSlider.setMaximum(2);
																										hypernymsSlider.setValue(0);
																						
																						Box horizontalBox_9 = Box.createHorizontalBox();
																						verticalBox_5.add(horizontalBox_9);
																						
																						Component horizontalStrut_6 = Box.createHorizontalStrut(25);
																						horizontalBox_9.add(horizontalStrut_6);
																						
																						JLabel lblHyponymSim = new JLabel("Hyponym sim");
																						horizontalBox_9.add(lblHyponymSim);
																						
																						Component horizontalStrut_3 = Box.createHorizontalStrut(130);
																						horizontalBox_9.add(horizontalStrut_3);
																						
																						hyperValueSlider = new JSlider();
																						horizontalBox_9.add(hyperValueSlider);
																						hyperValueSlider.setValue(40);
																						hyperValueSlider.setMinorTickSpacing(10);
																						hyperValueSlider.setPaintLabels(true);
																						hyperValueSlider.setMajorTickSpacing(100);
																						hyperValueSlider.setSnapToTicks(true);
																						hyperValueSlider.setEnabled(false);
																						hyperValueSlider.setPaintTicks(true);
																						
																						 hyperValueSlider.setLabelTable(labelTable);
																				
																						Box horizontalBox_3 = Box.createHorizontalBox();
																						verticalBox_5.add(horizontalBox_3);
																						horizontalBox_3.setBorder(new EmptyBorder(0, 0, 0, 0));
																						 
																						 Component horizontalStrut_7 = Box.createHorizontalStrut(25);
																						 horizontalBox_3.add(horizontalStrut_7);
																						 
																						 JLabel lblHypernymSim = new JLabel("Hypernym sim");
																						 horizontalBox_3.add(lblHypernymSim);
																						 
																						 Component horizontalStrut_8 = Box.createHorizontalStrut(125);
																						 horizontalBox_3.add(horizontalStrut_8);
																						 
																						 hypoValueSlider = new JSlider();
																						 horizontalBox_3.add(hypoValueSlider);
																						 hypoValueSlider.setValue(90);
																						 hypoValueSlider.setSnapToTicks(true);
																						 hypoValueSlider.setPaintTicks(true);
																						 hypoValueSlider.setPaintLabels(true);
																						 hypoValueSlider.setMinorTickSpacing(10);
																						 hypoValueSlider.setMajorTickSpacing(100);
																						 hypoValueSlider.setEnabled(false);
																						 hypoValueSlider.setLabelTable(labelTable);
		
		Box verticalBox_6 = Box.createVerticalBox();
		verticalBox_6.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_2.add(verticalBox_6);
		
				Box horizontalBox_4 = Box.createHorizontalBox();
				verticalBox_6.add(horizontalBox_4);
				horizontalBox_4.setBorder(new EmptyBorder(0, 0, 0, 0));
				
						chckbxSynonyms = new JCheckBox("Synonyms");
						chckbxSynonyms.addChangeListener(new ChangeListener() {
							public void stateChanged(ChangeEvent e) {
								synonymsSlider.setEnabled(chckbxSynonyms.isSelected() && chckbxSynonyms.isEnabled());
								synoValueSlider.setEnabled(chckbxSynonyms.isSelected() && chckbxSynonyms.isEnabled());
							}
						});
						horizontalBox_4.add(chckbxSynonyms);
						
						Component horizontalGlue = Box.createHorizontalGlue();
						horizontalBox_4.add(horizontalGlue);
										
										Box horizontalBox_1 = Box.createHorizontalBox();
										verticalBox_6.add(horizontalBox_1);
												
												Component horizontalStrut = Box.createHorizontalStrut(25);
												horizontalBox_1.add(horizontalStrut);
												
												JLabel label = new JLabel("Layers");
												horizontalBox_1.add(label);
												
												Component horizontalStrut_1 = Box.createHorizontalStrut(175);
												horizontalBox_1.add(horizontalStrut_1);
										
												synonymsSlider = new JSlider();
												horizontalBox_1.add(synonymsSlider);
												synonymsSlider.setMinimum(1);
												synonymsSlider.setMajorTickSpacing(1);
												synonymsSlider.setSnapToTicks(true);
												synonymsSlider.setPaintTicks(true);
												synonymsSlider.setPaintLabels(true);
												synonymsSlider.setMaximum(2);
												synonymsSlider.setValue(0);
												synonymsSlider.setEnabled(false);
										
										Box horizontalBox_10 = Box.createHorizontalBox();
										verticalBox_6.add(horizontalBox_10);
										
										Component horizontalStrut_9 = Box.createHorizontalStrut(25);
										horizontalBox_10.add(horizontalStrut_9);
										
										JLabel lblSynonymSim = new JLabel("Synonym sim");
										horizontalBox_10.add(lblSynonymSim);
										
										Component horizontalStrut_10 = Box.createHorizontalStrut(130);
										horizontalBox_10.add(horizontalStrut_10);
										
										synoValueSlider = new JSlider();
										horizontalBox_10.add(synoValueSlider);
										synoValueSlider.setValue(90);
										synoValueSlider.setSnapToTicks(true);
										synoValueSlider.setPaintTicks(true);
										synoValueSlider.setPaintLabels(true);
										synoValueSlider.setMinorTickSpacing(10);
										synoValueSlider.setMajorTickSpacing(100);
										synoValueSlider.setEnabled(false);
										synoValueSlider.setLabelTable(labelTable);
		
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
			super.settings.put("HyperHypoInclude", "true");
			super.settings.put("HyperHypoLayers", ""+(hypernymsSlider.getValue()-1));
			super.settings.put("HyperScore", ""+(hyperValueSlider.getValue()));
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
		if (settings.get("HyperHypoLayers") != null) {
			chckbxHypernyms.setSelected(true);
			hypernymsSlider.setEnabled(true);
			hypernymsSlider.setValue(Integer.parseInt(settings.get("HyperHypoLayers"))+1);
			hyperValueSlider.setValue(Integer.parseInt(settings.get("HyperScore")));
			hypoValueSlider.setValue(Integer.parseInt(settings.get("HypoScore")));
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
