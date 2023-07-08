package com.caleumtatsu2010.utility.servlet.request;

import com.caleumtatsu2010.utility.common.validate.StringValidator;
import com.caleumtatsu2010.utility.object.reflect.Invoker;
import com.caleumtatsu2010.utility.time.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RequestUlti {
	
	public static void mapRequestParamToObjByField(HttpServletRequest request, Object obj) {
		try {
			List<String> attrNames = Invoker.getAllAttributeNames(obj);
			List<String> attrTypes = Invoker.getAllAttributeType(obj);
			Object requestParamValue = new Object();
			for (int i = 0; i < attrNames.size(); i++) {
				switch (attrTypes.get(i)) {
					case "java.lang.String":
						requestParamValue = StringValidator.NulltoBlank(request.getParameter(attrNames.get(i)));
						break;
					case "int":
						requestParamValue = StringValidator.safeParseInt(request.getParameter(attrNames.get(i)));
						break;
					case "double":
						requestParamValue = StringValidator.safeParseDouble(request.getParameter(attrNames.get(i)));
						break;
					case "java.sql.Timestamp":
						requestParamValue = DateUtil.toSqlTimestamp(StringValidator.NulltoBlank(request.getParameter(attrNames.get(i))), DateUtil.timeStampFormat1);
					default:
				}
				Invoker.invokeSetter(obj, attrNames.get(i), requestParamValue);
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
