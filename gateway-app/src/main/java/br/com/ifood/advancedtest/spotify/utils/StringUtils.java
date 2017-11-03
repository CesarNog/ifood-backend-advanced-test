package br.com.ifood.advancedtest.spotify.utils;

public class StringUtils {
	/**
	 * Checking if a given string is valid
	 * <p>
	 * A string is considered valid if it is not null nor empty
	 *
	 * @param str the string to be checked
	 * @return a flag which indicates if the string is valid
	 */
	public static boolean isValid(String str) {
		return str != null && !str.isEmpty();
	}
}
