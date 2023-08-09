package com.caleumtatsu2010.techmate_session.httpsession.util;

import com.caleumtatsu2010.techmate_session.httpsession.core.HttpSessionInitOperators;
import com.caleumtatsu2010.utility.common.validate.StrValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HttpSessionUtil {
	
	public static void autoSetSessionAttribute(HttpSessionInitOperators httpSessionInitOperators, Object castObject) {
		String attributeName = castObject.getClass().getSimpleName();//attribute name as object name
		httpSessionInitOperators.setSessionAttribute(StrValidator.NulltoBlank(attributeName), castObject);
	}
	
}
