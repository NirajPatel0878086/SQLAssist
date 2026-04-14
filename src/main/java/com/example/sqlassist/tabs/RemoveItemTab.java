package com.example.sqlassist.tabs;

import com.example.sqlassist.utils.Animations;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import com.example.sqlassist.models.Item;
import com.example.sqlassist.table.ItemTable;
import javafx.scene.layout.HBox;

public class RemoveItemTab extends Tab {

    private static RemoveItemTab instance;
    public TableView<Item> tableView;

    private RemoveItemTab() {
        this.setText("Remove Item");

        ItemTable itemTable = ItemTable.getInstance();

        BorderPane root = new BorderPane();
        //background color
        root.setStyle("-fx-background-color: #BDC4CB;");

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


        //Hover animation
        Animations.addHoverScale(removeItem);

        //bounce animation
        removeItem.setOnMouseClicked(e-> Animations.clickBounce(removeItem));

        removeItem.setOnAction(e -> {
            Item selectedItem = tableView.getSelectionModel().getSelectedItem();

            if (selectedItem == null) {
                Animations.shake(tableView);
                return;
            }
            itemTable.deleteItem(selectedItem.getId());
            refreshTable();
            StatisticsTab.getInstance().generateChart();
        });
        HBox bottomBox = new HBox(removeItem);
        bottomBox.setAlignment(Pos.CENTER);
        root.setBottom(bottomBox);

        this.setContent(root);

        //Animation tab
        this.setOnSelectionChanged(e-> {
            if(this.isSelected()){
                Animations.fadeIn(this.getContent(), 250);
                Animations.slideIn(this.getContent(), 250);
            }
        });
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