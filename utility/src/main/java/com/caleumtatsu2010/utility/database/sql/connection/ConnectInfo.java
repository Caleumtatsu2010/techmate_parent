package com.caleumtatsu2010.utility.database.sql.connection;

public class ConnectInfo {
    private String host;
    private String dbname;
    private String username;
    private String password;
    private String port;
    private String dbtype;
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getDbname() {
        return dbname;
    }
    
    public void setDbname(String dbname) {
        this.dbname = dbname;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPort() {
        return port;
    }
    
    public void setPort(String port) {
        this.port = port;
    }
    
    public String getDbtype() {
        return dbtype;
    }
    
    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }
}
