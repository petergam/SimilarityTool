package Test;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Loader.JPDocumentLoaderPlainText;

public class JPDocumentLoaderPlainTextTest extends DataProviderTest {

  @Test(dataProvider = "fileDataProvider")
  public void load(File file, int expectedWordCount, String expectedString) {
	  
	  JPDocumentLoaderPlainText loader = new JPDocumentLoaderPlainText();
	  try {
		String textString = loader.load(file);
		Assert.assertEquals(textString, expectedString);
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  
}
