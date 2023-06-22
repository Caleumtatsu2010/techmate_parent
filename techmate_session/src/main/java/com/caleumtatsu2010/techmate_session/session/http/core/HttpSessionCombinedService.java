package com.caleumtatsu2010.techmate_session.session.http.core;

import com.caleumtatsu2010.techmate_session.session.http.config.HttpSessionSelfConfigurator;
import com.caleumtatsu2010.techmate_session.session.http.config.SessionConfigPath;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpSession;

@NoArgsConstructor
public class HttpSessionCombinedService {
	
	public static Object HttpGetSelfSession(String attributeName, HttpSession httpSession) {
		HttpSessionSelfConfigurator httpSessionSelfConfigurator = new HttpSessionSelfConfigurator(SessionConfigPath.path);
		HttpSessionInitializerOperators httpSessionInitializerOperators = new HttpSessionInitializerOperators(httpSessionSelfConfigurator, httpSession);
		return httpSessionInitializerOperators.getSessionAttribute(attributeName);
	}
}
