package WVToolAdditions;

import java.io.Reader;

import WVToolExtension.JPWVTDocumentInfo;
import edu.udo.cs.wvtool.generic.tokenizer.WVTTokenizer;
import edu.udo.cs.wvtool.main.WVTDocumentInfo;
import edu.udo.cs.wvtool.util.TokenEnumeration;

public class ExtendedTokenizer implements WVTTokenizer, TokenEnumeration {

    /** The underlying character stream of the currently tokenized document */
    private Reader input;

    /**
     * The token, which is currently provided. This buffer is neccessary, to implement the semantic of TokenEnumeration
     */
    private String currentToken;

    private JPWVTDocumentInfo document;
    private int currentSentenceIndex = 0;
    
    public ExtendedTokenizer() {

        input = null;
        currentToken = null;
    }

    /**
     * @see edu.udo.cs.wvtool.generic.tokenizer.WVTTokenizer#tokenize(Reader, WVTDocumentInfo)
     */
    public TokenEnumeration tokenize(Reader source, WVTDocumentInfo d) {

    	this.document = (JPWVTDocumentInfo) d;
    	
        if (source != null) {

            input = source;
            readNextToken();
            
//            if(document.getSentenceArray().size()==0) {
//        		int wordIndex = 0;
//        		int sentenceIndex = 0;
//        		
//        		ArrayList<JPWord> words = new ArrayList<JPWord>();
//
//                while (hasMoreTokens()) {
//                	String value = nextToken();
//                	JPWord word = new JPWord();
//                	word.setValue(value);
//                	
//                	words.add(word);
//                	
//                	if(document.getSentenceIndexArray().size()>sentenceIndex) {
//
//                    	if (wordIndex == document.getSentenceIndexArray().get(sentenceIndex).intValue()+1) {
//                    		JPSentence sentence = new JPSentence();
//                    		
//                    		JPWord[] wordsArray = new JPWord[words.size()];
//                    		words.toArray(wordsArray);
//                    		sentence.setWords(wordsArray);
//                    		
//                    		document.getSentenceArray().add(sentence);                    		
//                    		sentenceIndex++;
//                    		
//                    		words = new ArrayList<JPWord>();
//            			}
//                	}
//                	
//                	if(document.getWordHashMap().containsKey(word)) {
//                		Integer count = document.getWordHashMap().get(word);
//                		document.getWordHashMap().put(value, count+1);
//                	}else {
//                		document.getWordHashMap().put(value, 1);
//                	}
//                	
//                	document.setNumberOfWords(document.getNumberOfWords()+1);
//                	wordIndex++;
//
//                }                
//            }
                        
            return this;

        } else
            return null;

    }

    /**
     * Read a token from the character stream and store it into currentToken. If there are no more tokens left store a null value.
     * 
     */
    private void readNextToken() {

        StringBuffer buf = new StringBuffer();
        boolean endReached = false;
        int in = 0;

        try {

            // Read from the stream, until a letter occurs

            in = input.read();
            char ch = (char) in;

            
            while ((in != -1) && (!Character.isLetter(ch) && !isNewSentenceSymbol(ch))) {
                in = input.read();
                ch = (char) in;
            }

            if (in != -1)
                buf.append(ch);

            // Read from the stream, util a non-letter occurs

            while ((in != -1) && (Character.isLetter(ch) || isNewSentenceSymbol(ch))) {

                in = input.read();
                ch = (char) in;

                if (Character.isLetter(ch))
                    buf.append(ch);
                
                if (isNewSentenceSymbol(ch)) {
                	document.getSentenceIndexArray().add(new Integer(currentSentenceIndex));
                	break;
                }
                

            }
        } catch (Exception e) {
            endReached = true;

        }

        if (in == -1)
            endReached = true;

                
        currentSentenceIndex++;
        
        if (endReached) {

            // If the stream ended with a non-empty token, this is the last
            // token, otherwise there is no more token.

            if (buf.length() > 0)
                currentToken = buf.toString();
            else
                currentToken = null;
            

            return;
        } else {

            // if the end of the stream has not been reached yet, simply store
            // the extracted token.
            currentToken = buf.toString();
            

            return;
        }
        


    }

    /**
     * @see edu.udo.cs.wvtool.util.TokenEnumeration#hasMoreTokens()
     */
    public boolean hasMoreTokens() {

        // If the current token does not equal the null value, then there is at
        // least this token left
        if (input != null)
            return (currentToken != null);
        else
            return false;
    }

    /**
     * @see edu.udo.cs.wvtool.util.TokenEnumeration#nextToken()
     */
    public String nextToken() {

        String result = null;

        // If unequal null, return the current token and read another one from
        // the stream

        if (currentToken != null) {
            result = currentToken;
            readNextToken();
        } else
            result = null;

        
        return result;
    }
    
    private boolean isNewSentenceSymbol(char ch) {
    	switch (ch) {
		case '.':
		case '?':
		case '!':
		case ':':
		case ';':
			return true;
		default:
			return false;
		}
    }

}

