package com.example.sqlassist.table;

import com.example.sqlassist.database.Database;
import com.example.sqlassist.models.Item;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemTable {

    private static ItemTable instance;
    Database db = Database.getInstance();
    ArrayList<Item> items = new ArrayList<>();

    public static ItemTable getInstance() {
        if (instance == null) {
            instance = new ItemTable();
        }
        return instance;
    }

    //This method get items from database
    public ArrayList<Item> getAllItems() {
        String query = "SELECT * FROM items";
        items = new ArrayList<>();

        try {
            ResultSet data = db.getConnection()
                    .createStatement()
                    .executeQuery(query);

            while (data.next()) {
                items.add(new Item(
                        data.getInt("id"),
                        data.getString("name"),
                        data.getInt("quantity"),
                        data.getDouble("price"),
                        data.getInt("category_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    //This method gets one item using id
    public Item getItem(int id) {
        Item item = null;
        String query = "SELECT * FROM items WHERE id = " + id;

        try {
            ResultSet data = db.getConnection()
                    .createStatement()
                    .executeQuery(query);

            if (data.next()) {
                item = new Item(
                        data.getInt("id"),
                        data.getString("name"),
                        data.getInt("quantity"),
                        data.getDouble("price"),
                        data.getInt("category_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    //This method adds a new item into database
    public void createItem(Item item) {
        String query = "INSERT INTO items (name, quantity, price, category_id) VALUES ('"
                + item.getName() + "', '"
                + item.getQuantity() + "', '"
                + item.getPrice() + "', '"
                + item.getCategoryId() + "')";

        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Inserted item");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method updates item data in database
    public void updateItem(Item item) {
        String query = "UPDATE items SET "
                + "name = '" + item.getName() + "', "
                + "quantity = '" + item.getQuantity() + "', "
                + "price = '" + item.getPrice() + "', "
                + "category_id = '" + item.getCategoryId() + "' "
                + "WHERE id = " + item.getId();

        try {
            db.getConnection().createStatement().executeUpdate(query);
            System.out.println("Updated item");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method deletes item using id
    public void deleteItem(int id) {
        String query = "DELETE FROM items WHERE id = " + id;

        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Deleted item");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method get items of by one category
    public ArrayList<Item> getItemsByCategoryId(int categoryId) {
        ArrayList<Item> list = new ArrayList<>();
        String query = "SELECT * FROM items WHERE category_id = " + categoryId;

        try {
            ResultSet data = db.getConnection()
                    .createStatement()
                    .executeQuery(query);

            while (data.next()) {
                list.add(new Item(
                        data.getInt("id"),
                        data.getString("name"),
                        data.getInt("quantity"),
                        data.getDouble("price"),
                        data.getInt("category_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    //This method counts items by one category
    public int getItemCount(int categoryId) {
        int count = 0;
        String query = "SELECT COUNT(*) as count FROM items WHERE category_id = " + categoryId;

        try {
            ResultSet data = db.getConnection()
                    .createStatement()
                    .executeQuery(query);

            if (data.next()) {
                count = data.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}