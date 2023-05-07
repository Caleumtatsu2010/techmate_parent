//package com.caleumtatsu2010.server.tomcat;
//
//import com.caleumtatsu2010.server.configuration.ServerConfiguration;
//import com.caleumtatsu2010.server.configuration.ServerConfigurationInfo;
//import com.caleumtatsu2010.utility.file.common.Path;
//import com.caleumtatsu2010.utility.file.properties.Utils;
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.catalina.startup.Tomcat;
//
//import java.util.Properties;
//
//@Getter
//@Setter
//public class TomcatServerConfiguration implements ServerConfiguration {
//
//	private TomcatServerInfo tomcatServerInfo = null;
//
//	public void loadServerInfo(String default_host, String default_port, String defaultContextPath, String docBase, String additionWebInfClasses, String webAppMount, String internalPath) {
//		this.tomcatServerInfo = new TomcatServerInfo(default_host, default_port, defaultContextPath, docBase, additionWebInfClasses, webAppMount, internalPath);
//	}
//
//	@Override
//	public void readServerInfo() {
//		Properties prop = Utils.loadProp(Path.tomcatConfigProperties);
//		String default_host = prop.getProperty(ServerConfigurationInfo.hostPropName);
//		String default_port = prop.getProperty(ServerConfigurationInfo.portPropName);
//		String defaultContextPath = prop.getProperty(ServerConfigurationInfo.contextPathPropName);
//		String docBase = prop.getProperty(ServerConfigurationInfo.docBasePropName);
//		String additionWebInfClasses = prop.getProperty(ServerConfigurationInfo.additionWebInfClassesPropName);
//		String webAppMount = prop.getProperty(ServerConfigurationInfo.webAppMountPropName);
//		String internalPath = prop.getProperty(ServerConfigurationInfo.internalPathPropName);
//		loadServerInfo(default_host, default_port, defaultContextPath, docBase, additionWebInfClasses, webAppMount, internalPath);
//	}
//
//	@Override
//	public void getServerInstant(Object obj) {
//		Tomcat tomcat = (Tomcat) obj;
//		tomcat.setHostname(this.tomcatServerInfo.getHost());
//		tomcat.getHost().setAppBase(this.tomcatServerInfo.getDocBase());
//		tomcat.setPort(Integer.parseInt(this.tomcatServerInfo.getPort()));
//		tomcat.getConnector();
//	}
//
//
//}
