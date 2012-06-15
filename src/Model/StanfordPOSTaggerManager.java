package Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Objects.JPSentence;
import Objects.JPWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * The Enum StanfordPOSTaggerManager.
 * Allows POS tagging using the Stanford POS Tagger
 */
public enum StanfordPOSTaggerManager {
    
    /** The Shared instance. */
    SharedInstance;

    /** The tagger. */
    private MaxentTagger tagger;
    
    /**
     * Instantiates a new stanford pos tagger manager.
     */
    private StanfordPOSTaggerManager() {
    	//POS tagger
		try {			
			tagger = new MaxentTagger("english-left3words-distsim.tagger");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * Tag sentence.
	 *
	 * @param sentence the sentence that should be POS-tagged
	 * @return the POS-tagged sentence
	 */
	public JPSentence tagSentence(JPSentence s) {
		
		List<HasWord> sentence = new ArrayList<HasWord>();
		
		for (JPWord word : s.getWords()) {
			HasWord w = new TaggedWord();
			w.setWord(word.getValue());
			sentence.add(w);
		}
		
		ArrayList<TaggedWord> tSentence = tagger.tagSentence(sentence);

		int index = 0;
		for (TaggedWord w : tSentence) {
			
			JPWord word = s.getWords().get(index);
			word.setTag(w.tag());
			word.setWordTypeFromTag(w.tag());
			word.setSenseIndex(JPWord.SenseIndexUnkown);
			word.setSenseValue(null);
			index++;
		}

		s.setPOSTagged(true);
		return s;
	}
	
}

