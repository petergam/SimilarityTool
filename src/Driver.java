import java.io.File;

import Algorithms.LevenshteinDistance;
import Model.Article;
import Model.ArticleManager;
import Model.POSTaggerManager;
import Model.WordNetManager;
import Utilities.Constants;
import View.MainFrame;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.POS;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArticleManager articleManager = new ArticleManager();
				
		
		Constants.removeShortWords = false;
		
		File file1 = new File("Texts/TW2.txt");
		Article article1 = articleManager.getArticleFromFile(file1);
		
		System.out.println("Sentence count: "+article1.getSentences().length);
		System.out.println("First word: "+article1.getSentences()[0].getWords()[0].getText());

		File file2 = new File("Texts/Stirling1.txt");
		Article article2 = articleManager.getArticleFromFile(file2);
		
		int commonWordCount = 0;
		for (String word : article1.getWordsArrayList()) {
			if(article2.getWordHashMap().get(word)!=null) {
				commonWordCount++;
			}
		}
		
		System.out.println("Number of common Words: "+commonWordCount);
		
//		
//		File file2 = new File("Texts/test2.txt");
//		Article article2 = articleManager.getArticleFromFile(file2);
//		
//		long time1 = System.currentTimeMillis();
//		
//		int compareValue = LevenshteinDistance.computeLevenshteinDistance(article1, article2);
//		
//		long time2 = System.currentTimeMillis();
//
//		long computeTime = time2- time1;
//		
//		System.out.println("Article 1 length: " + article1.getWordsArrayList().size());
//		System.out.println("Article 2 length: " + article2.getWordsArrayList().size());
//		System.out.println("Levenstein distance " + compareValue);
//		System.out.println("Runtime: " + computeTime);
//		
//		
//		System.out.println("Article 1 length: " + article1.getWordsArrayList().size());
//		System.out.println("Article 2 length: " + article2.getWordsArrayList().size());
//		System.out.println("Levenstein distance " + compareValue);
//		System.out.println("Runtime: " + computeTime);
		
		
		WordNetManager wnManager = WordNetManager.SharedInstance;
		
		IWord[] words =wnManager.getWordsFromString("car", POS.NOUN);
		System.out.println("Lemma = " + words[0].getLemma());
		IWord[] synonums = wnManager.getSynonyms(words[0]);
		System.out.println("Synonyms: ");
		for(IWord word : synonums) System.out.print(word.getLemma() + ", ");
		System.out.println();
		String[] stemmedWords = wnManager.getStemmedWords("laughed", POS.VERB);
		System.out.print("Stemmed words: ");
		for(String word : stemmedWords) System.out.print(word + ", ");
		
		MainFrame mainFrame = new MainFrame();


	}


}
