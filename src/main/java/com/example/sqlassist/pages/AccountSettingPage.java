package com.example.sqlassist.pages;

import com.example.sqlassist.models.DbSetting;
import com.example.sqlassist.utils.FileUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccountSettingPage {

    public static void show(Stage stage) {

        Label title = new Label("Account Settings");

        TextField serverField = new TextField();
        serverField.setPromptText("Server Location");

        TextField dbField = new TextField();
        dbField.setPromptText("Database Name");

        TextField userField = new TextField();
        userField.setPromptText("Username");

        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");

        Label message = new Label();

        Button saveBtn = new Button("Save Settings");
        Button testBtn = new Button("Test Connection");
        Button backBtn = new Button("Back");

        DbSetting old = FileUtil.load();
        if (old != null) {
            serverField.setText(old.server);
            dbField.setText(old.database);
            userField.setText(old.username);
            passField.setText(old.password);
        }

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                title,
                serverField,
                dbField,
                userField,
                passField,
                saveBtn,
                testBtn,
                message,
                backBtn
        );

        Scene scene = new Scene(layout, 700, 450);

        saveBtn.setOnAction(e -> {

            if (serverField.getText().isEmpty() ||
                    dbField.getText().isEmpty() ||
                    userField.getText().isEmpty() ||
                    passField.getText().isEmpty()) {

                message.setText("Please fill all fields!");
                return;
            }

            DbSetting setting = new DbSetting(
                    serverField.getText(),
                    dbField.getText(),
                    userField.getText(),
                    passField.getText()
            );

            FileUtil.save(setting);
            message.setText("Settings Saved!");
        });

        testBtn.setOnAction(e -> {
            message.setText("Use Initialize Database to test connection");
        });

        backBtn.setOnAction(e -> DashBoardPage.show(stage));

        stage.setScene(scene);
    }
}