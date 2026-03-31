package com.example.sqlassist.tabs;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import com.example.sqlassist.models.Category;
import com.example.sqlassist.models.Item;
import com.example.sqlassist.table.CategoryTable;
import com.example.sqlassist.table.ItemTable;

import java.util.ArrayList;

public class UpdateItemPane extends GridPane {

    public int findCategoryIndex(ArrayList<Category> categories, int categoryId) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == categoryId) {
                return i;
            }
        }
        return 0;
    }

    public UpdateItemPane(Item item) {
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(10);
        this.setHgap(10);

        CategoryTable categoryTable = CategoryTable.getInstance();
        ItemTable itemTable = ItemTable.getInstance();

        // Name
        Text nameLabel = new Text("Item Name:");
        TextField nameField = new TextField();
        nameField.setText(item.getName());
        this.add(nameLabel, 0, 0);
        this.add(nameField, 1, 0);

        // Quantity
        Text quantityLabel = new Text("Quantity:");
        TextField quantityField = new TextField();
        quantityField.setText(String.valueOf(item.getQuantity()));
        this.add(quantityLabel, 0, 1);
        this.add(quantityField, 1, 1);

        // Price
        Text priceLabel = new Text("Price:");
        TextField priceField = new TextField();
        priceField.setText(String.valueOf(item.getPrice()));
        this.add(priceLabel, 0, 2);
        this.add(priceField, 1, 2);

        // Category
        Text categoryLabel = new Text("Category:");
        ComboBox<Category> categoryComboBox = new ComboBox<>();
        ArrayList<Category> allCategories = categoryTable.getAllCategories();
        categoryComboBox.setItems(FXCollections.observableArrayList(allCategories));
        categoryComboBox.getSelectionModel().select(findCategoryIndex(allCategories, item.getCategoryId()));
        this.add(categoryLabel, 0, 3);
        this.add(categoryComboBox, 1, 3);

        // Update button
        Button update = new Button("Update");

        update.setOnAction(e -> {
            try {
                item.setName(nameField.getText());
                item.setQuantity(Integer.parseInt(quantityField.getText()));
                item.setPrice(Double.parseDouble(priceField.getText()));
                item.setCategoryId(categoryComboBox.getSelectionModel().getSelectedItem().getId());

                itemTable.updateItem(item);
                RemoveItemTab.getInstance().refreshTable();
                StatisticsTab.getInstance().generateChart();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        this.add(update, 1, 4);
    }
}