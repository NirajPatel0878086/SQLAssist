package com.example.sqlassist.database;

public class DBConst {

    public static final String TABLE_USERS = "app_users";
    public static final String USER_COL_ID = "id";
    public static final String USER_COL_FIRST = "first_name";
    public static final String USER_COL_LAST = "last_name";
    public static final String USER_COL_EMAIL = "email";
    public static final String USER_COL_PASS = "password";

    public static final String TABLE_ITEMS = "managed_items";
    public static final String ITEM_COL_ID = "id";
    public static final String ITEM_COL_NAME = "item_name";
    public static final String ITEM_COL_TYPE = "item_type";
    public static final String ITEM_COL_VALUE = "item_value";

    public static final String TABLE_LOGS = "activity_logs";
    public static final String LOG_COL_ID = "id";
    public static final String LOG_COL_ACTION = "action_name";
    public static final String LOG_COL_DATE = "action_date";

    public static final String CREATE_TABLE_USERS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
                    USER_COL_ID + " int NOT NULL AUTO_INCREMENT, " +
                    USER_COL_FIRST + " VARCHAR(50), " +
                    USER_COL_LAST + " VARCHAR(50), " +
                    USER_COL_EMAIL + " VARCHAR(100), " +
                    USER_COL_PASS + " VARCHAR(100), " +
                    "PRIMARY KEY(" + USER_COL_ID + "))";

    public static final String CREATE_TABLE_ITEMS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ITEMS + " (" +
                    ITEM_COL_ID + " int NOT NULL AUTO_INCREMENT, " +
                    ITEM_COL_NAME + " VARCHAR(100), " +
                    ITEM_COL_TYPE + " VARCHAR(50), " +
                    ITEM_COL_VALUE + " VARCHAR(100), " +
                    "PRIMARY KEY(" + ITEM_COL_ID + "))";

    public static final String CREATE_TABLE_LOGS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_LOGS + " (" +
                    LOG_COL_ID + " int NOT NULL AUTO_INCREMENT, " +
                    LOG_COL_ACTION + " VARCHAR(100), " +
                    LOG_COL_DATE + " VARCHAR(50), " +
                    "PRIMARY KEY(" + LOG_COL_ID + "))";
}