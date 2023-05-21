package com.caleumtatsu2010.utility.servlet.request;

import com.caleumtatsu2010.utility.common.StringValidate;
import com.caleumtatsu2010.utility.object.reflect.Invoke;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RequestUlti {
	
	public static void mapRequestToObjectFieldNames(Map<String, Object> request, Object obj, List<String> attrNames) {
		try {
			List<String> attrNames = Invoke.getAllAttributeName(obj);
			List<String> attrTypes = Invoke.getAllAttributeType(obj);
			Object requestParamValue = new Object();
			for (int i = 0; i < attrNames.size(); i++) {
				switch (attrTypes.get(i)) {
					case "class java.lang.String":
						requestParamValue = StringValidate.NulltoBlank(request.getParameter(attrNames.get(i)));
						break;
					case "int":
						requestParamValue = StringValidate.safeParseInt(request.getParameter(attrNames.get(i)));
						break;
					case "double":
						requestParamValue = StringValidate.safeParseDouble(request.getParameter(attrNames.get(i)));
						break;
					default:
				}
				Invoke.invokeSetter(obj, attrNames.get(i), requestParamValue);
			}
		}
		
	}
