import java.io.File;

import com.sun.org.apache.bcel.internal.classfile.Field;

import Model.Article;
import Model.ArticleManager;


public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArticleManager articleManager = new ArticleManager();
		
		
		File file1 = new File("test.txt");
		Article article1 = articleManager.getArticleFromFile(file1);
		
		
	}

}
