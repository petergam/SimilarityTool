package SenseRelate;

import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;

/**
 * The Class JPSenseRelateBaseline.
 * Sets the sense index to the most frequent in the SemCor corpus
 */
public class JPSenseRelateBaseline extends JPAbstractSenseRelate {

	/* (non-Javadoc)
	 * @see SenseRelate.JPSenseRelate#senseRelate(Objects.JPDocument)
	 */
	@Override
	public JPDocument senseRelate(JPDocument document) {
		document.setSenseTagged(true);
		for (JPSentence sentence : document.getSentenceArray())  {
			for (JPWord word : sentence.getWords()) {
				if (word.wordNetID != null) {
					word.setSenseIndex(1);
				} else {
					word.setSenseIndex(0);
				}
				
				
			}
		}
		return document;
	}

}
