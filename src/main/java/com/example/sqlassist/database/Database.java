package com.example.sqlassist.database;

import com.example.sqlassist.models.DbSetting;
import com.example.sqlassist.utils.FileUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static com.example.sqlassist.database.DBConst.*;

public class Database {

    private static Database instance;
    private Connection connection;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            DbSetting setting = FileUtil.load();

            if (setting == null) {
                throw new RuntimeException("Settings file not found.");
            }

            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + setting.server + ":3307/" + setting.database + "?useSSL=false&serverTimezone=UTC",
                    setting.username,
                    setting.password
            );

            createTable(CREATE_TABLE_USERS);
            createTable(CREATE_TABLE_CATEGORIES);
            createTable(CREATE_TABLE_ITEMS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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