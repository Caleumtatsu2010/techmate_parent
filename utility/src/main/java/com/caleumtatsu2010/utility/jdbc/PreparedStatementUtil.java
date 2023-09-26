package com.caleumtatsu2010.utility.jdbc;

import com.caleumtatsu2010.utility.object.reflect.Invoker;

import java.sql.*;
import java.util.List;

public class PreparedStatementUtil {
	
	public static void mapParam(PreparedStatement ps, Object arg, int i) throws SQLException {
		if (arg instanceof Integer) {
			ps.setInt(i, (Integer) arg);
		} else if (arg instanceof Double) {
			ps.setDouble(i, (Double) arg);
		} else if (arg instanceof String) {
			ps.setString(i, (String) arg);
		} else if (arg instanceof Date) {
			ps.setTimestamp(i, new Timestamp(((Date) arg).getTime()));
		} else if (arg instanceof Timestamp) {
			ps.setTimestamp(i, (Timestamp) arg);
		} else if (arg instanceof Blob) {
			ps.setBlob(i, (Blob) arg);
		} else if (arg == byte[].class) {
			ps.setBytes(i, (byte[]) arg);
		} else if ("byte[]".equals(arg.getClass().getSimpleName())) {
			ps.setBytes(i, (byte[]) arg);
		} else {
			ps.setDate(i, (Date) arg);
		}
	}
	
	public static void mapPreparedStatementFieldNames(PreparedStatement ps, Object obj, List<String> allAttributeNames) throws SQLException {
		for (int i = 0; i < allAttributeNames.size(); i++) {
			mapParam(ps, Invoker.invokeGetter(obj, allAttributeNames.get(i)), i + 1);
		}
	}
	
	/**
	 * @param ps                PreparedStatement
	 * @param obj               Object
	 * @param allAttributeNames allAttributeNames
	 * @param queryType         1:insert        2:update
	 * @throws SQLException SQLException
	 */
	public static void mapPreparedStatementFieldNamesType(PreparedStatement ps, Object obj, List<String> allAttributeNames, int queryType) throws SQLException {
		List<String> tempAttrNames = allAttributeNames;
		if (queryType == 2 && "id".equals(allAttributeNames.get(0))) { // is update query
			tempAttrNames.add(allAttributeNames.get(0));
			tempAttrNames.remove(0);
		}
		mapPreparedStatementFieldNames(ps, obj, tempAttrNames);
	}
	
	/**
	 * @param ps        PreparedStatement
	 * @param obj       Object
	 * @param queryType 1 for insert query because id is on first place of the query
	 *                  2 for update query because id is on last place of the query
	 * @throws SQLException SQLException
	 */
	public static void mapPreparedStatementToObjectType(PreparedStatement ps, Object obj, int queryType) throws SQLException {
		List<String> allAttributeNames = Invoker.getAllAttributeNames(obj);
		mapPreparedStatementFieldNamesType(ps, obj, allAttributeNames, queryType);
	}
	
	public static void main(String[] args) {
		
	}
	
}