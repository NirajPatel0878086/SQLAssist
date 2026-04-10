package com.example.sqlassist.tabs;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import com.example.sqlassist.models.Category;
import com.example.sqlassist.table.CategoryTable;

import java.util.ArrayList;

public class AddCategoryTab extends Tab {

    private static AddCategoryTab instance;

    private AddCategoryTab() {

        this.setText("Add Category");

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);

        // Background CSS
        root.setStyle("-fx-background-color: lightblue; -fx-padding:20;");

        CategoryTable categoryTable = CategoryTable.getInstance();

        Label nameLabel = new Label("Category Name:");
        nameLabel.setStyle("-fx-font-size:14px; -fx-font-weight:bold;");

        TextField nameField = new TextField();
        nameField.setStyle("-fx-font-size:14px;");

        Button addBtn = new Button("Add Category");
        Button loadBtn = new Button("Load Categories");
        Button deleteBtn = new Button("Delete Selected");

        // Same button width
        addBtn.setPrefWidth(160);
        loadBtn.setPrefWidth(160);
        deleteBtn.setPrefWidth(160);

        // Button CSS white with black border
        String buttonStyle =
                "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-color:white;" +
                        "-fx-text-fill:black;" +
                        "-fx-border-color:black;" +
                        "-fx-border-width:2;";

        addBtn.setStyle(buttonStyle);
        loadBtn.setStyle(buttonStyle);
        deleteBtn.setStyle(buttonStyle);

        //This will display all the categories in listview
        ListView<Category> listView = new ListView<>();
        listView.setPrefHeight(200);
        listView.setPrefWidth(250);

        listView.setStyle("-fx-font-size:13px;");

        addBtn.setOnAction(e -> {
            try {
                categoryTable.createCategory(nameField.getText());
                nameField.clear();
                refreshList(listView);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        loadBtn.setOnAction(e -> refreshList(listView));

        deleteBtn.setOnAction(e -> {

            Category selected = listView.getSelectionModel().getSelectedItem();

            if (selected != null) {
                categoryTable.deleteCategory(selected.getId());
                refreshList(listView);
                StatisticsTab.getInstance().generateChart();
            }
        });

        root.add(nameLabel, 0, 0);
        root.add(nameField, 1, 0);
        root.add(addBtn, 0, 1);
        root.add(loadBtn, 1, 1);
        root.add(deleteBtn, 0, 2);
        root.add(listView, 0, 3, 2, 1);

        this.setContent(root);
    }

    private void refreshList(ListView<Category> listView) {

        CategoryTable categoryTable = CategoryTable.getInstance();
        ArrayList<Category> categories = categoryTable.getAllCategories();

        listView.getItems().clear();
        listView.getItems().addAll(categories);
    }

    public static AddCategoryTab getInstance() {

        if (instance == null) {
            instance = new AddCategoryTab();
        }

        return instance;
    }
}