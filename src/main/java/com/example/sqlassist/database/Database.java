package com.example.sqlassist.database;

import com.example.sqlassist.models.DbSetting;
import com.example.sqlassist.utils.FileUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static com.example.sqlassist.database.DBConst.*;

public class Database {


    private static Database instance;

    //To set the database connection
    private final Connection connection;

    private Database() {
        try {
            //Load MYSQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Load saved database setting from file which store data dynamic
            DbSetting setting = FileUtil.load();

            //if file missing this will show error
            if (setting == null) {
                throw new RuntimeException("Settings file not found.");
            }
            //Create Database connection with MYSQL database
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + setting.server + ":3306/" + setting.database + "?useSSL=false&serverTimezone=UTC",
                    setting.username,
                    setting.password
            );
            //Create users table
            createTable(CREATE_TABLE_USERS);
            //Create categories table
            createTable(CREATE_TABLE_CATEGORIES);
            //Create items table
            createTable(CREATE_TABLE_ITEMS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //This method use to execute the Create table query
    public void createTable(String query) {
        try {
            Statement st = connection.createStatement();
            st.execute(query);
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}