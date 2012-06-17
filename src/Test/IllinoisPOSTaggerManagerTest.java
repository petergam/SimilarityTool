package Test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import POSTagger.JPPOSTaggerIllinois;

public class IllinoisPOSTaggerManagerTest extends DataProviderTest {

	private ArrayList<Double> successPercent = new ArrayList<Double>();
	
	@Test(dataProvider = "generate-sentences")
	public void tagSentence(JPDocument document, JPDocument expectedDocument) {
		JPPOSTaggerIllinois posTagger = new JPPOSTaggerIllinois();
		JPDocument posTaggedDocument = posTagger.tag(document);

		double overallFails = 0;

		for (int i = 0; i < posTaggedDocument.getSentenceArray().size(); i++) {
			JPSentence sentence = posTaggedDocument.getSentenceArray().get(i);
			JPSentence expectedSentence = expectedDocument.getSentenceArray()
					.get(i);

			for (int j = 0; j < sentence.getWords().size(); j++) {
				JPWord word = sentence.getWords().get(j);
				JPWord expectedWord = expectedSentence.getWords().get(j);

				Assert.assertEquals(word.getValue(), expectedWord.getValue());

				overallFails = word.getWordType()==expectedWord.getWordType() ? overallFails : overallFails+1;
				
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
		
		Reporter.log("Overall Illinois POS-tagger success percent: " + overallPercent);
	}
}
