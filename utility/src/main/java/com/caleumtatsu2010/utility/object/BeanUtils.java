package com.caleumtatsu2010.utility.object;

import com.caleumtatsu2010.utility.object.reflect.Invoker;

import java.util.List;

public class BeanUtils {
	public static void copyBean(Object sourceBean, Object targetBean) {
		List<String> allAttributeNames = Invoker.getAllAttributeNames(sourceBean);
		for (int i = 0; i < allAttributeNames.size(); i++) {
			Object srcGetterValue = Invoker.invokeGetter(sourceBean, allAttributeNames.get(i));
			Invoker.invokeSetter(targetBean, allAttributeNames.get(i), srcGetterValue);
		}
	}
}
