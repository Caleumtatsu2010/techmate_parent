package com.caleumtatsu2010.server.tomcat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TomcatServerInfo {
	private String host = "";
	private String port;
	private String contextPath = "";
	private String docBase = "";
	private String additionWebInfClasses = "";
	private String webAppMount = "";
	private String internalPath = "";
}
