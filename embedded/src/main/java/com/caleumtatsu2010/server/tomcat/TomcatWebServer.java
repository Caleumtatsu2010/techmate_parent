//package com.caleumtatsu2010.server.tomcat;
//
//import java.io.File;
//
//import com.caleumtatsu2010.server.WebServer;
//import org.apache.catalina.Context;
//import org.apache.catalina.LifecycleException;
//import org.apache.catalina.WebResourceRoot;
//import org.apache.catalina.startup.Tomcat;
//import org.apache.catalina.webresources.DirResourceSet;
//import org.apache.catalina.webresources.StandardRoot;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class TomcatWebServer implements WebServer {
//
//	private TomcatServerConfiguration tomcatServerConfiguration;
//	private static final Logger LOGGER = LoggerFactory.getLogger(TomcatWebServer.class);
//	private Tomcat tomcat = null;
//
//	public TomcatWebServer() {
//		this.tomcatServerConfiguration = new TomcatServerConfiguration();
//		this.tomcat = tomcatServerConfiguration.getTomcatServerInfo();
//	}
//
//	public void loadServer() {
//		tomcatServerConfiguration.readServerInfo();
//	}
//
//	@Override
//	public void initialize() {
//		loadServer();
//	}
//
//	private Context context(Tomcat tomcat, TomcatServerInfo tomcatServerInfo) {
//		Context context = tomcat.addWebapp(tomcatServerInfo.getContextPath(), tomcatServerInfo.getDocBase());
//		File classes = new File(tomcatServerInfo.getAdditionWebInfClasses());
//		String base = classes.getAbsolutePath();
//		WebResourceRoot resources = new StandardRoot(context);
//		resources.addPreResources(new DirResourceSet(resources, tomcatServerInfo.getWebAppMount(), base, tomcatServerInfo.getInternalPath()));
//		context.setResources(resources);
//
//		return context;
//	}
//
//	@Override
//	public void start() {
//		try {
//			tomcat.start();
//		} catch (LifecycleException exception) {
//			LOGGER.error("{}", exception.getMessage());
//			LOGGER.error("Exit...");
//			System.exit(1);
//		}
//		LOGGER.info("Application started with URL {}.", tomcatServerConfiguration.getTomcatServerInfo().getHost() + ":" + tomcatServerConfiguration.getTomcatServerInfo().getPort() + tomcatServerConfiguration.getTomcatServerInfo().getContextPath());
//		LOGGER.info("Hit Ctrl + D or C to stop it...");
//		tomcat.getServer().await();
//
//	}
//
//	@Override
//	public void stop() {
//
//	}
//
//}
