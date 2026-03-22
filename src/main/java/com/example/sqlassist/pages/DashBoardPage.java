package com.example.sqlassist.pages;

import com.example.sqlassist.database.Database;
import com.example.sqlassist.main.SQLAssist;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DashBoardPage {

    public static void show(Stage stage) {

        Label title = new Label("SQL Assist Dashboard");
        title.setFont(new Font("Arial", 28));

        Label messageLabel = new Label();

        Button settingsBtn = new Button("Account Settings");
        Button initBtn = new Button("Initialize Database");
        Button logoutBtn = new Button("Logout");

        settingsBtn.setPrefWidth(220);
        initBtn.setPrefWidth(220);
        logoutBtn.setPrefWidth(220);

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                title,
                settingsBtn,
                initBtn,
                messageLabel,
                logoutBtn
        );

        Scene scene = new Scene(layout, 700, 450);

        settingsBtn.setOnAction(e -> {
            AccountSettingPage.show(stage);
        });

        initBtn.setOnAction(e -> {
            try {
                Database.getInstance();
                messageLabel.setText("Database initialized successfully!");
            } catch (Exception ex) {
                messageLabel.setText("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

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