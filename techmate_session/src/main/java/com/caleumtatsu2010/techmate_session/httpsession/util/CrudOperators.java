package com.caleumtatsu2010.techmate_session.httpsession.util;

import com.caleumtatsu2010.utility.object.reflect.Invoker;

public class CrudOperators {
	
	public static void CrudOperatorsForAstraDb(Object objectDao, Object param, Object uuid) {
		Object methodReturn = Invoker.invokeGetMethod(objectDao, "get", uuid);
		if (methodReturn != null) {
			Invoker.invokeSetMethod(objectDao, "update", param, uuid);
		} else {
			Invoker.invokeSetMethod(objectDao, "insert", param);
		}
	}
	
}
