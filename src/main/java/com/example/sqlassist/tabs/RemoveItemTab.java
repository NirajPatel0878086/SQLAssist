package com.example.sqlassist.tabs;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import com.example.sqlassist.models.Item;
import com.example.sqlassist.table.ItemTable;

public class RemoveItemTab extends Tab {

    private static RemoveItemTab instance;
    public TableView<Item> tableView;

    private RemoveItemTab() {

        this.setText("Remove Item");

        ItemTable itemTable = ItemTable.getInstance();

        BorderPane root = new BorderPane();

        // Background CSS
        root.setStyle("-fx-background-color: lightblue; -fx-padding:20;");

        tableView = new TableView<>();
        tableView.setStyle("-fx-font-size:14px;");

        TableColumn<Item, Number> idColumn = new TableColumn<>("ID");
        idColumn.setStyle("-fx-font-weight:bold;");
        idColumn.setCellValueFactory(e ->
                new SimpleIntegerProperty(e.getValue().getId()));

        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setStyle("-fx-font-weight:bold;");
        nameColumn.setCellValueFactory(e ->
                new SimpleStringProperty(e.getValue().getName()));

        TableColumn<Item, Number> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setStyle("-fx-font-weight:bold;");
        quantityColumn.setCellValueFactory(e ->
                new SimpleIntegerProperty(e.getValue().getQuantity()));

        TableColumn<Item, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setStyle("-fx-font-weight:bold;");
        priceColumn.setCellValueFactory(e ->
                new SimpleDoubleProperty(e.getValue().getPrice()));

        TableColumn<Item, Number> categoryColumn = new TableColumn<>("Category ID");
        categoryColumn.setStyle("-fx-font-weight:bold;");
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

        root.setCenter(tableView);

        Button removeItem = new Button("Remove Item");

        // Button CSS white button with black border
        removeItem.setStyle(
                "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-color:white;" +
                        "-fx-text-fill:black;" +
                        "-fx-border-color:black;" +
                        "-fx-border-width:2;"
        );

        removeItem.setPrefWidth(160);

        removeItem.setOnAction(e -> {

            Item selectedItem = tableView.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                itemTable.deleteItem(selectedItem.getId());
                refreshTable();
                StatisticsTab.getInstance().generateChart();
            }
        });

        // Center the button
        HBox buttonBox = new HBox(removeItem);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-padding:15;");

        root.setBottom(buttonBox);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                UpdateItemPane pane = new UpdateItemPane(newValue);
                root.setRight(pane);
            }
        });

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