package com.caleumtatsu2010.techmate_session.session.http.core;

import com.caleumtatsu2010.techmate_session.session.http.HttpSessionInstance;

import java.util.List;

public interface HttpSessionInitializer extends HttpSessionInstance {
	
	public void setSessionAttribute(String attributeName, Object o);
	
	public Object getSessionAttribute(String attributeName);
	
	public List<Object> getAllSessionAttributes();
	
	public void removeSession(String attributeName);
	
	public void sessionInvalidate();
	
}
