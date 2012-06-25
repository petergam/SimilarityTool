package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import Stemmer.JPStemmerPorter;

public class JPStemmerPorterTest extends DataProviderTest {

	@Test(dataProvider = "generate-documentStemmingPorter")
	public void stem(JPDocument document, Object[][] expectedStemming) {
		JPStemmerPorter stemmer = new JPStemmerPorter();
		JPDocument stemmedDocument = stemmer.stem(document);
		
		  int sentenceIndex = 0;
		  for (JPSentence sentence : stemmedDocument.getSentenceArray()) {
			  Object[] stemmings = expectedStemming[sentenceIndex];
			  int wordIndex = 0;
			  for (JPWord word : sentence.getWords()) {
				  String stemming = (String) stemmings[wordIndex];
				  
				  Assert.assertEquals(stemming, word.getValue());
				  
				  wordIndex++;
			  }
			  sentenceIndex++;
		  }
	}
}
