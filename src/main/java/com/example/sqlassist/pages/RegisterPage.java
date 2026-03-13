package com.example.sqlassist.pages;

import com.example.sqlassist.main.SQLAssist;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegisterPage {

    public static void show(Stage stage) {

        Label title = new Label("Register Form");
        title.setFont(new Font(25));

        Label firstNameLabel = new Label("First Name");
        TextField firstName = new TextField();
        firstName.setMaxWidth(200);

        Label lastNameLabel = new Label("Last Name");
        TextField lastName = new TextField();
        lastName.setMaxWidth(200);

        Label emailLabel = new Label("Email");
        TextField email = new TextField();
        email.setMaxWidth(200);

        Label passwordLabel = new Label("Password");
        PasswordField password = new PasswordField();
        password.setMaxWidth(200);

        // Label to show messages to user
        Label messageLabel = new Label();

        Button submitBtn = new Button("Submit");
        Button backBtn = new Button("Back");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                title,
                firstNameLabel, firstName,
                lastNameLabel, lastName,
                emailLabel, email,
                passwordLabel, password,
                submitBtn,
                messageLabel,
                backBtn
        );

        Scene scene = new Scene(layout, 700, 450);

        // This runs when the user clicks the Submit button in the Register form
        submitBtn.setOnAction(e -> {
            if (firstName.getText().isEmpty() ||
                    lastName.getText().isEmpty() ||
                    email.getText().isEmpty() ||
                    password.getText().isEmpty()) {

                messageLabel.setText("Please fill all fields!");
            } else {
                messageLabel.setText("Registration Successful!");
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