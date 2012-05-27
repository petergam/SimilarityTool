package Deprecated;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public enum POSTaggerManager {
    SharedInstance;

    private MaxentTagger tagger;
    
    private POSTaggerManager() {
    	//POS tagger
		try {
			tagger = new MaxentTagger("models/english-bidirectional-distsim.tagger");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		

		
    }
    
//    public Article tagFile(File file) {
//		try {
//			
//			List<List<HasWord>>  sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader(file)));
//			
//			Article article = new Article();
//			Model.Sentence[] ss = new Model.Sentence[sentences.size()];
//			
//			int index = 0;
//		    for (List<HasWord> sentence : sentences) {
//			      ArrayList<TaggedWord> tSentence = tagger.tagSentence(sentence);
//
//			      Model.Sentence s = new Model.Sentence();
//			      
//			      Word[] words = new Word[tSentence.size()];
//			      int i = 0;
//			      for(TaggedWord w : tSentence) {
////			    	  if(w.tag().equals("SYM")) {
////			    		  continue;
////			    	  }
//			    	  
//			    	  Word word = new Word();
//			    	  word.setText(w.value());
//			    	  word.setStyleFromTag(w.tag());
//			    	  words[i] = word;
//
//			    	  i++;
//			      }
//			      
//			      s.setWords(words);
//			      
//			      ss[index] = s;
//			      
//			      index++;
//			    }
//		    
//		    article.setSentences(ss);
//		    
//		    return article;
//		    
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	
//    	return null;
//    }
//    
//    public void test1() {
//		try {
//			
//			List<List<HasWord>>  sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader("Texts/HP1.txt")));
//			
//		    for (List<HasWord> sentence : sentences) {
//			      ArrayList<TaggedWord> tSentence = tagger.tagSentence(sentence);
//
//			      System.out.println(Sentence.listToString(tSentence, false));
//			    }
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }

    
}

