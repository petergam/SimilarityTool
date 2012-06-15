package Utilities;

import javax.swing.JTextPane;

/**
 * The Class GUILog.
 * Logs text to the GUI
 */
public class GUILog {

	/** The log. */
	private static JTextPane log = null;
	
	/**
	 * Setup the logger.
	 *
	 * @param logPane the new up logger
	 */
	public static void setupLogger(JTextPane logPane) {
		log = logPane;
	}
	
	/**
	 * New line log
	 *
	 * @param s the string that should be logged
	 */
	public static void nLog(String s){
		if(log!=null) {
			log.setText(log.getText() + s + "\n");
		} else {
			System.out.println(s);
		}
	}
	
	/**
	 * Log.
	 *
	 * @param s the string that should be logged
	 */
	public static void log(String s){
		if(log!=null) {
			log.setText(log.getText() + "" + s);
		} else {
			System.out.print(s);
		}
	}
	
	/**
	 * Gets the text of the logger.
	 *
	 * @return the text
	 */
	public static  String getText(){
		if(log != null) {
			return log.getText();
		} else {
			return "";
		}
	}
	
	/**
	 * Clear the log.
	 */
	public static void clear(){
		if(log!=null) {
			log.setText("");
		}
	}
	
}
