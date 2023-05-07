package com.caleumtatsu2010.utility.file.properties;


import java.io.*;

public class Utils {
	
	public static java.util.Properties loadProp(String filepath) {
		java.util.Properties prop = null;
		try (InputStream input = new FileInputStream(filepath)) {
			prop = new java.util.Properties();
			prop.load(input);
			return prop;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	
	public static String readDatabasePath(String propPath, String dbName) {
		java.util.Properties prop = null;
		try (InputStream input = new FileInputStream(propPath)) {
			prop = new java.util.Properties();
			prop.load(input);
			String databasePath = prop.getProperty(dbName);
			return databasePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

