package Algorithms;

import java.io.File;

import Utilities.UtilMethods;

public class TfIdf {

	public static float calculateTdIdf(File file) {
		
		String text = UtilMethods.fileToString(file);
		text = UtilMethods.removeStopWords(text);
		
		return 0;
	}	
}
