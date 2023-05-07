package com.caleumtatsu2010.cassandra.models.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CASAccount {
	private UUID id;
	private String username;
	private String password;
	private String privatekey;
	private int accountTypeId;
	private String accountStatus;
}
