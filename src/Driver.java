import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.SimpleStemmer;


import Algorithms.LevenshteinDistance;
import Model.Article;
import Model.ArticleManager;
import Model.WordNetManager;


public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArticleManager articleManager = new ArticleManager();
		
		
		File file1 = new File("Texts/test.txt");
		Article article1 = articleManager.getArticleFromFile(file1);
		
		File file2 = new File("Texts/test2.txt");
		Article article2 = articleManager.getArticleFromFile(file2);
		
		long time1 = System.currentTimeMillis();
		
		int compareValue = LevenshteinDistance.computeLevenshteinDistance(article1, article2);
		
		long time2 = System.currentTimeMillis();

		long computeTime = time2- time1;
		
		System.out.println("Article 1 length: " + article1.getWordsArrayList().size());
		System.out.println("Article 2 length: " + article2.getWordsArrayList().size());
		System.out.println("Levenstein distance " + compareValue);
		System.out.println("Runtime: " + computeTime);
		
		
		WordNetManager wnManager = WordNetManager.SharedInstance;
		
		IWord[] words =wnManager.getWordsFromString("pirate", POS.NOUN);
		System.out.println("Lemma = " + words[0].getLemma());
		IWord[] synonums = wnManager.getSynonyms(words[0]);
		System.out.println("Synonyms:");
		for(IWord word : synonums) System.out.println(word.getLemma());
		String[] stemmedWords = wnManager.getStemmedWords("sleeping", POS.VERB);
		System.out.println("Stemmed words:");
		for(String word : stemmedWords) System.out.println(word);

	}


}
