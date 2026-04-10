package com.example.sqlassist.pages;
import com.example.sqlassist.SoundPlayer;
import com.example.sqlassist.database.Database;
import com.example.sqlassist.main.SQLAssist;

import com.example.sqlassist.models.DbSetting;
import com.example.sqlassist.utils.Animations;
import com.example.sqlassist.utils.FileUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AccountSettingPage {

    public static void show(Stage stage) {

        //page title
        Label title = new Label("Account Settings");
        //set title font size and bold type
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Labels
        Label serverLabel = new Label("Server Location");
        Label dbLabel = new Label("Database Name");
        Label userLabel = new Label("Username");
        Label passLabel = new Label("Password");
        //set lable font size and make it bold
        serverLabel.setStyle("-fx-font-size:14px; -fx-font-weight:bold;");
        dbLabel.setStyle("-fx-font-size:14px; -fx-font-weight:bold;");
        userLabel.setStyle("-fx-font-size:14px; -fx-font-weight:bold;");
        passLabel.setStyle("-fx-font-size:14px; -fx-font-weight:bold;");

        // Input Fields
        TextField serverField = new TextField();
        TextField dbField = new TextField();
        TextField userField = new TextField();
        PasswordField passField = new PasswordField();

        // Width control
        serverField.setMaxWidth(250);
        dbField.setMaxWidth(250);
        userField.setMaxWidth(250);
        passField.setMaxWidth(250);

        // CSS for input fields set with background radius with font size
        serverField.setStyle("-fx-font-size:14px; -fx-background-radius:8;");
        dbField.setStyle("-fx-font-size:14px; -fx-background-radius:8;");
        userField.setStyle("-fx-font-size:14px; -fx-background-radius:8;");
        passField.setStyle("-fx-font-size:14px; -fx-background-radius:8;");

        //Buttons
        Button saveBtn = new Button("Save Settings");
        Button testBtn = new Button("Test Connection");
        Button backBtn = new Button("Back");

        // Button CSS background color, font size and bold type
        saveBtn.setStyle("-fx-font-size:14px; -fx-font-weight:bold; -fx-background-color:#4CAF50; -fx-text-fill:white;");
        testBtn.setStyle("-fx-font-size:14px; -fx-font-weight:bold; -fx-background-color:#2196F3; -fx-text-fill:white;");
        backBtn.setStyle("-fx-font-size:14px; -fx-font-weight:bold; -fx-background-color:#9e9e9e; -fx-text-fill:white;");

        //message label
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size:13px; -fx-text-fill:#e74c3c; -fx-font-weight:bold;");

        // Load saved settings
        try {
            DbSetting setting = FileUtil.load();
            if (setting != null) {
                serverField.setText(setting.server);
                dbField.setText(setting.database);
                userField.setText(setting.username);
                passField.setText(setting.password);
            }
        } catch (Exception e) {
            messageLabel.setText("Failed to load settings");
        }

        // Save button
        saveBtn.setOnAction(e -> {

            SoundPlayer.click();

            DbSetting setting = new DbSetting(
                    serverField.getText(),
                    dbField.getText(),
                    userField.getText(),
                    passField.getText()
            );

            try {
                FileUtil.save(setting);
                messageLabel.setText("Settings saved successfully.");

                SoundPlayer.playSuccess();
                SQLAssist.showMainApp(stage);

            } catch (Exception ex) {

                messageLabel.setText("Failed to save settings.");

                SoundPlayer.playError();
                Animations.shake(messageLabel);
            }
        });

        // Test Database connection
        testBtn.setOnAction(e -> {

            SoundPlayer.click();

            try {

                DbSetting setting = new DbSetting(
                        serverField.getText(),
                        dbField.getText(),
                        userField.getText(),
                        passField.getText()
                );

                FileUtil.save(setting);

                Database.getInstance();

                messageLabel.setText("Connection successful.");
                SoundPlayer.playSuccess();

            } catch (Exception ex) {

                messageLabel.setText("Connection failed.");

                SoundPlayer.playError();
                Animations.shake(messageLabel);
                ex.printStackTrace();
            }
        });

        //Back button
        backBtn.setOnAction(e -> {

            SoundPlayer.click();
            HomePage.show(stage);

        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER_LEFT);

        // Page background CSS
        layout.setStyle("-fx-background-color:lightblue;");

        layout.getChildren().addAll(
                title,
                serverLabel,
                serverField,
                dbLabel,
                dbField,
                userLabel,
                userField,
                passLabel,
                passField,
                saveBtn,
                testBtn,
                messageLabel,
                backBtn
        );

        //Animations
        Animations.fadeIn(layout, 400);
        Animations.addHoverScale(saveBtn);
        Animations.addHoverScale(testBtn);
        Animations.addHoverScale(backBtn);

        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
}