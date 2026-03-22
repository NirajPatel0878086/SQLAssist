package com.example.sqlassist.database;

public class DBConst {

    public static final String TABLE_USERS = "users";
    public static final String USER_COL_ID = "id";
    public static final String USER_COL_NAME = "name";
    public static final String USER_COL_EMAIL = "email";

    public static final String TABLE_CATEGORIES = "categories";
    public static final String CATEGORY_COL_ID = "id";
    public static final String CATEGORY_COL_NAME = "name";

    public static final String TABLE_ITEMS = "items";
    public static final String ITEM_COL_ID = "id";
    public static final String ITEM_COL_NAME = "name";
    public static final String ITEM_COL_QUANTITY = "quantity";
    public static final String ITEM_COL_PRICE = "price";
    public static final String ITEM_COL_CATEGORY_ID = "category_id";

    public static final String CREATE_TABLE_USERS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
                    USER_COL_ID + " INT NOT NULL AUTO_INCREMENT, " +
                    USER_COL_NAME + " VARCHAR(100) NOT NULL, " +
                    USER_COL_EMAIL + " VARCHAR(100) NOT NULL, " +
                    "PRIMARY KEY (" + USER_COL_ID + "))";

    public static final String CREATE_TABLE_CATEGORIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORIES + " (" +
                    CATEGORY_COL_ID + " INT NOT NULL AUTO_INCREMENT, " +
                    CATEGORY_COL_NAME + " VARCHAR(100) NOT NULL, " +
                    "PRIMARY KEY (" + CATEGORY_COL_ID + "))";

    public static final String CREATE_TABLE_ITEMS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ITEMS + " (" +
                    ITEM_COL_ID + " INT NOT NULL AUTO_INCREMENT, " +
                    ITEM_COL_NAME + " VARCHAR(100) NOT NULL, " +
                    ITEM_COL_QUANTITY + " INT NOT NULL, " +
                    ITEM_COL_PRICE + " DOUBLE NOT NULL, " +
                    ITEM_COL_CATEGORY_ID + " INT NOT NULL, " +
                    "PRIMARY KEY (" + ITEM_COL_ID + "), " +
                    "FOREIGN KEY (" + ITEM_COL_CATEGORY_ID + ") REFERENCES " +
                    TABLE_CATEGORIES + "(" + CATEGORY_COL_ID + "))";
}