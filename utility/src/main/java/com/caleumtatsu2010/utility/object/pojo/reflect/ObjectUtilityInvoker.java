package com.caleumtatsu2010.utility.object.pojo.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObjectUtilityInvoker {


//    public static List<Object> invokeGetters(Object p) {
//        Class<?> c = p.getClass();
//        Method[] allMethods = c.getDeclaredMethods();
//        List<Object> list = new ArrayList<>();
//        for (Method m : allMethods) {
//                m.setAccessible(true);
//                String result;
//                try {
//                    list.add(invokeGetter(p, "id"));
//                } catch (IllegalAccessException | InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//        }
//        return list;
//    }
	
	public static void invokeSetter(Object obj, String variableName, Object variableValue) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(variableName, obj.getClass());
			Method setter = pd.getWriteMethod();
			try {
				setter.invoke(obj, variableValue);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}
	
	public static Object invokeGetter(Object obj, String variableName) {
		Object f = null;//atribute
		try {
			PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
			Method getter = pd.getReadMethod();
			f = getter.invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public static void invokeSetMethod(Object obj, String methodName, Object methodParam) {
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName, methodParam.getClass());
			try {
				method.invoke(obj, methodParam);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public static void invokeSetMethod(Object object, String methodName, Object methodParam1, Object methodParam2) {
		try {
			Method method = object.getClass().getDeclaredMethod(methodName, methodParam1.getClass(), methodParam2.getClass());
			try {
				method.invoke(object, methodParam1, methodParam2);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public static Object invokeGetMethod(Object obj, String methodName, Object methodParam) {
		Object methodReturn = null;
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName);
			try {
				methodReturn = method.invoke(obj, methodParam);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return methodReturn;
	}
	
	public static Object invokeGetMethod(Object obj, String methodName) {
		Object methodReturn = null;
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName);
			try {
				methodReturn = method.invoke(obj);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return methodReturn;
	}
	
	public static List<String> getAllAttributeName(Object object) {
		List<String> attrNames = new ArrayList<>();
		Field[] attributes = object.getClass().getDeclaredFields();
		for (Field field : attributes) {
			// Dynamically read Attribute Name
			attrNames.add(field.getName());
		}
		return attrNames;
	}
	
	public static List<String> getAllAttributeType(Object object) {
		List<String> attrType = new ArrayList<>();
		Field[] attributes = object.getClass().getDeclaredFields();
		for (Field field : attributes) {
			// Dynamically read Attribute Name
			attrType.add(field.getType().toString());
		}
		return attrType;
	}
	
	public static void main(String[] args) {
//		Calendar cal = Calendar.getInstance();
//        Cart cart = new Cart(2, "userid", 10, DateUtility.toSqlTimestamp(cal), DateUtility.toSqlTimestamp(cal));
//
//        System.out.println(invokeGetter(cart, "id"));
//        System.out.println(invokeGetter(cart, "userId"));
//        System.out.println(invokeGetter(cart, "total"));
//        System.out.println(invokeGetter(cart, "createdAt"));
//        System.out.println(invokeGetter(cart, "modifiedAt"));
		
	
	}
	
}
