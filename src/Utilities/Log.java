package Utilities;

import javax.swing.JTextPane;

// TODO: Auto-generated Javadoc
/**
 * The Class Log.
 */
public class Log {

	/** The log. */
	private static JTextPane log = null;
	
	/**
	 * Sets the up logger.
	 *
	 * @param logPane the new up logger
	 */
	public static void setupLogger(JTextPane logPane) {
		log = logPane;
	}
	
	/**
	 * N log.
	 *
	 * @param s the s
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
	 * @param s the s
	 */
	public static void log(String s){
		if(log!=null) {
			log.setText(log.getText() + "" + s);
		} else {
			System.out.print(s);
		}
	}
	
	/**
	 * Gets the text.
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
	 * Clear.
	 */
	public static void clear(){
		if(log!=null) {
			log.setText("");
		}
	}
	
}
