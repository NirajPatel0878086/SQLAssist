package com.example.sqlassist.models;

public class Item {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int categoryId;

    public Item() {
    }

    public Item(int id, String name, int quantity, double price, int categoryId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Item(String name, int quantity, double price, int categoryId) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}