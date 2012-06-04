package SenseRelate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;

import Model.JPCache;
import Model.SettingsManager;
import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;
import Objects.SenseRelation;
import Utilities.Log;

import com.google.gson.Gson;

public class JPSenseRelateWordNet extends JPAbstractSenseRelate{

	@Override
    public JPDocument senseRelate(JPDocument document) {

		if (document.isSenseTagged()) {
			return document;
		}
		
		ExecutorService engine = Executors.newFixedThreadPool(4);
		
		for (final JPSentence sentence : document.getSentenceArray()) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						String sentenceString = sentence.getSentenceString();
						String[] paths = (String[]) SettingsManager.SharedInstance.getSettings().get(SettingsManager.PerlLibraryPathsKey);
						
						String[] commands = new String[4+(2*paths.length)];
						
						commands[0] = "perl";
						for (int i = 0; i < paths.length; i++) {
							int index = 2*i+1;
							commands[index] = "-I";
							commands[index+1] = paths[i];
						}
						
						commands[commands.length-3] = "SenseRelate.pl";
						commands[commands.length-2] = "-j";
						commands[commands.length-1] = sentenceString;

						ProcessBuilder pb = new ProcessBuilder(commands);
						pb.redirectErrorStream(true);
						Process proc = pb.start();
						StringWriter writer = new StringWriter();
						IOUtils.copy(proc.getInputStream(), writer);
						String jsonResponse = writer.toString();
						SenseRelation senseRelation = new Gson().fromJson(
								jsonResponse, SenseRelation.class);

						if (senseRelation.getSuccess() == 1) {
							for (int i = 0; i < sentence.getWords().size(); i++) {
								JPWord word = sentence.getWords().get(i);
								SenseRelation.Relation relation = senseRelation
										.getRelations().get(i);
								word.setSenseIndex(relation.getSenseIndex());
								word.setWordType(relation.getWordType());
								word.setSenseValue(relation.getNewWord());
							}

						} else {
							throw new RuntimeException(
									"Failed to sense relate with error: "
											+ senseRelation.getMessage());
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};

			engine.submit(runnable);

		}
		
		try {
			engine.shutdown();
			engine.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			
		}
		Log.nLog("Caching " + document.getDocumentTitle());
		document.setSenseTagged(true);
		JPCache cache = new JPCache();
        cache.cacheDocument(document);
		return document;
	}

}
