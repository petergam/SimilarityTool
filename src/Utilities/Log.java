package Utilities;

import javax.swing.JTextPane;

public class Log {

	public static JTextPane log;
	
	public Log(JTextPane log){
		Log.log = log;
	}
	
	
	
	public static void nLog(String s){
		log.setText(log.getText() + s + "\n");
	}
	
	public static void log(String s){
		log.setText(log.getText() + "" + s);
	}
	
	public static  String getText(){
		return log.getText();
	}
	
	public static void clear(){
		log.setText("");
	}
	
}
