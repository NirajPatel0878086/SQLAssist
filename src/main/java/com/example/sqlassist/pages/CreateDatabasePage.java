package com.example.sqlassist.pages;

import com.example.sqlassist.session.AppSession;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

/*
 * This page is used to create a new SQLite database file.
 */
public class CreateDatabasePage {

    public static void show(Stage stage) {

        Label title = new Label("Create Database");
        title.setFont(new Font("Arial", 24));

        Label nameLabel = new Label("Enter Database Name");
        TextField dbNameField = new TextField();
        dbNameField.setMaxWidth(220);

        Label messageLabel = new Label();

        Button createBtn = new Button("Create");
        Button backBtn = new Button("Back");

        createBtn.setPrefWidth(200);
        backBtn.setPrefWidth(200);

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                title,
                nameLabel,
                dbNameField,
                createBtn,
                messageLabel,
                backBtn
        );

        Scene scene = new Scene(layout, 700, 450);

        createBtn.setOnAction(e -> {
            String dbName = dbNameField.getText();

            if (dbName.isEmpty()) {
                messageLabel.setText("Please enter database name!");
                return;
            }

            try {
                File file = new File(dbName + ".db");

                if (file.createNewFile()) {
                    AppSession.currentDatabase = dbName;
                    messageLabel.setText("Database created successfully!");
                } else {
                    AppSession.currentDatabase = dbName;
                    messageLabel.setText("Database already exists!");
                }

            } catch (Exception ex) {
                messageLabel.setText("Error: " + ex.getMessage());
            }
        });

        backBtn.setOnAction(e -> DashboardPage.show(stage));

        stage.setScene(scene);
    }
}