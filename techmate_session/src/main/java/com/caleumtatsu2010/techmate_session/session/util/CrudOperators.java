package com.caleumtatsu2010.techmate_session.session.util;

import com.caleumtatsu2010.utility.object.reflect.ObjectUtilityInvoker;

public class CrudOperators {
	
	public static void CrudOperatorsForAstraDb(Object objectDao, Object param, Object uuid) {
		Object methodReturn = ObjectUtilityInvoker.invokeGetMethod(objectDao, "get", uuid);
		if (methodReturn != null) {
			ObjectUtilityInvoker.invokeSetMethod(objectDao, "update", param, uuid);
		} else {
			ObjectUtilityInvoker.invokeSetMethod(objectDao, "insert", param);
		}
	}
	
}
