package com.caleumtatsu2010.utility.servlet.request;

import com.caleumtatsu2010.utility.common.validate.StringValidator;
import com.caleumtatsu2010.utility.object.reflect.ObjectUtilityInvoker;
import com.caleumtatsu2010.utility.time.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RequestUlti {
	
	
	public static void mapRequestParamToObjByField(HttpServletRequest request, Object obj) {
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
					case "class java.sql.Timestamp":
						requestParamValue = DateUtil.toSqlTimestamp(StringValidator.NulltoBlank(request.getParameter(attrNames.get(i))));
					default:
				}
				ObjectUtilityInvoker.invokeSetter(obj, attrNames.get(i), requestParamValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> getAllParameters(HttpServletRequest request){
		Enumeration enumeration = request.getParameterNames();
		Map<String, String> parameterMap = new HashMap<>();
		while(enumeration.hasMoreElements()){
			String parameterName = (String) enumeration.nextElement();
			parameterMap.put(parameterName, (String)request.getParameter(parameterName));
		}
		return parameterMap;
	}
	
	
	
}
