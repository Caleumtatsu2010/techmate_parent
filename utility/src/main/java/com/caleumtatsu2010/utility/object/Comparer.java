package com.caleumtatsu2010.utility.object;

import com.caleumtatsu2010.utility.object.reflect.Invoker;

import java.sql.*;
import java.util.List;

public class Comparer {
	
	public static boolean compareObject(Object obj1, Object obj2)  {
		boolean result = true;
		if (obj1.getClass() != obj2.getClass())
			return true;
		if (obj1 == null || obj2 == null)
			return false;
		if (obj1 == obj2)
			return true;
		List<String> allAttributeNames = Invoker.getAllAttributeNames(obj1);
		for (int i = 0; i < allAttributeNames.size(); i++) {
			if (!selfEquals(Invoker.invokeGetter(obj1, allAttributeNames.get(i)),
					Invoker.invokeGetter(obj2, allAttributeNames.get(i)))) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * @throws SQLException
	 */
	public static boolean selfEquals(Object obj1, Object obj2) {
		if (obj1 instanceof Integer && obj2 instanceof Integer) {
			if ((Integer) obj1 != (Integer) obj2) {
				return false;
			}
		} else if (obj1 instanceof Double && obj2 instanceof Double) {
			if ((Double) obj1 != (Double) obj2) {
				return false;
			}
		} else if (obj1 instanceof String && obj2 instanceof String) {
			if (!((String) obj1).equals((String) obj2)) {
				return false;
			}
		} else if (obj1 instanceof Timestamp && obj2 instanceof Timestamp) {
			if (((Timestamp) obj1).compareTo((Timestamp) obj2) != 0) {
				return false;
			}
		}
		return true;
	}
}
