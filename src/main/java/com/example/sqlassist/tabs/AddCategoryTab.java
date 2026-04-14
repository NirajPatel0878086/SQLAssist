package com.example.sqlassist.tabs;

import com.example.sqlassist.utils.Animations;
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

        CategoryTable categoryTable = CategoryTable.getInstance();

        Label nameLabel = new Label("Category Name:");
        TextField nameField = new TextField();

        Button addBtn = new Button("Add Category");
        Button loadBtn = new Button("Load Categories");
        Button deleteBtn = new Button("Delete Selected");

        //This will display all the categories in listview
        ListView<Category> listView = new ListView<>();
        listView.setPrefHeight(200);
        listView.setPrefWidth(250);

        //Animation
        Animations.addHoverScale(addBtn);
        Animations.addHoverScale(loadBtn);
        Animations.addHoverScale(deleteBtn);

        //bounce animation
        addBtn.setOnMouseClicked(e-> Animations.clickBounce(addBtn));
        loadBtn.setOnMouseClicked(e-> Animations.clickBounce(loadBtn));
        deleteBtn.setOnMouseClicked(e-> Animations.clickBounce(deleteBtn));

        addBtn.setOnAction(e -> {
            if(nameField.getText().trim().isEmpty()){
                Animations.shake(nameField);
                return;
            }
            try {
                categoryTable.createCategory(nameField.getText());
                nameField.clear();
                refreshList(listView);
                AddItemTab.getInstance().refreshCategories();
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
                AddItemTab.getInstance().refreshCategories();
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

        //Animation tab
        this.setOnSelectionChanged(e-> {
            if(this.isSelected()) {
                Animations.fadeIn(this.getContent(), 250);
                Animations.slideIn(this.getContent(), 250);
            }
        });
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