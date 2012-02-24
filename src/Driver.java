import java.io.File;


import Algorithms.LevenshteinDistance;
import Model.Article;
import Model.ArticleManager;


public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArticleManager articleManager = new ArticleManager();
		
		
		File file1 = new File("test.txt");
		Article article1 = articleManager.getArticleFromFile(file1);
		
		File file2 = new File("test2.txt");
		Article article2 = articleManager.getArticleFromFile(file2);
		
		int compareValue = LevenshteinDistance.computeLevenshteinDistance(article1, article2);
	}

}
