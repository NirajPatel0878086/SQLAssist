package com.example.sqlassist.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // Current database file name
    public static String currentDatabase = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + currentDatabase + ".db");
    }
}