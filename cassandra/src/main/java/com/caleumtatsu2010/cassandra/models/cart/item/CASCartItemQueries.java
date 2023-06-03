package com.caleumtatsu2010.cassandra.models.cart.item;

public class CASCartItemQueries {
	
	public static String selectAll = "select * from techmate.cart_item;";
	
	public static String selectById = "select * from techmate.cart_item where id = ?;";
	
	public static String insertById = "INSERT INTO techmate.cart_item (id, cart_id, product_id) VALUES (?, ?, ?)";
	
	public static String updateById = "UPDATE techmate.cart_item SET cart_id = ?, product_id = ? WHERE id = ? IF EXISTS;";
	
	public static String deleteByID = "DELETE FROM techmate.cart_item where id = ?;";
}
