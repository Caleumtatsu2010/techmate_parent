package com.caleumtatsu2010.techmate_session.cassandra.astra.util;

import com.caleumtatsu2010.utility.object.reflect.ObjectUtilityInvoker;

public class AstraCrudUlti {
	
	public static void syncAstraCrud(Object objectDao, Object param, Object uuid) {
		Object methodReturn = ObjectUtilityInvoker.invokeGetMethod(objectDao, "get", uuid);
		if (methodReturn != null) {
			ObjectUtilityInvoker.invokeSetMethod(objectDao, "update", param, uuid);
		} else {
			ObjectUtilityInvoker.invokeSetMethod(objectDao, "insert", param);
		}
	}
	
}
