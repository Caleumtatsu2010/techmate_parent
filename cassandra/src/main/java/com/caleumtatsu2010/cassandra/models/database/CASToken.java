package com.caleumtatsu2010.cassandra.models.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CASToken {
    private String ClientId;
    private String ClientSecret;
    private String Role;
    private String Token;
}