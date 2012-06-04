package Model;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import WVToolAdditions.JPSentence;
import WVToolExtension.JPWVTDocumentInfo;



public enum SenseRelateManager {
    SharedInstance;

    public void findSense(JPWVTDocumentInfo document) {
		try {
			
			for (JPSentence sentence : document.getSentenceArray()) {
				String sentenceString = sentence.getSentenceString();
				System.out.println(sentenceString);

		        String[] command = {"perl", "-I", "/opt/local/lib/perl5/site_perl/5.12.3/darwin-multi-2level",
		        		"-I", "/opt/local/lib/perl5/site_perl/5.12.3", "-I",
		        		"/opt/local/lib/perl5/vendor_perl/5.12.3/darwin-multi-2level", "-I",
		        		"/opt/local/lib/perl5/vendor_perl/5.12.3", "-I", "/opt/local/lib/perl5/5.12.3/darwin-multi-2level",
		        		"-I", "/opt/local/lib/perl5/5.12.3", "-I", "/opt/local/lib/perl5/site_perl", "-I",
		        		"/opt/local/lib/perl5/vendor_perl", "disambiguate.pl", "-j", sentenceString};
		        
				ProcessBuilder pb = new ProcessBuilder(command);
				pb.redirectErrorStream(true);
				Process proc = pb.start();
				StringWriter writer = new StringWriter();
				IOUtils.copy(proc.getInputStream(), writer);
				String jsonResponse = writer.toString();
				System.out.println(jsonResponse);
		        SenseRelation senseRelation = new Gson().fromJson(jsonResponse, SenseRelation.class);
		        System.out.println(senseRelation.getRelations().get(0));
				
			}
		} catch (IOException e) {
			System.out.println("Boom");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
