package Test;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import Parser.JPStringParser;

public class JPStringParserTest extends DataProviderTest {
	
  @Test(dataProvider = "fileDataProvider")
  public void parse(File file, int expectedWordCount, String expectedString) {
	  JPStringParser parser = new JPStringParser();
	  JPDocument document = new JPDocument();
	  parser.parse(document, expectedString);
	  
	  Assert.assertEquals(document.getNumberOfWords(), expectedWordCount);
	  
	  for (JPWord word : document.getAllWords()) {
		  Assert.assertNotEquals(word.getValue(), "");
	  }
	  
	  for (JPSentence sentence : document.getSentenceArray()) {
		  Assert.assertNotEquals(sentence.getWords().size(), 0);
	  }
  }
}
