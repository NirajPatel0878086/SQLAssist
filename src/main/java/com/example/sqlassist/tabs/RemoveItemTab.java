package com.example.sqlassist.tabs;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import com.example.sqlassist.models.Item;
import com.example.sqlassist.table.ItemTable;

public class RemoveItemTab extends Tab {

    private static RemoveItemTab instance;
    public TableView<Item> tableView;

    private RemoveItemTab() {
        this.setText("Remove Item");

        ItemTable itemTable = ItemTable.getInstance();

        BorderPane root = new BorderPane();

        tableView = new TableView<>();

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

        tableView.getItems().addAll(itemTable.getAllItems());
        refreshTable();
        root.setCenter(tableView);

        Button removeItem = new Button("Remove Item");
        removeItem.setOnAction(e -> {
            Item selectedItem = tableView.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                itemTable.deleteItem(selectedItem.getId());
                refreshTable();
                StatisticsTab.getInstance().generateChart();
            }
        });
        root.setBottom(removeItem);

        this.setContent(root);
    }

    public void refreshTable() {
        ItemTable itemTable = ItemTable.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(itemTable.getAllItems());
    }

    public static RemoveItemTab getInstance() {
        if (instance == null) {
            instance = new RemoveItemTab();
        }
        return instance;
    }
}