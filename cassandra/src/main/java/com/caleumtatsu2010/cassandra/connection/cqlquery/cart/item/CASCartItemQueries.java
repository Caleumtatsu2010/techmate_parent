package com.caleumtatsu2010.cassandra.connection.cqlquery.cart.item;

public class CASCartItemQueries {
	
	public static String selectAll = "select * from cart.cart_item;";
	
	public static String selectById = "select * from cart.cart_item where id = ?;";
	
	public static String insertById = "INSERT INTO cart.cart_item (id, cart_id, product_id) VALUES (?, ?, ?)";
	
	public static String updateById = "UPDATE cart.cart_item SET cart_id = ?, product_id = ? WHERE id = ? IF EXISTS;";
	
	public static String deleteByID = "DELETE FROM cart.cart_item where id = ?;";
}
