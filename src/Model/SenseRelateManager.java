package Model;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

public enum SenseRelateManager {
    SharedInstance;

    public void findSense(String sentence) {
		try {
	        String[] command = {"perl", "-I", "/opt/local/lib/perl5/site_perl/5.12.3/darwin-multi-2level",
	        		"-I", "/opt/local/lib/perl5/site_perl/5.12.3", "-I",
	        		"/opt/local/lib/perl5/vendor_perl/5.12.3/darwin-multi-2level", "-I",
	        		"/opt/local/lib/perl5/vendor_perl/5.12.3", "-I", "/opt/local/lib/perl5/5.12.3/darwin-multi-2level",
	        		"-I", "/opt/local/lib/perl5/5.12.3", "-I", "/opt/local/lib/perl5/site_perl", "-I",
	        		"/opt/local/lib/perl5/vendor_perl", "disambiguate.pl", "-j", sentence};
	        
	        
			ProcessBuilder pb = new ProcessBuilder(command);
			pb.redirectErrorStream(true);
			Process proc = pb.start();

			StringWriter writer = new StringWriter();
			IOUtils.copy(proc.getInputStream(), writer);
			String jsonResponse = writer.toString();
			System.out.println(jsonResponse);				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
