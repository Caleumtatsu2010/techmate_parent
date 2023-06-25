package com.caleumtatsu2010.techmate_session.httpsession.core;

import com.caleumtatsu2010.techmate_session.httpsession.config.HttpSessionSelfConfigurator;
import com.caleumtatsu2010.techmate_session.httpsession.config.SessionConfigPath;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;

@NoArgsConstructor
public class HttpSessionCombinedService {
	
	public static Object HttpGetSelfSession(String attributeName, HttpSession httpSession) {
		HttpSessionSelfConfigurator httpSessionSelfConfigurator = new HttpSessionSelfConfigurator(SessionConfigPath.path);
		HttpSessionInitializerOperators httpSessionInitializerOperators = new HttpSessionInitializerOperators(httpSessionSelfConfigurator, httpSession);
		return httpSessionInitializerOperators.getSessionAttribute(attributeName);
	}
}
