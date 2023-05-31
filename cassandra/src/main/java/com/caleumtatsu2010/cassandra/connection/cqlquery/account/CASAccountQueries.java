package com.caleumtatsu2010.cassandra.connection.cqlquery.account;

public class CASAccountQueries {
	
	public static String selectAll = "select * from techmate.account;";
	
	public static String selectById = "select * from techmate.account where id = ?;";
	
	public static String insertById = "insert into techmate.account (id, username, password, account_type_id, account_status) values (?, ?, ?, ?, ?) IF NOT EXISTS; ";
	
	public static String updateById = "UPDATE techmate.account SET username = ?, password = ?, account_type_id = ?, account_status=? WHERE id = ? IF EXISTS;";

	public static String deleteByID = "DELETE FROM techmate.account where id = ?;";
}
