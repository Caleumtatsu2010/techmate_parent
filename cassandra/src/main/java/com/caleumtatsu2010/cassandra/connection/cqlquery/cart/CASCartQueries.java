package com.caleumtatsu2010.cassandra.connection.cqlquery.cart;

public class CASCartQueries {
	
	public static String selectAll = "select * from cart.cart;";
	
	public static String selectById = "select * from techmate.cart where id = ?;";
	
	public static String insertById = "INSERT INTO techmate.cart (id, quantity, total_price, cart_is_active, account_id, created_at, modified_at) VALUES (?, ?, ?, ?, ?, ?, ?) IF NOT EXISTS";
	
	public static String updateById = "UPDATE techmate.cart SET cart_is_active = ?, created_at = ?, modified_at = ?, quantity=?, total_price=?, user_id=?  WHERE id = ? IF EXISTS;";
	
	public static String deleteByID = "DELETE FROM techmate.cart where id = ?;";
}
