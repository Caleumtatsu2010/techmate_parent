package com.caleumtatsu2010.cassandra.connection.cqlquery.cart;

public class CASCartQueries {
	
	public static String selectAll = "select * from cart.cart;";
	
	public static String selectById = "select * from cart.cart where id = ?;";
	
	public static String insertById = "INSERT INTO cart.cart (id, cart_is_active, created_at, modified_at, quantity, total_price, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	public static String updateById = "UPDATE cart.cart SET cart_is_active = ?, created_at = ?, modified_at = ?, quantity=?, total_price=?, user_id=?  WHERE id = ? IF EXISTS;";
	
	public static String deleteByID = "DELETE FROM cart.cart where id = ?;";
}
