package com.caleumtatsu2010.utility.database.connection;

import com.caleumtatsu2010.utility.database.DatabasePath;
import com.caleumtatsu2010.utility.file.properties.Utils;
import com.caleumtatsu2010.utility.object.reflect.Invoke;

import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.PropertyResourceBundle;

public class ConnectionUtil {
	
	private ConnectInfo connInfo = null;
	
	private static String DbPropPath = DatabasePath.databaseProperties;
	
	private static String JDBC_URL = "jdbc:";
	private static String DBL_SLASH = "://";
	private static String DBL_DOT = ":";
	private static String SLASH = "/";
	private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	
	public ConnectionUtil(String dbName) {
		this.connInfo = readConnectionInfo(dbName);
	}
	
	public Connection getConn() {
		Connection conn = null;
		String url = JDBC_URL + connInfo.getDbtype() + DBL_SLASH + connInfo.getHost() + DBL_DOT + connInfo.getPort() + SLASH + connInfo.getDbname();
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.println("JDBC Driver not found!");
			return null;
		}
		try {
			conn = DriverManager.getConnection(url, this.connInfo.getUsername(), this.connInfo.getPassword());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConn(Connection connection) throws SQLException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				System.err.println("Close Connection failed!");
			} finally {
				connection.close();
			}
		}
	}
	
	
	public static void closePS(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException ex) {
				System.err.println("Close PreparedStatement failed!");
			}
		}
	}
	
	public static ConnectInfo readConnectionInfo(String dbName) {
		ConnectInfo connectInfo = new ConnectInfo();
		Properties prop = Utils.loadProp(Utils.readDatabasePath(DbPropPath, dbName));
		List<String> attrNames = Invoke.getAllAttributeName(connectInfo);
		for (int i = 0; i < attrNames.size(); i++) {
			Invoke.invokeSetter(connectInfo, attrNames.get(i), prop.getProperty(attrNames.get(i)));
		}
		return connectInfo;
//		String host = prop.getProperty("host");
//		String dbname = prop.getProperty("dbname");
//		String username = prop.getProperty("username");
//		String password = prop.getProperty("password");
//		String port = prop.getProperty("port");
//		String dbtype = prop.getProperty("dbtype");
//		return new ConnectInfo(host, dbname, username, password, port, dbtype);
	}
	
	
	public static void closeRS(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				System.err.println("Close ResultSet failed!");
				ex.printStackTrace();
			}
		}
	}
	
	public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PropertyResourceBundle prb = (PropertyResourceBundle) PropertyResourceBundle.getBundle("properties.mysqlConnect");
		System.out.println(prb.getString("host"));
	}
	
}
