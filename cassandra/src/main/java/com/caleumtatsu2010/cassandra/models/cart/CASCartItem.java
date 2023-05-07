package com.caleumtatsu2010.cassandra.models.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CASCartItem {
	private UUID id;
	private UUID cartId;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private String name;
	private double price;
	private UUID productId;
}
