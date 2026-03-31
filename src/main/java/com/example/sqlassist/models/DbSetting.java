package com.example.sqlassist.models;

public class DbSetting {

    //Store  server name
    public String server;
    //Store database name
    public String database;
    //store database username
    public String username;
    //store database password
    public String password;

    //Constructor to set all database settings
    public DbSetting(String server, String database, String username, String password) {
        this.server = server;
        this.database = database;
        this.username = username;
        this.password = password;
    }
}