package Model;

import java.util.ArrayList;
import java.util.StringTokenizer;

import LBJ2.nlp.Word;
import LBJ2.nlp.seg.Token;
import Objects.JPSentence;
import Objects.JPWord;
import edu.illinois.cs.cogcomp.lbj.pos.POSTagger;

// TODO: Auto-generated Javadoc
/**
 * The Enum IllinoisPOSTaggerManager.
 */
public enum IllinoisPOSTaggerManager {
	
	/** The Shared instance. */
	SharedInstance;

	/**
	 * Tag sentence.
	 *
	 * @param sentence the sentence
	 * @return the jP sentence
	 */
	public JPSentence tagSentence(JPSentence sentence) {

		POSTagger tagger = new POSTagger();

		StringTokenizer tokenizer = new StringTokenizer(sentence.getSentenceString());

		ArrayList<Token> tokens = new ArrayList<Token>();
		Token lastToken = null;
		while (tokenizer.hasMoreElements()) {
			String tokenString = (String) tokenizer.nextElement();
			Word word = new Word(tokenString);
			Token token = new Token(word, lastToken, null);
			tokens.add(token);
			lastToken = token;
		}

		int index = 0;
		for (Token token : tokens) {
			JPWord word = sentence.getWords().get(index);
			String tag = tagger.discreteValue(token);
			word.setWordTypeFromTag(tag);
			word.setTag(tag);

			index ++;
		}
		
		sentence.setPOSTagged(true);
		return sentence;
	}
}
