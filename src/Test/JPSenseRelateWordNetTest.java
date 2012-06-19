package Test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Model.JPCache;
import Model.SettingsManager;
import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import SenseRelate.JPSenseRelateWordNet;

public class JPSenseRelateWordNetTest extends DataProviderTest {

	@BeforeTest
	public void setup() {
		SettingsManager.SharedInstance.loadSettings();
		JPCache.SharedCache.loadCache(JPCache.sDefaultCacheName);
	}
	
	private ArrayList<Double> successPercent = new ArrayList<Double>();
	
	@Test(dataProvider = "generate-documentslight")
	public void senseRelate(JPDocument document, JPDocument expectedDocument) {
		JPSenseRelateWordNet senseRelate = new JPSenseRelateWordNet();
		
		JPDocument senseRelatedDocument = senseRelate.senseRelate(document);
		
		double overallFails = 0;

		for (int i = 0; i < senseRelatedDocument.getSentenceArray().size(); i++) {
			JPSentence sentence = senseRelatedDocument.getSentenceArray().get(i);
			JPSentence expectedSentence = expectedDocument.getSentenceArray()
					.get(i);

			for (int j = 0; j < sentence.getWords().size(); j++) {
				JPWord word = sentence.getWords().get(j);
				JPWord expectedWord = expectedSentence.getWords().get(j);

				Assert.assertEquals(word.getValue(), expectedWord.getValue());

				overallFails = word.getSenseIndex()==expectedWord.getSenseIndex() ? overallFails : overallFails+1;
			}
		}
		
		Double percent = 100-(overallFails/document.getNumberOfWords())*100;
		
		Reporter.log("Success percent for " + document.getDocumentTitle() + ": " + percent);
		
		successPercent.add(percent);
	}
	
	@AfterTest
	public void printResult() {
		
		double sum = 0.0;
		for (Double percent : successPercent) {
			sum += percent;
		}
		
		double overallPercent = sum/successPercent.size();
		
		Reporter.log("Overall WordNet sense relate success percent: " + overallPercent);
	}
}
