package com.caleumtatsu2010.utility.common.validate;

public class StringUtil {
	
	public static String subStringAfter(String str, String separator) {
		int sepPos = str.indexOf(separator);
		if (sepPos == -1) {
			return "";
		}
		return str.substring(sepPos + separator.length());
	}
	
	public static String subStringBefore(String str, String separator) {
		int pos = str.indexOf(separator);
		return pos >= 0 ? str.substring(0, pos) : str;
	}
	
}
