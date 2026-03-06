package com.example.sqlassist;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginPage {

    public static void show(Stage stage) {

        Label title = new Label("Login Form");
        title.setFont(new Font(25));

        Label emailLabel = new Label("Email");
        TextField email = new TextField();
        email.setMaxWidth(200);

        Label passwordLabel = new Label("Password");
        PasswordField password = new PasswordField();
        password.setMaxWidth(200);

        Label messageLabel = new Label();

        Button loginBtn = new Button("Login");
        Button backBtn = new Button("Back");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                title,
                emailLabel, email,
                passwordLabel, password,
                loginBtn,
                messageLabel,
                backBtn
        );

        Scene scene = new Scene(layout, 700, 450);

        // This runs when the user clicks the Login button
        loginBtn.setOnAction(e -> {
            if (email.getText().isEmpty() || password.getText().isEmpty()) {
                messageLabel.setText("Please fill all fields!");
            } else {
                messageLabel.setText("Login Successful!");
            }
        });

        backBtn.setOnAction(e -> {
            try {
                new SQLAssist().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        stage.setScene(scene);
    }
}