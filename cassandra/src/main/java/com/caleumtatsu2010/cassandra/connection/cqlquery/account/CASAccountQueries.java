package com.caleumtatsu2010.cassandra.connection.cqlquery.account;

public class CASAccountQueries {
	
	public static String selectAll = "select * from user.account;";
	
	public static String selectById = "select * from user.account where id = ?;";
	
	public static String insertById = " insert into user.account (id, username, password, privatekey, account_typeId, account_status) values (?, ?, ?, ?, ?, ?); ";
	
//	public static String updateById = "UPDATE cart.cart SET cart_is_active = ?, created_at = ?, modified_at = ?, quantity=?, total_price=?, user_id=?  WHERE id = ? IF EXISTS;";
//
	public static String deleteByID = "DELETE FROM user.account where id = ?;";
}
