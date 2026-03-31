package com.example.sqlassist.table;

import com.example.sqlassist.database.Database;
import com.example.sqlassist.models.Category;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryTable {

    private static CategoryTable instance;
    Database db = Database.getInstance();

    //This list stores all categories
    ArrayList<Category> categories;

    //this methods get all categories from database
    public ArrayList<Category> getAllCategories() {
        String query = "SELECT * FROM categories";
        categories = new ArrayList<>();

        try {
            ResultSet data = db.getConnection()
                    .createStatement()
                    .executeQuery(query);

            while (data.next()) {
                categories.add(new Category(
                        data.getInt("id"),
                        data.getString("name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
    //This method adds a new category into database
    public void createCategory(String name) {
        String query = "INSERT INTO categories (name) VALUES ('" + name + "')";

        try {
            db.getConnection().createStatement().execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method delete category using id
    public void deleteCategory(int id) {
        String query = "DELETE FROM categories WHERE id = " + id;

        try {
            db.getConnection().createStatement().execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CategoryTable getInstance() {
        if (instance == null) {
            instance = new CategoryTable();
        }
        return instance;
    }
}