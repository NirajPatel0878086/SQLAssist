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
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Labels
        Label serverLabel = new Label("Server Location");
        Label dbLabel = new Label("Database Name");
        Label userLabel = new Label("Username");
        Label passLabel = new Label("Password");

        serverLabel.setStyle("-fx-font-weight: bold;");
        dbLabel.setStyle("-fx-font-weight: bold;");
        userLabel.setStyle("-fx-font-weight: bold;");
        passLabel.setStyle("-fx-font-weight: bold;");

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

        //Buttons
        Button saveBtn = new Button("Save Settings");
        Button testBtn = new Button("Test Connection");
        Button backBtn = new Button("Back");

        //message label for showing result
        Label messageLabel = new Label();

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

            //sound
            SoundPlayer.click();
            DbSetting setting = new DbSetting(
                    serverField.getText(),
                    dbField.getText(),
                    userField.getText(),
                    passField.getText()
            );

            try {
                //Save login information to settings
                FileUtil.save(setting);
                messageLabel.setText("Settings saved successfully.");

                //sound
                SoundPlayer.playSuccess();
                //if info correct and saved than it will open main application
                SQLAssist.showMainApp(stage);
            } catch (Exception ex) {
                messageLabel.setText("Failed to save settings.");

                //sound and animation
                SoundPlayer.playError();
                Animations.shake(messageLabel);
            }
        });

        // Test Database connection button
        testBtn.setOnAction(e -> {

            //sound
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

                //sound
                SoundPlayer.playSuccess();
            } catch (Exception ex) {
                messageLabel.setText("Connection failed.");

                //sound and animation
                SoundPlayer.playError();
                Animations.shake(messageLabel);
                ex.printStackTrace();
            }
        });

        //Back button to go home
        backBtn.setText("Back");
        backBtn.setOnAction(e -> {

            //sound
            SoundPlayer.click();
            HomePage.show(stage);
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

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

        //Background color
        layout.setStyle("-fx-background-color: #AAB4BC;");

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