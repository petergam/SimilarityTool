package Utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

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
	
	
	/**
	 * Sha1.
	 *
	 * @param string the string to be hashed
	 * @return the sha1 hashed string
	 */
	public static String sha1(String string)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(string.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	/**
	 * Byte to hex.
	 *
	 * @param hash the hash
	 * @return the string
	 */
	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    return formatter.toString();
	}
}
