package Utilities;

import javax.swing.JTextPane;

public class Log {

	private static JTextPane log = null;
	
	public static void setupLogger(JTextPane logPane) {
		log = logPane;
	}
	
	public static void nLog(String s){
		if(log!=null) {
			log.setText(log.getText() + s + "\n");
		} else {
			System.out.println(s);
		}
	}
	
	public static void log(String s){
		if(log!=null) {
			log.setText(log.getText() + "" + s);
		} else {
			System.out.print(s);
		}
	}
	
	public static  String getText(){
		if(log != null) {
			return log.getText();
		} else {
			return "";
		}
	}
	
	public static void clear(){
		if(log!=null) {
			log.setText("");
		}
	}
	
}
