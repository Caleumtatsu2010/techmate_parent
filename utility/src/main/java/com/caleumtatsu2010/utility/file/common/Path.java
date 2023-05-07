package com.caleumtatsu2010.utility.file.common;

public class Path {
	//project dir
	
	//    private static String dir = "D:\\stuff\\JAVA\\techmate";
	private static String dir = "C:\\stuff\\Projects\\techmate_parent\\techmate_core";

	private static String resources =  "\\src\\main\\resources";
	
	private static String resourceDir = dir + resources;
	
	public static String databaseProperties = resourceDir + "/properties/database.properties";
	
	public static String tomcatConfigProperties = dir + "\\embedded" + resources + "/tomcatConfig.properties";
}
