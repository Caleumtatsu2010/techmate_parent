package com.caleumtatsu2010.techmate_session.session.http.config;

import com.caleumtatsu2010.utility.common.StringValidator;
import com.caleumtatsu2010.utility.file.properties.PropsUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Properties;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HttpSessionSelfConfigurator {
	
	private SessionConfig httpSessionConfig;
	
	public HttpSessionSelfConfigurator(String sessionConfigPath) {
		this.httpSessionConfig = readSessionConfig(sessionConfigPath, "maxInactiveInterval");
	}
	
	public static SessionConfig readSessionConfig(String sessionConfigPath, String propName) {
		Properties prop = PropsUtil.loadProp(sessionConfigPath);
		int maxInactiveInterval = StringValidator.safeParseInt(prop.getProperty(propName));
		return new SessionConfig(maxInactiveInterval);
	}
	
}
