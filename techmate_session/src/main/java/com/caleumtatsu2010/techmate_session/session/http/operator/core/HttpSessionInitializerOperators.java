package com.caleumtatsu2010.techmate_session.session.http.operator.core;

import com.caleumtatsu2010.techmate_session.session.http.config.HttpSessionSelfConfigurator;
import com.caleumtatsu2010.utility.common.StringValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class HttpSessionInitializerOperators implements HttpSessionInitializer {
	
	private HttpSession httpSession;
	private HttpSessionSelfConfigurator config;
	
	public HttpSessionInitializerOperators(HttpSession httpSession, HttpSessionSelfConfigurator config) {
		this.httpSession = httpSession;
		this.config = config;
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
