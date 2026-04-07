package com.example.sqlassist.tabs;

import com.example.sqlassist.models.Category;
import com.example.sqlassist.models.Item;
import com.example.sqlassist.table.CategoryTable;
import com.example.sqlassist.table.ItemTable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class UpdateItemTab extends Tab {
    private static UpdateItemTab instance;

    private UpdateItemTab() {
        this.setText("Update Item");

        BorderPane root = new BorderPane();

        CategoryTable categoryTable = CategoryTable.getInstance();
        ItemTable itemTable = ItemTable.getInstance();

        // Top Area
        HBox topBox = new HBox(10);

        Text categoryLabel = new Text("Category");
        ComboBox<Category> categoryComboBox = new ComboBox<>();
        categoryComboBox.setPrefWidth(200);
        categoryComboBox.setItems(FXCollections.observableArrayList(categoryTable.getAllCategories()));

        if (!categoryComboBox.getItems().isEmpty()) {
            categoryComboBox.getSelectionModel().select(0);
        }

        Button loadBtn = new Button("Load");

        topBox.getChildren().addAll(categoryLabel, categoryComboBox, loadBtn);

        // Table to display data
        TableView<Item> tableView = new TableView<>();

        TableColumn<Item, Number> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(e ->
                new SimpleIntegerProperty(e.getValue().getId()));

        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(e ->
                new SimpleStringProperty(e.getValue().getName()));

        TableColumn<Item, Number> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(e ->
                new SimpleIntegerProperty(e.getValue().getQuantity()));

        TableColumn<Item, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(e ->
                new SimpleDoubleProperty(e.getValue().getPrice()));

        TableColumn<Item, Number> categoryColumn = new TableColumn<>("Category ID");
        categoryColumn.setCellValueFactory(e ->
                new SimpleIntegerProperty(e.getValue().getCategoryId()));

        tableView.getColumns().addAll(
                idColumn,
                nameColumn,
                quantityColumn,
                priceColumn,
                categoryColumn
        );

        // Update form using Grid
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        Text nameLabel = new Text("Item Name");
        TextField nameField = new TextField();

        Text quantityLabel = new Text("Quantity");
        TextField quantityField = new TextField();

        Text priceLabel = new Text("Price");
        TextField priceField = new TextField();

        Button updateBtn = new Button("Update");

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);

        form.add(quantityLabel, 0, 1);
        form.add(quantityField, 1, 1);

        form.add(priceLabel, 0, 2);
        form.add(priceField, 1, 2);


        form.add(updateBtn, 1, 4);

        // Load Action will load items by category
        loadBtn.setOnAction(e -> {
            Category selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                tableView.getItems().clear();
                tableView.getItems().addAll(
                        itemTable.getItemsByCategoryId(selectedCategory.getId())
                );
            }
        });

        // When row selected that data will fill in the form so user can update from their
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameField.setText(newValue.getName());
                quantityField.setText(String.valueOf(newValue.getQuantity()));
                priceField.setText(String.valueOf(newValue.getPrice()));

            }
        });

        // Update Action will update your selected data
        updateBtn.setOnAction(e -> {
            Item selectedItem = tableView.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                try {
                    selectedItem.setName(nameField.getText());
                    selectedItem.setQuantity(Integer.parseInt(quantityField.getText()));
                    selectedItem.setPrice(Double.parseDouble(priceField.getText()));

                    itemTable.updateItem(selectedItem);

                    tableView.getItems().clear();

                    Category selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
                    if (selectedCategory != null) {
                        tableView.getItems().addAll(
                                itemTable.getItemsByCategoryId(selectedCategory.getId())
                        );
                    }

                    //This will refresh your data in Remove tab
                    RemoveItemTab.getInstance().refreshTable();
                    //This will refresh your data in your Statistics Tab
                    StatisticsTab.getInstance().generateChart();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        root.setTop(topBox);
        root.setCenter(tableView);
        root.setRight(form);

        this.setContent(root);
    }

    public static UpdateItemTab getInstance() {
        if (instance == null) {
            instance = new UpdateItemTab();
        }
        return instance;
    }
}