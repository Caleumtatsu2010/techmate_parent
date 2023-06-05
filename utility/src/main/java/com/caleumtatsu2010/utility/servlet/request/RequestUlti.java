package com.caleumtatsu2010.utility.servlet.request;

import com.caleumtatsu2010.utility.common.StringValidator;
import com.caleumtatsu2010.utility.object.reflect.ObjectUtilityInvoker;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RequestUlti {
	
	
	public static void mapRequestToObjectFieldNames(HttpServletRequest request, Object obj) {
		try {
			List<String> attrNames = ObjectUtilityInvoker.getAllAttributeName(obj);
			List<String> attrTypes = ObjectUtilityInvoker.getAllAttributeType(obj);
			Object requestParamValue = new Object();
			for (int i = 0; i < attrNames.size(); i++) {
				switch (attrTypes.get(i)) {
					case "class java.lang.String":
						requestParamValue = StringValidator.NulltoBlank(request.getParameter(attrNames.get(i)));
						break;
					case "int":
						requestParamValue = StringValidator.safeParseInt(request.getParameter(attrNames.get(i)));
						break;
					case "double":
						requestParamValue = StringValidator.safeParseDouble(request.getParameter(attrNames.get(i)));
						break;
					default:
				}
				ObjectUtilityInvoker.invokeSetter(obj, attrNames.get(i), requestParamValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
