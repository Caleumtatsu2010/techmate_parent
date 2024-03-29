package com.caleumtatsu2010.utility.servlet.request;

import com.caleumtatsu2010.utility.common.validate.StrValidator;
import com.caleumtatsu2010.utility.object.reflect.Invoker;
import com.caleumtatsu2010.utility.time.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RequestUlti {
	
	/**
	 * for object and List<ArrayList> objectList only
	 * @param request
	 * @param obj
	 */
	public static void autoSetAttribute(HttpServletRequest request, Object obj) {
		String attributeName = "";
		if (obj != null) {
			attributeName = obj.getClass().getSimpleName();
		}
		if (obj instanceof List) {
			String elementClassName = "";
			try {
				elementClassName = ((List<?>) obj).get(0).getClass().getSimpleName();
			} catch (IndexOutOfBoundsException e) {
				obj = null;// ex NullList
			}
			attributeName = elementClassName + "List";// ex ProductList
		}
		request.setAttribute(attributeName, obj);
	}
	
	public static void mapRequestParamToObjByField(HttpServletRequest request, Object obj) {
		try {
			List<String> attrNames = Invoker.getAllAttributeNames(obj);
			List<String> attrTypes = Invoker.getAllAttributeType(obj);
			Object requestParamValue = new Object();
			for (int i = 0; i < attrNames.size(); i++) {
				switch (attrTypes.get(i)) {
					case "java.lang.String":
						requestParamValue = StrValidator.NulltoBlank(request.getParameter(attrNames.get(i)));
						break;
					case "int":
						requestParamValue = StrValidator.safeParseInt(request.getParameter(attrNames.get(i)));
						break;
					case "double":
						requestParamValue = StrValidator.safeParseDouble(request.getParameter(attrNames.get(i)));
						break;
					case "java.sql.Timestamp":
						requestParamValue = DateUtil.toSqlTimestamp(StrValidator.NulltoBlank(request.getParameter(attrNames.get(i))), DateUtil.timeStampFormat1);
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
	
	public static Iterator<Map.Entry<String, String>> getAllParametersIterator(HttpServletRequest request) {
		Map<String, String> parameterMap = getAllParameters(request);
		Iterator<Map.Entry<String, String>> new_Iterator = parameterMap.entrySet().iterator();
		return new_Iterator;
	}
	
	
}
