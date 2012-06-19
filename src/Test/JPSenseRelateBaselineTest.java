package Test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Model.SettingsManager;
import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import SenseRelate.JPSenseRelateBaseline;

public class JPSenseRelateBaselineTest extends DataProviderTest {

	@BeforeTest
	public void setup() {
		SettingsManager.SharedInstance.loadSettings();
	}
	
	private ArrayList<Double> successPercent = new ArrayList<Double>();
	
	@Test(dataProvider = "generate-documents")
	public void senseRelate(JPDocument document, JPDocument expectedDocument) {
		JPSenseRelateBaseline senseRelate = new JPSenseRelateBaseline();
		
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
		
		Reporter.log("Overall Baseline sense relate success percent: " + overallPercent);
	}
}
