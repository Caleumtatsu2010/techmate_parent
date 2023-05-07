package com.caleumtatsu2010.utility.database.jdbc;

import com.caleumtatsu2010.utility.common.*;
import com.caleumtatsu2010.utility.object.*;

//import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class ResultSetUtil {
	
	public static void mapResultSetToObjectFieldNames(ResultSet rs, Object obj, List<String> attrNames) throws SQLException {
		Object resultSetValue = new Object();
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			switch (rsmd.getColumnType(i)) {
				case Types.VARCHAR | Types.CHAR | Types.NCHAR:
					resultSetValue = rs.getString(rsmd.getColumnName(i));
					break;
				case Types.INTEGER:
					resultSetValue = rs.getInt(rsmd.getColumnName(i));
					break;
				case Types.DOUBLE | Types.FLOAT | Types.DECIMAL:
					resultSetValue = rs.getDouble(rsmd.getColumnName(i));
					break;
				default:
			}
			Invoke.invokeSetter(obj, attrNames.get(i), resultSetValue);
		}
	}
	
	public static void mapResultSetToObject(ResultSet rs, Object obj) throws SQLException {
		List<String> attrNames = Invoke.getAllAttributeName(obj);
		mapResultSetToObjectFieldNames(rs, obj, attrNames);
	}
	
//	public static void mapRequestAttrToObject(HttpServletRequest request, Object obj) throws SQLException {
//		List<String> attrNames = Invoke.getAllAttributeName(obj);
//		for (int i = 0; i < attrNames.size(); i++) {
//			String requestParam = StringValidate.NulltoBlank(request.getParameter(attrNames.get(i)));
//			Invoke.invokeSetter(obj, attrNames.get(i), requestParam);
//		}
//	}
	
}
