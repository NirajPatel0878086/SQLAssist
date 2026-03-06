package com.example.sqlassist;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SQLAssist extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("SQL ASSIST");
        title.setFont(new Font("Arial", 40));

        Label subtitle = new Label("Easy SQL operations without complex queries");

        Button registerBtn = new Button("Register");
        Button loginBtn = new Button("Login");
        Button exitBtn = new Button("Exit");

        registerBtn.setPrefWidth(200);
        loginBtn.setPrefWidth(200);
        exitBtn.setPrefWidth(200);

        VBox pane = new VBox(15);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(title, subtitle, registerBtn, loginBtn, exitBtn);

        Scene homeScene = new Scene(pane, 700, 450);

        registerBtn.setOnAction(e -> RegisterPage.show(stage));
        loginBtn.setOnAction(e -> LoginPage.show(stage));

        exitBtn.setOnAction(e -> stage.close());

        stage.setTitle("SQL Assist");
        stage.setScene(homeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}