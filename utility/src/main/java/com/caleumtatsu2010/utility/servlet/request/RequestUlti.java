//package com.caleumtatsu2010.utility.servlet.request;
//
//import com.caleumtatsu2010.utility.object.reflect.Invoke;
//import javax.servlet.*;
//import java.io.IOException;
//import java.util.List;
//
//public class RequestUlti {
//
//	public static void mapRequestToObjectFieldNames(HttpServletRequest request, Object obj, List<String> attrNames) throws SQLException {
//		Object resultSetValue = new Object();
//		ResultSetMetaData rsmd = rs.getMetaData();
//		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//			switch (rsmd.getColumnType(i)) {
//				case Types.LONGVARCHAR:
//					resultSetValue = rs.getString(rsmd.getColumnName(i));
//					break;
//				case Types.VARCHAR:
//					resultSetValue = rs.getString(rsmd.getColumnName(i));
//					break;
//				case Types.CHAR:
//					resultSetValue = rs.getString(rsmd.getColumnName(i));
//					break;
//				case Types.INTEGER:
//					resultSetValue = rs.getInt(rsmd.getColumnName(i));
//					break;
//				case Types.DOUBLE:
//					resultSetValue = rs.getDouble(rsmd.getColumnName(i));
//					break;
//				case Types.FLOAT:
//					resultSetValue = rs.getDouble(rsmd.getColumnName(i));
//					break;
//				case Types.DECIMAL:
//					resultSetValue = rs.getDouble(rsmd.getColumnName(i));
//					break;
//				default:
//			}
//			Invoke.invokeSetter(obj, attrNames.get(i - 1), (resultSetValue == null) ? "null" : resultSetValue);
//		}
//	}
//
//}
