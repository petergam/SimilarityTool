import java.io.File;

import Algorithms.FuzzySim;
import Model.Article;
import Model.ArticleManager;
import Model.WordNetManager;
import Utilities.Constants;
import Utilities.Log;
import View.MainFrame;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.POS;

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
//		
//		System.out.println("Article 1 length: " + article1.getWordsArrayList().size());
//		System.out.println("Article 2 length: " + article2.getWordsArrayList().size());
//		System.out.println("Levenstein distance " + compareValue);
//		System.out.println("Runtime: " + computeTime);
		
//		System.out.println("Article 1 length: " + article1.getWordsArrayList().size());
//		System.out.println("Article 2 length: " + article2.getWordsArrayList().size());
//		System.out.println("Levenstein distance " + compareValue);
//		System.out.println("Runtime: " + computeTime);
		
		
		WordNetManager wnManager = WordNetManager.SharedInstance;
		
		IWord[] words =wnManager.getWordsFromString("cat", POS.NOUN);
		System.out.println("Lemma = " + words[0].getLemma());
		IWord[] synonums = wnManager.getSynonyms(words[0]);
		System.out.println("Synonyms: ");
		for(IWord word : synonums) {System.out.print(word.getLemma() + ", ");
		System.out.println("Gloss: " + word.getSynset().getGloss());
		
		}
		System.out.println();
		String[] stemmedWords = wnManager.getStemmedWords("cars", POS.NOUN);
		System.out.print("Stemmed words: ");
		for(String word : stemmedWords) System.out.print(word + ", ");
		
		
		
		MainFrame mainFrame = new MainFrame();
		log = new Log(mainFrame.panel);
	
		File file1 = new File("Texts/Stirling2.txt");
		Article article1 = articleManager.getArticleFromFile(file1);
		
		File file2 = new File("Texts/Stirling1.txt");
		Article article2 = articleManager.getArticleFromFile(file2);
		
		File file3 = new File("Texts/HP1.txt");
		Article article3 = articleManager.getArticleFromFile(file3);
		
		File file4 = new File("Texts/TW1.txt");
		Article article4 = articleManager.getArticleFromFile(file4);
		
		File file5 = new File("Texts/TW2.txt");
		Article article5 = articleManager.getArticleFromFile(file5);
		
		File file6 = new File("Texts/TW3.txt");
		Article article6 = articleManager.getArticleFromFile(file6);
		
//		Log.nLog("Sentence count: "+article1.getSentences().length);
//		Log.nLog("First word: "+article1.getSentences()[0].getWords()[0].getText());


		
//		int commonWordCount = 0;
//		for (String word : article1.getWordsArrayList()) {
//			if(article2.getWordHashMap().get(word)!=null) {
//				commonWordCount++;
//			}
//		}
//		
//		Log.nLog("Number of common Words: "+commonWordCount);
		
//		Log.nLog("Computing levenshtein distance:");
//		Log.nLog("The levenshtein distance is: " + LevenshteinDistance.computeLevenshteinDistance(article1, article2));
		Log.nLog("");
		Log.nLog("FuzzySim Test");
		Log.nLog("Stirling and stirling");
		FuzzySim.computeFuzzySim(article1, article2);
		Log.nLog("Stirling and HP");
		FuzzySim.computeFuzzySim(article1, article3);
		Log.nLog("Stirling and TW");
		FuzzySim.computeFuzzySim(article1, article4);
		Log.nLog("Stirling and HP");
		FuzzySim.computeFuzzySim(article2, article3);
		Log.nLog("Stirling and TW");
		FuzzySim.computeFuzzySim(article2, article4);
		Log.nLog("TW and HP");
		FuzzySim.computeFuzzySim(article3, article4);
		Log.nLog("HP and TW");
		FuzzySim.computeFuzzySim(article3, article5);
		Log.nLog("HP and TW");
		FuzzySim.computeFuzzySim(article3, article6);
		Log.nLog("TW and TW");
		FuzzySim.computeFuzzySim(article4, article5);
		Log.nLog("Stirling and TW");
		FuzzySim.computeFuzzySim(article2, article5);
		Log.nLog("TW and TW");
		FuzzySim.computeFuzzySim(article4, article6);
		Log.nLog("TW and TW");
		FuzzySim.computeFuzzySim(article5, article6);
		Log.nLog("Stirling and TW");
		FuzzySim.computeFuzzySim(article1, article6);
	}


}
