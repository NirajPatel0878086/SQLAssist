package com.example.sqlassist.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static com.example.sqlassist.database.Const.*;
import static com.example.sqlassist.database.DBConst.*;

public class Database {

    private static Database instance;
    private Connection connection;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3307/" + DB_NAME + "?serverTimezone=UTC",
                    DB_USER,
                    DB_PASS
            );

            createTable(CREATE_TABLE_USERS);
            createTable(CREATE_TABLE_ITEMS);
            createTable(CREATE_TABLE_LOGS);

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