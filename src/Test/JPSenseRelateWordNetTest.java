package Test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Model.SettingsManager;
import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import SenseRelate.JPSenseRelateWordNet;

public class JPSenseRelateWordNetTest extends DataProviderTest {

	@BeforeTest
	public void setup() {
		SettingsManager.SharedInstance.loadSettings();
	}
	
	@Test(dataProvider = "generate-documentSense")
	public void senseRelate(JPDocument document, Object[][] expectedSenses) {
		JPSenseRelateWordNet senseRelate = new JPSenseRelateWordNet();
		JPDocument senseRelatedDocument = senseRelate.senseRelate(document);

		int sentenceIndex = 0;
		for (JPSentence sentence : senseRelatedDocument.getSentenceArray()) {
			Object[] senses = expectedSenses[sentenceIndex];
			int wordIndex = 0;
			for (JPWord word : sentence.getWords()) {
				Integer sense = (Integer) senses[wordIndex];

				Assert.assertEquals(sense.intValue(), word.getSenseIndex());

				wordIndex++;
			}
			sentenceIndex++;
		}
	}

	@Test(dataProvider = "generate-documentSensePOSTagged")
	public void senseRelatePOStagged(JPDocument document, Object[][] expectedSenses) {
		JPSenseRelateWordNet senseRelate = new JPSenseRelateWordNet();
		JPDocument senseRelatedDocument = senseRelate.senseRelate(document);

		int sentenceIndex = 0;
		for (JPSentence sentence : senseRelatedDocument.getSentenceArray()) {
			Object[] senses = expectedSenses[sentenceIndex];
			int wordIndex = 0;
			for (JPWord word : sentence.getWords()) {
				Integer sense = (Integer) senses[wordIndex];

				Assert.assertEquals(sense.intValue(), word.getSenseIndex());

				wordIndex++;
			}
			sentenceIndex++;
		}
	}

}
