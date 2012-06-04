package Deprecated;


public class JPWordLoaderCustom {

//	@Override
//	public TokenEnumeration load(TokenEnumeration tokens, JPWVTDocumentInfo d)
//			throws WVToolException {
//
//        if(d.getSentenceArray().size()==0) {
//    		int wordIndex = 0;
//    		int sentenceIndex = 0;
//    		
//    		ArrayList<JPWord> words = new ArrayList<JPWord>();
//
//            while (tokens.hasMoreTokens()) {
//            	String value = tokens.nextToken();
//            	JPWord word = new JPWord();
//            	word.setValue(value);
//            	
//            	words.add(word);
//            	
//            	if(d.getSentenceIndexArray().size()>sentenceIndex) {
//
//                	if (wordIndex == d.getSentenceIndexArray().get(sentenceIndex).intValue()+1) {
//                		JPSentence sentence = new JPSentence();
//                		
//                		JPWord[] wordsArray = new JPWord[words.size()];
//                		words.toArray(wordsArray);
//                		sentence.setWords(wordsArray);
//                		
//                		d.getSentenceArray().add(sentence);                    		
//                		sentenceIndex++;
//                		
//                		words = new ArrayList<JPWord>();
//        			}
//            	}
//            	
//            	if(d.getWordHashMap().containsKey(word)) {
//            		Integer count = d.getWordHashMap().get(word);
//            		d.getWordHashMap().put(value, count+1);
//            	}else {
//            		d.getWordHashMap().put(value, 1);
//            	}
//            	
//            	d.setNumberOfWords(d.getNumberOfWords()+1);
//            	wordIndex++;
//
//            }                
//        }
//
//        
//		return tokens;
//		
////		return null;
//	}

}
