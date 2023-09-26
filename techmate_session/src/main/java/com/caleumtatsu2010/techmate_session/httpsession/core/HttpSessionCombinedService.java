//package com.caleumtatsu2010.techmate_session.httpsession.core;
//
//import com.caleumtatsu2010.techmate_session.httpsession.config.HttpSessionSelfConfigurator;
//import com.caleumtatsu2010.techmate_session.httpsession.config.SessionConfigPath;
//import lombok.NoArgsConstructor;
//
//import javax.servlet.http.HttpSession;
//
//public class HttpSessionCombinedService {
//
//	private HttpSessionSelfConfigurator httpSessionSelfConfigurator = null;
//	private HttpSessionInitializerOperators httpSessionInitializerOperators = null;
//
//	public Object HttpGetSelfSession(String sessionConfigPath, HttpSession httpSession, String attributeName) {
//		this.httpSessionSelfConfigurator = new HttpSessionSelfConfigurator(sessionConfigPath);
//		this.httpSessionInitializerOperators = new HttpSessionInitializerOperators(this.httpSessionSelfConfigurator, httpSession);
//		return this.httpSessionInitializerOperators.getSessionAttribute(attributeName);
//	}
//}
