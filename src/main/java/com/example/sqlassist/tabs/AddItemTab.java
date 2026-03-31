package com.example.sqlassist.tabs;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import com.example.sqlassist.models.Category;
import com.example.sqlassist.models.Item;
import com.example.sqlassist.table.CategoryTable;
import com.example.sqlassist.table.ItemTable;

public class AddItemTab extends Tab {

    private static AddItemTab instance;

    private AddItemTab() {
        this.setText("Add Item");

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);

        CategoryTable categoryTable = CategoryTable.getInstance();
        ItemTable itemTable = ItemTable.getInstance();

        Text nameLabel = new Text("Item Name:");
        TextField nameField = new TextField();
        root.add(nameLabel, 0, 0);
        root.add(nameField, 1, 0);

        Text quantityLabel = new Text("Quantity:");
        TextField quantityField = new TextField();
        root.add(quantityLabel, 0, 1);
        root.add(quantityField, 1, 1);

        Text priceLabel = new Text("Price:");
        TextField priceField = new TextField();
        root.add(priceLabel, 0, 2);
        root.add(priceField, 1, 2);

        Text categoryLabel = new Text("Category:");
        ComboBox<Category> categoryComboBox = new ComboBox<>();
        categoryComboBox.setItems(FXCollections.observableArrayList(categoryTable.getAllCategories()));
        if (!categoryComboBox.getItems().isEmpty()) {
            categoryComboBox.getSelectionModel().select(0);
        }
        root.add(categoryLabel, 0, 3);
        root.add(categoryComboBox, 1, 3);

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            try {
                Item item = new Item(
                        nameField.getText(),
                        Integer.parseInt(quantityField.getText()),
                        Double.parseDouble(priceField.getText()),
                        categoryComboBox.getSelectionModel().getSelectedItem().getId()
                );

                itemTable.createItem(item);

                nameField.clear();
                quantityField.clear();
                priceField.clear();

                if (!categoryComboBox.getItems().isEmpty()) {
                    categoryComboBox.getSelectionModel().select(0);
                }

                StatisticsTab.getInstance().generateChart();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.add(submit, 0, 4);

        this.setContent(root);
    }

    public static AddItemTab getInstance() {
        if (instance == null) {
            instance = new AddItemTab();
        }
        return instance;
    }
}