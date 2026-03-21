package com.example.sqlassist.utils;

import com.example.sqlassist.models.DbSetting;

import java.io.*;

public class FileUtil {

    public static void save(DbSetting setting) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("settings.txt"));

            writer.write(setting.server);
            writer.newLine();
            writer.write(setting.database);
            writer.newLine();
            writer.write(setting.username);
            writer.newLine();
            writer.write(setting.password);
            writer.newLine();

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DbSetting load() {

        try {
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