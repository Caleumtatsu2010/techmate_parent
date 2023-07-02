package com.caleumtatsu2010.utility.jdbc;

import com.caleumtatsu2010.utility.object.reflect.ObjectUtilityInvoker;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class ResultSetUtil {
	
	public static void mapResultSetToObjectFieldNames(ResultSet rs, Object obj, List<String> attrNames) throws SQLException {
		Object resultSetValue = new Object();
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			switch (rsmd.getColumnType(i)) {
				case Types.TIMESTAMP:
					resultSetValue = rs.getTimestamp(rsmd.getColumnName(i));
					break;
				case Types.LONGVARCHAR:
					resultSetValue = rs.getString(rsmd.getColumnName(i));
					break;
				case Types.VARCHAR:
					resultSetValue = rs.getString(rsmd.getColumnName(i));
					break;
				case Types.CHAR:
					resultSetValue = rs.getString(rsmd.getColumnName(i));
					break;
				case Types.INTEGER:
					resultSetValue = rs.getInt(rsmd.getColumnName(i));
					break;
				case Types.DOUBLE:
					resultSetValue = rs.getDouble(rsmd.getColumnName(i));
					break;
				case Types.FLOAT:
					resultSetValue = rs.getDouble(rsmd.getColumnName(i));
					break;
				case Types.DECIMAL:
					resultSetValue = rs.getDouble(rsmd.getColumnName(i));
					break;
				default:
			}
			ObjectUtilityInvoker.invokeSetter(obj, attrNames.get(i - 1), (resultSetValue == null) ? "null" : resultSetValue);
		}
	}
	
	public static void mapResultSetToObject(ResultSet rs, Object obj) throws SQLException {
		List<String> attrNames = ObjectUtilityInvoker.getAllAttributeName(obj);
		mapResultSetToObjectFieldNames(rs, obj, attrNames);
	}
	
	
}
