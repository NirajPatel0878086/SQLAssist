package com.example.sqlassist.pages;

import com.example.sqlassist.main.SQLAssist;
import com.example.sqlassist.session.AppSession;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class DashboardPage {

    public static void show(Stage stage) {

        Label title = new Label("SQL Assist Dashboard");
        title.setFont(new Font("Arial", 28));

        Label dbLabel = new Label("Current Database: " + AppSession.currentDatabase);

        Button createDatabaseBtn = new Button("Create Database");
        Button logoutBtn = new Button("Logout");

        createDatabaseBtn.setPrefWidth(220);
        logoutBtn.setPrefWidth(220);

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                title,
                dbLabel,
                createDatabaseBtn,
                logoutBtn
        );

        Scene scene = new Scene(layout, 700, 450);

        createDatabaseBtn.setOnAction(e -> CreateDatabasePage.show(stage));

        logoutBtn.setOnAction(e -> {
            try {
                new SQLAssist().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        stage.setScene(scene);
    }
}