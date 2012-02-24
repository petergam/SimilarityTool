import java.io.File;

import com.sun.org.apache.bcel.internal.classfile.Field;

import Algorithms.LevenshteinDistance;
import Model.Article;
import Model.ArticleManager;


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
	}

}
