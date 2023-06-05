package com.caleumtatsu2010.techmate_session.http.operator;

import com.caleumtatsu2010.techmate_session.http.HttpSessionInstance;

import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class HttpSessionInitializer implements HttpSessionInstance {
	
	protected HttpSession httpSession;
	
	@Override
	public void initialize(HttpSession session) {
		this.httpSession = session;
	}
	
	abstract protected void setSessionAttribute(String attributeName, Object o);
	
	abstract protected Object getSessionAttribute(String attributeName);
	
	abstract protected List<Object> getAllSessionAttributes();
}
