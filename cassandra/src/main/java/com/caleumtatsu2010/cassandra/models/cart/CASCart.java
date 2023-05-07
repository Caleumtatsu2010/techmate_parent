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
public class CASCart {
	private UUID id;
	boolean cartIsActive;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private int quantity;
	private double totalPrice;
	private UUID userId;
}