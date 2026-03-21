package com.example.sqlassist.models;

public class DbSetting {

    public String server;
    public String database;
    public String username;
    public String password;

    public DbSetting(String server, String database, String username, String password) {
        this.server = server;
        this.database = database;
        this.username = username;
        this.password = password;
    }
}