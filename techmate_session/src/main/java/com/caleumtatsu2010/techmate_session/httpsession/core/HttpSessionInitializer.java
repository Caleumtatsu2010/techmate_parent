package com.caleumtatsu2010.techmate_session.httpsession.core;

import com.caleumtatsu2010.techmate_session.httpsession.HttpSessionInstance;

import java.util.List;

public interface HttpSessionInitializer extends HttpSessionInstance {
	
	public void setSessionAttribute(String attributeName, Object o);
	
	public Object getSessionAttribute(String attributeName);
	
	public List<Object> getAllSessionAttributes();
	
	public void removeSessionAttribute(String attributeName);
	
	public void sessionInvalidate();
	
}
