package com.example.sqlassist.main;

import com.example.sqlassist.database.Database;
import com.example.sqlassist.pages.HomePage;
import com.example.sqlassist.tabs.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SQLAssist extends Application {

    @Override
    public void start(Stage stage) {
        //Open Homepage
        HomePage.show(stage);
    }
    //This method open main application
    public static void showMainApp(Stage stage) {
        BorderPane root = new BorderPane();

        //Menu bar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem exit = new MenuItem("Exit");

        fileMenu.getItems().add(exit);
        menuBar.getMenus().add(fileMenu);

        //Exit button
        exit.setOnAction(e -> System.exit(0));

        //Tab pane
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(
                AddCategoryTab.getInstance(),
                AddItemTab.getInstance(),
                UpdateItemTab.getInstance(),
                RemoveItemTab.getInstance(),
                ViewByCategoryTab.getInstance(),
                StatisticsTab.getInstance()
        );
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        root.setTop(menuBar);
        root.setCenter(tabPane);

        //Initialize database
        Database.getInstance();

        Scene scene = new Scene(root, 700, 500);
        stage.setTitle("SQL Assist");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}