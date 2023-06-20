package com.caleumtatsu2010.techmate_session.session.http.config;

import com.caleumtatsu2010.utility.common.StringValidator;
import com.caleumtatsu2010.utility.file.properties.PropsUtil;
import lombok.Getter;

import java.util.Properties;

@Getter
public class HttpSessionSelfConfigurator {
	
	private SessionConfig sessionConfig;
	
	public HttpSessionSelfConfigurator(String sessionConfigPath) {
		this.sessionConfig = readSessionConfig(sessionConfigPath, "maxInactiveInterval");
	}
	
	public SessionConfig readSessionConfig(String sessionConfigPath, String propName) {
		Properties prop = PropsUtil.loadProp(sessionConfigPath);
		int maxInactiveInterval = StringValidator.safeParseInt(prop.getProperty(propName));
		return new SessionConfig(maxInactiveInterval);
	}
	
	public static void main(String[] args) {
		HttpSessionSelfConfigurator httpSessionSelfConfigurator = new HttpSessionSelfConfigurator(SessionConfigPath.path);
	}
}
