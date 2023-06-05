package com.caleumtatsu2010.techmate_session.http.operator;

import com.caleumtatsu2010.utility.common.StringValidator;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class HttpSessionInitializerOperators extends HttpSessionInitializer {
	
	@Override
	public void initialize(HttpSession session) {
		super.initialize(session);
	}
	
	@Override
	protected void setSessionAttribute(String attributeName, Object castObject) {
		super.httpSession.setAttribute(StringValidator.NulltoBlank(attributeName), castObject);
	}
	
	@Override
	protected Object getSessionAttribute(String attributeName) {
		return super.httpSession.getAttribute(StringValidator.NulltoBlank(attributeName));
	}
	
	@Override
	protected List<Object> getAllSessionAttributes() {
		List<Object> list = new ArrayList<>();
		Enumeration keys = super.httpSession.getAttributeNames();
		while (keys.hasMoreElements()){
			String key = (String)keys.nextElement();
			list.add(httpSession.getValue(key));
		}
		return list;
	}
}
