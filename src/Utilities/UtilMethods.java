package Utilities;



/**
 * The Class UtilMethods.
 * Utility methods
 */
public class UtilMethods {

	/**
	 * Minimum. Finds minimum of provided parameters.
	 *
	 * @param a the first value
	 * @param b the second value
	 * @param c the third value
	 * @return the minimum value
	 */
	public static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }	
}
