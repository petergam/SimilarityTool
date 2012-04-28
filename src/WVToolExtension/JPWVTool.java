package WVToolExtension;

import java.util.Iterator;

import edu.udo.cs.wvtool.config.WVTConfiguration;
import edu.udo.cs.wvtool.generic.charmapper.WVTCharConverter;
import edu.udo.cs.wvtool.generic.inputfilter.WVTInputFilter;
import edu.udo.cs.wvtool.generic.loader.WVTDocumentLoader;
import edu.udo.cs.wvtool.generic.stemmer.WVTStemmer;
import edu.udo.cs.wvtool.generic.tokenizer.WVTTokenizer;
import edu.udo.cs.wvtool.generic.wordfilter.WVTWordFilter;
import edu.udo.cs.wvtool.main.WVTFileInputList;
import edu.udo.cs.wvtool.main.WVTool;
import edu.udo.cs.wvtool.util.TokenEnumeration;
import edu.udo.cs.wvtool.util.WVToolException;

public class JPWVTool extends WVTool{

	public JPWVTool(boolean arg0) {
		super(arg0);
	}
	
	public void loadFileInputList(WVTFileInputList fileInputList, WVTConfiguration config) {
		
        WVTDocumentLoader loader = null;
        WVTInputFilter infilter = null;
        WVTCharConverter charConverter = null;
        WVTTokenizer tokenizer = null;
        WVTWordFilter wordFilter = null;
        WVTStemmer stemmer = null;
		
        Iterator<?> inList = fileInputList.getEntries();

        // Get through the list
        while (inList.hasNext()) {

            JPWVTDocumentInfo d = (JPWVTDocumentInfo) inList.next();
            
            try {

                loader = (WVTDocumentLoader) config.getComponentForStep(WVTConfiguration.STEP_LOADER, d);
                infilter = (WVTInputFilter) config.getComponentForStep(WVTConfiguration.STEP_INPUT_FILTER, d);
                charConverter = (WVTCharConverter) config.getComponentForStep(WVTConfiguration.STEP_CHAR_MAPPER, d);
                tokenizer = (WVTTokenizer) config.getComponentForStep(WVTConfiguration.STEP_TOKENIZER, d);
                wordFilter = (WVTWordFilter) config.getComponentForStep(WVTConfiguration.STEP_WORDFILTER, d);
                stemmer = (WVTStemmer) config.getComponentForStep(WVTConfiguration.STEP_STEMMER, d);
            	
                TokenEnumeration tokens = stemmer.stem(wordFilter.filter(tokenizer.tokenize(charConverter.convertChars(infilter.convertToPlainText(loader.loadDocument(d), d), d), d), d), d);
                
                while (tokens.hasMoreTokens()) {
                	String word = tokens.nextToken();
                	d.getWordsArrayList().add(word);
                	
                	if(d.getWordHashMap().containsKey(word)) {
                		Integer value = d.getWordHashMap().get(word);
                		d.getWordHashMap().put(word, value+1);
                	}else {
                		d.getWordHashMap().put(word, 1);
                	}
                	
                	d.setNumberOfWords(d.getNumberOfWords()+1);
                }

                loader.close(d);
                
            } catch (WVToolException e) {

            
            }

        }
		
	}

}
