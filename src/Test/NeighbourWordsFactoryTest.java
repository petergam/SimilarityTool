package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Model.NeighbourWordsFactory;
import Objects.JPWord;

public class NeighbourWordsFactoryTest extends DataProviderTest {

  @Test(dataProvider = "generate-neighbor")
  public void findNeighbours(String wordString, String[] expectedNeighbors) {
	  NeighbourWordsFactory factory = new NeighbourWordsFactory();
	  
	  JPWord word = new JPWord();
	  word.setValue(wordString);
	  factory.findNeighbours(word, 0, true, true);
  
	  for (Object[] o : word.getNeighbourWord()) {
		  boolean included = false;
		  for (String w : expectedNeighbors) {
			  if (w.equals(o[0])) {
				  included = true;
			  }
		  }
		  
		  Assert.assertEquals(included, true);
	  }
  }
}
