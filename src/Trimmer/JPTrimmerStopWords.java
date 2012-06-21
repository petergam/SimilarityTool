package Trimmer;

import java.util.HashMap;

import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;

/**
 * The Class JPTrimmerStopWords.
 * Removes stop words form a document
 */
public class JPTrimmerStopWords extends JPAbstractTrimmer {

	/** The stop words. */
	private HashMap<String, Integer> stopWords = new HashMap<String, Integer>();
	
	
	/* (non-Javadoc)
	 * @see Trimmer.JPTrimmer#trim(Objects.JPDocument)
	 */
	@Override
	public JPDocument trim(JPDocument document) {
		for(JPSentence sentence : document.getSentenceArray()) {
			for (JPWord word : sentence.getWords()) {
				if (stopWords.containsKey(word.getValue().toLowerCase())) {
					word.setStopWord(true);
					
					document.setNumberOfWords(document.getNumberOfWords()-1);
				}
				
			}	
		}
		
		return document;
	}
	
	/**
	 * Instantiates a new jP trimmer stop words.
	 */
	public JPTrimmerStopWords() {
		
		stopWords.put("a", 1);
		stopWords.put("about", 1);
		stopWords.put("above", 1);
		stopWords.put("after", 1);
		stopWords.put("again", 1);
		stopWords.put("against", 1);
		stopWords.put("all", 1);
		stopWords.put("am", 1);
		stopWords.put("an", 1);
		stopWords.put("and", 1);
		stopWords.put("any", 1);
		stopWords.put("are", 1);
		stopWords.put("aren't", 1);
		stopWords.put("as", 1);
		stopWords.put("at", 1);
		stopWords.put("be", 1);
		stopWords.put("because", 1);
		stopWords.put("been", 1);
		stopWords.put("before", 1);
		stopWords.put("being", 1);
		stopWords.put("below", 1);
		stopWords.put("between", 1);
		stopWords.put("both", 1);
		stopWords.put("but", 1);
		stopWords.put("by", 1);
		stopWords.put("can't", 1);
		stopWords.put("cannot", 1);
		stopWords.put("could", 1);
		stopWords.put("couldn't", 1);
		stopWords.put("did", 1);
		stopWords.put("did't", 1);
		stopWords.put("do", 1);
		stopWords.put("does", 1);
		stopWords.put("doesn't", 1);
		stopWords.put("doing", 1);
		stopWords.put("don't", 1);
		stopWords.put("down", 1);
		stopWords.put("during", 1);
		stopWords.put("each", 1);
		stopWords.put("few", 1);
		stopWords.put("for", 1);
		stopWords.put("from", 1);
		stopWords.put("further", 1);
		stopWords.put("had", 1);
		stopWords.put("hadn't", 1);
		stopWords.put("has", 1);
		stopWords.put("hasn't", 1);
		stopWords.put("have", 1);
		stopWords.put("haven't", 1);
		stopWords.put("having", 1);
		stopWords.put("he", 1);
		stopWords.put("he'd", 1);
		stopWords.put("he'll", 1);
		stopWords.put("he's", 1);
		stopWords.put("her", 1);
		stopWords.put("here", 1);
		stopWords.put("here's", 1);
		stopWords.put("hers", 1);
		stopWords.put("herself", 1);
		stopWords.put("him", 1);
		stopWords.put("himself", 1);
		stopWords.put("his", 1);
		stopWords.put("how", 1);
		stopWords.put("how's", 1);
		stopWords.put("i", 1);
		stopWords.put("i'd", 1);
		stopWords.put("i'll", 1);
		stopWords.put("i'm", 1);
		stopWords.put("i've", 1);
		stopWords.put("if", 1);
		stopWords.put("in", 1);
		stopWords.put("into", 1);
		stopWords.put("is", 1);
		stopWords.put("isn't", 1);
		stopWords.put("it", 1);
		stopWords.put("it's", 1);
		stopWords.put("its", 1);
		stopWords.put("itself", 1);
		stopWords.put("let's", 1);
		stopWords.put("me", 1);
		stopWords.put("more", 1);
		stopWords.put("most", 1);
		stopWords.put("mustn't", 1);
		stopWords.put("my", 1);
		stopWords.put("myself", 1);
		stopWords.put("no", 1);
		stopWords.put("nor", 1);
		stopWords.put("not", 1);
		stopWords.put("of", 1);
		stopWords.put("off", 1);
		stopWords.put("on", 1);
		stopWords.put("once", 1);
		stopWords.put("only", 1);
		stopWords.put("or", 1);
		stopWords.put("other", 1);
		stopWords.put("ought", 1);
		stopWords.put("our", 1);
		stopWords.put("ours", 1);
		stopWords.put("ourselves", 1);
		stopWords.put("out", 1);
		stopWords.put("over", 1);
		stopWords.put("own", 1);
		stopWords.put("same", 1);
		stopWords.put("shan't", 1);
		stopWords.put("she", 1);
		stopWords.put("she'd", 1);
		stopWords.put("she'll", 1);
		stopWords.put("she's", 1);
		stopWords.put("should", 1);
		stopWords.put("should't", 1);
		stopWords.put("so", 1);
		stopWords.put("some", 1);
		stopWords.put("such", 1);
		stopWords.put("than", 1);
		stopWords.put("that", 1);
		stopWords.put("that's", 1);
		stopWords.put("the", 1);
		stopWords.put("their", 1);
		stopWords.put("theirs", 1);
		stopWords.put("them", 1);
		stopWords.put("themselves", 1);
		stopWords.put("then", 1);
		stopWords.put("there", 1);
		stopWords.put("there's", 1);
		stopWords.put("these", 1);
		stopWords.put("they", 1);
		stopWords.put("they'd", 1);
		stopWords.put("they'll", 1);
		stopWords.put("they're", 1);
		stopWords.put("they've", 1);
		stopWords.put("this", 1);
		stopWords.put("those", 1);
		stopWords.put("through", 1);
		stopWords.put("to", 1);
		stopWords.put("too", 1);
		stopWords.put("under", 1);
		stopWords.put("until", 1);
		stopWords.put("up", 1);
		stopWords.put("very", 1);
		stopWords.put("was", 1);
		stopWords.put("wasn't", 1);
		stopWords.put("we", 1);
		stopWords.put("we'd", 1);
		stopWords.put("we'll", 1);
		stopWords.put("we're", 1);
		stopWords.put("we've", 1);
		stopWords.put("were", 1);
		stopWords.put("weren't", 1);
		stopWords.put("what", 1);
		stopWords.put("what's", 1);
		stopWords.put("when", 1);
		stopWords.put("when's", 1);
		stopWords.put("where", 1);
		stopWords.put("where's", 1);
		stopWords.put("which", 1);
		stopWords.put("while", 1);
		stopWords.put("who", 1);
		stopWords.put("who's", 1);
		stopWords.put("whom", 1);
		stopWords.put("why", 1);
		stopWords.put("why's", 1);
		stopWords.put("with", 1);
		stopWords.put("won't", 1);
		stopWords.put("would", 1);
		stopWords.put("wouldn't", 1);
		stopWords.put("you", 1);
		stopWords.put("you'd", 1);
		stopWords.put("you'll", 1);
		stopWords.put("you're", 1);
		stopWords.put("you've", 1);
		stopWords.put("your", 1);
		stopWords.put("yours", 1);
		stopWords.put("yourself", 1);
		stopWords.put("yourselves", 1);

	}

}
