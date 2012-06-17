package Test;

import java.io.File;
import java.util.Iterator;

import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import edu.mit.jsemcor.element.IContext;
import edu.mit.jsemcor.element.ISentence;
import edu.mit.jsemcor.element.IWordform;
import edu.mit.jsemcor.main.IConcordance;
import edu.mit.jsemcor.main.IConcordanceSet;
import edu.mit.jsemcor.main.Semcor;

public class SemCorParser {

	
	public JPDocument[] parse(File file) {
		
		IConcordanceSet semcor = new Semcor(file);
		semcor.open();
        
		String[] concordStrings = new String[]{ "brown1", "brown2", "brownv" };
		
		int size = 0;
		for (String concordString : concordStrings) {
			IConcordance concord = semcor.get(concordString);
			size += concord.getContextIDs().size();

		}
		
		JPDocument[] documents = new JPDocument[size];

		int i=0;

		for (String concordString : concordStrings) {
			IConcordance concord = semcor.get(concordString);

			Iterator<IContext> iterator = concord.iterator();

			while (iterator.hasNext()) {
				IContext context = iterator.next();
				
				JPDocument document = new JPDocument();
				document.setDocumentTitle(context.getFilename());
				documents[i] = document;
				for (ISentence sentence : context.getSentences()) {
					JPSentence s = new JPSentence();
					
					for(IWordform wf : sentence.getWordList()){
						JPWord word = new JPWord();
						word.setValue(wf.getText());
						if (wf.getSemanticTag()!=null) {
							word.setSenseIndex(wf.getSemanticTag().getSenseNumber().get(0));

						}
						if (wf.getPOSTag() != null) {
							word.setTag(wf.getPOSTag().getValue());
							word.setWordTypeFromTag(word.getTag());
						}
						document.setNumberOfWords(document.getNumberOfWords()+1);
						s.getWords().add(word);
					}
					
					document.getSentenceArray().add(s);
				}
				i++;
			}
		}

		return documents;
	}
}
