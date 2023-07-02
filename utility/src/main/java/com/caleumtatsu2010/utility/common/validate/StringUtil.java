package com.caleumtatsu2010.utility.common.validate;

public class StringUtil {
	
	public static String subStringAfter(String str, String separator) {
		int sepPos = str.indexOf(separator);
		if (sepPos == -1) {
			return "";
		}
		return str.substring(sepPos + separator.length());
	}
}
