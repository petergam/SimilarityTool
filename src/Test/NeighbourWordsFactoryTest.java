package Test;

import org.testng.annotations.Test;

import Model.NeighbourWordsFactory;
import Objects.JPWord;

public class NeighbourWordsFactoryTest {

  @Test
  public void findNeighbours() {
	  NeighbourWordsFactory factory = new NeighbourWordsFactory();
	  
	  JPWord word = new JPWord();
	  word.setValue("horse");
	  factory.findNeighbours(word, 0, true, true);
  
	  for (Object[] o : word.getNeighbourWord()) {
		  System.out.println(o[0] + " score: " + o[2]);
	  }
  }
}
