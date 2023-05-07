//package com.caleumtatsu2010.server.tomcat;
//import java.io.File;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.LifecycleException;
//import org.apache.catalina.WebResourceRoot;
//import org.apache.catalina.startup.Tomcat;
//import org.apache.catalina.webresources.DirResourceSet;
//import org.apache.catalina.webresources.StandardRoot;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class TomcatEmbeddedContext {
//
//	private static Context context(Tomcat tomcat, TomcatServerInfo tomcatServerInfo) {
//		Context context = tomcat.addWebapp(tomcatServerInfo.getContextPath(), tomcatServerInfo.getDocBase());
//		File classes = new File(tomcatServerInfo.getAdditionWebInfClasses());
//		String base = classes.getAbsolutePath();
//		WebResourceRoot resources = new StandardRoot(context);
//		resources.addPreResources(new DirResourceSet(resources, WEB_APP_MOUNT, base, INTERNAL_PATH));
//		context.setResources(resources);
//
//		return context;
//	}
//}
