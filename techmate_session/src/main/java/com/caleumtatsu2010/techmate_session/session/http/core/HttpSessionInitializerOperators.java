package com.caleumtatsu2010.techmate_session.session.http.core;

import com.caleumtatsu2010.techmate_session.session.http.config.HttpSessionSelfConfigurator;
import com.caleumtatsu2010.utility.common.StringValidator;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class HttpSessionInitializerOperators implements HttpSessionInitializer {
	
	private HttpSession httpSession;
	private HttpSessionSelfConfigurator config;
	
	public HttpSessionInitializerOperators(HttpSessionSelfConfigurator config, HttpSession httpSession) {
		this.config = config;
		this.httpSession = httpSession;
		if (this.httpSession != null)
			this.httpSession.setMaxInactiveInterval(config.getSessionConfig().getMaxInactiveInterval());
	}
	
	@Override
	public void setSessionAttribute(String attributeName, Object castObject) {
		this.httpSession.setAttribute(StringValidator.NulltoBlank(attributeName), castObject);
	}
	
	@Override
	public Object getSessionAttribute(String attributeName) {
		return this.httpSession.getAttribute(StringValidator.NulltoBlank(attributeName));
	}
	
	@Override
	public void removeSession(String attributeName) {
		httpSession.removeAttribute(StringValidator.NulltoBlank(attributeName));
	}
	
	@Override
	public void sessionInvalidate() {
		httpSession.invalidate();
	}
	
	@Override
	public List<Object> getAllSessionAttributes() {
		List<Object> list = new ArrayList<>();
		Enumeration keys = this.httpSession.getAttributeNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			list.add(httpSession.getValue(key));
		}
		return list;
	}
	
}
