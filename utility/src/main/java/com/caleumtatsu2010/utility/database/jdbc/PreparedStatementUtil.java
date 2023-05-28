package com.caleumtatsu2010.utility.database.jdbc;

import com.caleumtatsu2010.utility.object.reflect.Invoke;

import java.sql.*;
import java.util.List;

public class PreparedStatementUtil {
	
	/**
	 * @param ps
	 * @param arg
	 * @param i
	 * @throws SQLException
	 */
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
		} else {
			ps.setDate(i, (Date) arg);
		}
	}
	
	public static void mapPreparedStatementFieldNames(PreparedStatement ps, Object obj, List<String> attrNames) throws SQLException {
		for (int i = 0; i < attrNames.size(); i++) {
			mapParam(ps, Invoke.invokeGetter(obj, attrNames.get(i)), i + 1);
		}
	}
	
	/**
	 *
	 * @param ps
	 * @param obj
	 * @param attrNames
	 * @param queryType 1:insert        2:update
	 * @throws SQLException
	 */
	public static void mapPreparedStatementFieldNamesType(PreparedStatement ps, Object obj, List<String> attrNames, int queryType) throws SQLException {
		List<String> tempAttrNames = attrNames;
		if (queryType == 2 && "id".equals(attrNames.get(0))) { // is update query
			tempAttrNames.add(attrNames.get(0));
			tempAttrNames.remove(0);
		}
		mapPreparedStatementFieldNames(ps, obj, tempAttrNames);
	}
	
	public static void mapPreparedStatementToObjectType(PreparedStatement ps, Object obj, int queryType) throws SQLException {
		List<String> attrNames = Invoke.getAllAttributeName(obj);
		mapPreparedStatementFieldNamesType(ps, obj, attrNames, queryType);
	}
	
	public static void main(String[] args) {
		
	}
	
}