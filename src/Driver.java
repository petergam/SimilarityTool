import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Model.Article;
import Model.ArticleManager;
import Utilities.Constants;
import Utilities.Log;
import View.MainFrame;

public class Driver {

	public static Log log;
	
	public static void main(String[] args) {

		ArticleManager articleManager = new ArticleManager();
				
		
		Constants.removeShortWords = false;
		
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
		
//		System.out.println("Article 1 length: " + article1.getWordsArrayList().size());
//		System.out.println("Article 2 length: " + article2.getWordsArrayList().size());
//		System.out.println("Levenstein distance " + compareValue);
//		System.out.println("Runtime: " + computeTime);
		
		
//		WordNetManager wnManager = WordNetManager.SharedInstance;
//		
//		IWord[] words =wnManager.getWordsFromString("car", POS.NOUN);
//		System.out.println("Lemma = " + words[0].getLemma());
//		IWord[] synonums = wnManager.getSynonyms(words[0]);
//		System.out.println("Synonyms: ");
//		for(IWord word : synonums) System.out.print(word.getLemma() + ", ");
//		System.out.println();
//		String[] stemmedWords = wnManager.getStemmedWords("laughed", POS.VERB);
//		System.out.print("Stemmed words: ");
//		for(String word : stemmedWords) System.out.print(word + ", ");
		
		MainFrame mainFrame = new MainFrame();
		log = new Log(mainFrame.panel);
		
		File file1 = new File("Texts/TW2.txt");
		try {
			Scanner scan = new Scanner(file1);
			System.out.println("LOL " + scan.next());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Article article1 = articleManager.getArticleFromFile(file1);
		
		Log.nLog("Sentence count: "+article1.getSentences().length);
		Log.nLog("First word: "+article1.getSentences()[0].getWords()[0].getText());

		File file2 = new File("Texts/TW2.txt");
		Article article2 = articleManager.getArticleFromFile(file2);
		
		int commonWordCount = 0;
		for (String word : article1.getWordsArrayList()) {
			if(article2.getWordHashMap().get(word)!=null) {
				commonWordCount++;
			}
		}
		
		Log.nLog("Number of common Words: "+commonWordCount);
		
	}


}
