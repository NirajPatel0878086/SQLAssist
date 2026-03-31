package com.example.sqlassist.utils;

import com.example.sqlassist.models.DbSetting;

import java.io.*;

public class FileUtil {

    //This method saves database settings into a text file
    public static void save(DbSetting setting) {

        try {
            //Create writer for setting file
            BufferedWriter writer = new BufferedWriter(new FileWriter("settings.txt"));

            //save server name
            writer.write(setting.server);
            writer.newLine();
            //save database name
            writer.write(setting.database);
            writer.newLine();
            //save username
            writer.write(setting.username);
            writer.newLine();
            //save password
            writer.write(setting.password);
            writer.newLine();

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this method load database settings from the text file
    public static DbSetting load() {

        try {
            //Create reader for settings file
            BufferedReader reader = new BufferedReader(new FileReader("settings.txt"));

            String server = reader.readLine();
            String database = reader.readLine();
            String username = reader.readLine();
            String password = reader.readLine();

            reader.close();

            return new DbSetting(server, database, username, password);

        } catch (Exception e) {
            return null;
        }
    }
}