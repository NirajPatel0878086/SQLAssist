package com.example.sqlassist.tabs;

import com.example.sqlassist.utils.Animations;
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
    private final ComboBox<Category> categoryComboBox;

    private AddItemTab() {
        this.setText("Add Item");

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);

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
        categoryComboBox = new ComboBox<>();
        refreshCategories();
        if (!categoryComboBox.getItems().isEmpty()) {
            categoryComboBox.getSelectionModel().select(0);
        }
        root.add(categoryLabel, 0, 3);
        root.add(categoryComboBox, 1, 3);

        Button submit = new Button("Submit");

        //Hover animation
        Animations.addHoverScale(submit);

        //bounce animation
        submit.setOnMouseClicked(e->
            Animations.clickBounce(submit)
        );

        submit.setOnAction(e -> {
            if (nameField.getText().trim().isEmpty() ||
            quantityField.getText().trim().isEmpty() ||
            priceField.getText().trim().isEmpty()){
                //shaking all the empty fields
                if(nameField.getText().trim().isEmpty()) Animations.shake(nameField);
                if(quantityField.getText().trim().isEmpty()) Animations.shake(quantityField);
                if(priceField.getText().trim().isEmpty()) Animations.shake(priceField);
                return;
            }
            try {
                Item item = new Item(
                        nameField.getText(),
                        Integer.parseInt(quantityField.getText()),
                        Double.parseDouble(priceField.getText()),
                        categoryComboBox.getSelectionModel().getSelectedItem().getId()
                );

                itemTable.createItem(item);
                RemoveItemTab.getInstance().refreshTable();
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

        //Animation tab
        this.setOnSelectionChanged(e -> {
            if (this.isSelected()){
                Animations.fadeIn(this.getContent(), 250);
                Animations.slideIn(this.getContent(), 250);
            }
        });
    }
    //Refresh Category method
    public void refreshCategories(){
        CategoryTable categoryTable = CategoryTable.getInstance();

        categoryComboBox.setItems(
                FXCollections.observableArrayList(categoryTable.getAllCategories())
        );
        if (!categoryComboBox.getItems().isEmpty()){
            categoryComboBox.getSelectionModel().select(0);
        }
    }
    public static AddItemTab getInstance() {
        if (instance == null) {
            instance = new AddItemTab();
        }
        return instance;
    }
}