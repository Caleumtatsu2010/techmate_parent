package com.caleumtatsu2010.cassandra.models.user;

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
public class CASUserInfo {
	private UUID id;
	private UUID account_id;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
}
