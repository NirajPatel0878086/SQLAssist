package com.example.sqlassist.tabs;

import com.example.sqlassist.models.Category;
import com.example.sqlassist.models.Item;
import com.example.sqlassist.table.CategoryTable;
import com.example.sqlassist.table.ItemTable;
import com.example.sqlassist.utils.Animations;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ViewByCategoryTab  extends Tab {
    private static ViewByCategoryTab instance;

    private ViewByCategoryTab(){
        this.setText("View by Category");

        BorderPane root = new BorderPane();
        //background color
        root.setStyle("-fx-background-color: #BDC4CB;");

        CategoryTable categoryTable = CategoryTable.getInstance();
        ItemTable itemTable = ItemTable.getInstance();

        //Top Area
        HBox topBox = new HBox(10);

        Text categoryLabel = new Text("Category");
        categoryLabel.setStyle("-fx-font-weight: bold;");
        ComboBox<Category> categoryComboBox = new ComboBox<>();
        categoryComboBox.setPrefWidth(200);
        categoryComboBox.setItems(FXCollections.observableArrayList(categoryTable.getAllCategories()));

        if (!categoryComboBox.getItems().isEmpty()){
            categoryComboBox.getSelectionModel().select(0);
        }

        Button loadBtn = new Button("Load");

        //Hover and click animation
        Animations.addHoverScale(loadBtn);
        loadBtn.setOnMouseClicked(e-> Animations.clickBounce(loadBtn));

        topBox.getChildren().addAll(categoryLabel,categoryComboBox ,loadBtn);

        //Table
        TableView<Item> tableView = new TableView<>();

        TableColumn<Item, Number> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(e ->
                new SimpleIntegerProperty(e.getValue().getId()));

        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(e ->
                new SimpleStringProperty(e.getValue().getName()));

        TableColumn<Item, Number> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(e->
                new SimpleIntegerProperty(e.getValue().getQuantity()));

        TableColumn<Item, Number> priceColum = new TableColumn<>("Price");
        priceColum.setCellValueFactory(e->
                new SimpleDoubleProperty(e.getValue().getPrice()));

        TableColumn<Item, Number> categoryColumn = new TableColumn<>("Category ID");
        categoryColumn.setCellValueFactory(e ->
                new SimpleIntegerProperty(e.getValue().getCategoryId()));

        tableView.getColumns().addAll(
                idColumn,
                nameColumn,
                quantityColumn,
                priceColum,
                categoryColumn
        );

        loadBtn.setOnAction(e -> {
            Category selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                tableView.getItems().clear();
                AddItemTab.getInstance().refreshCategories();
                tableView.getItems().addAll(
                        itemTable.getItemsByCategoryId(selectedCategory.getId())
                );
            }
        });
        root.setTop(topBox);
        root.setCenter(tableView);

        this.setContent(root);

        //Animation tab
        this.setOnSelectionChanged(e-> {
            if (this.isSelected()) {
                Animations.fadeIn(this.getContent(), 250);
                Animations.slideIn(this.getContent(), 250);
            }
        });
    }
    public static ViewByCategoryTab getInstance(){
        if (instance == null){
            instance = new ViewByCategoryTab();
        }
        return instance;
    }
}
