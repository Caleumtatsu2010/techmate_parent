package com.caleumtatsu2010.techmate_session.httpsession.core;

import com.caleumtatsu2010.techmate_session.httpsession.config.HttpSessionSelfConfigurator;
import com.caleumtatsu2010.techmate_session.httpsession.util.HttpSessionUtil;
import com.caleumtatsu2010.utility.common.validate.StrValidator;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class HttpSessionInitOperators implements HttpSessionInit {
	
	private HttpSession httpSession;
	private HttpSessionSelfConfigurator config;
	
	public HttpSessionInitOperators(HttpSessionSelfConfigurator config, HttpSession httpSession) {
		this.config = config;
		this.httpSession = httpSession;
		if (this.httpSession != null) {
			this.httpSession.setMaxInactiveInterval(config.getSessionConfig().getMaxInactiveInterval());
		}
	}
	
	@Override
	public void setSessionAttribute(String attributeName, Object castObject) {
		this.httpSession.setAttribute(StrValidator.NulltoBlank(attributeName), castObject);
	}
	
	public void autoSetSessionAttribute(Object castObject) {
		String attributeName = castObject.getClass().getSimpleName();//attribute name as object name
		setSessionAttribute(attributeName, castObject);
	}
	
	@Override
	public Object getSessionAttribute(String attributeName) {
		return this.httpSession.getAttribute(StrValidator.NulltoBlank(attributeName));
	}
	
	public Object autoGetSessionAttribute(Object castObject) {
		String attributeName = "";
		try {
			attributeName = castObject.getClass().getSimpleName();//attribute name as object name
		} catch (NullPointerException e) {
			return null;
		}
		return getSessionAttribute(attributeName);
	}
	
	@Override
	public void removeSessionAttribute(String attributeName) {
		this.httpSession.removeAttribute(StrValidator.NulltoBlank(attributeName));
	}
	
	@Override
	public void sessionInvalidate() {
		if(httpSession != null){
			httpSession.invalidate();
		}
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
