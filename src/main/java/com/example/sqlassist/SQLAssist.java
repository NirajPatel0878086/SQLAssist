package com.example.sqlassist;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SQLAssist extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Main Title
        Label title = new Label("SQL ASSIST");
        title.setFont(new Font("Arial", 40));
        title.setStyle("-fx-font-weight: bold;");

        //Subtitle
        Label subtitle = new Label("Easy SQL operations without complex quires");
        subtitle.setFont(new Font("Arial", 16));
        subtitle.setStyle("-fx-text-fill: gray;");

        //Buttons
        Button registerBtn = new Button("Register");
        Button loginBtn = new Button("Login");
        Button startBtn = new Button("Start");
        Button exitBtn = new Button("Exit");

        //Button Width
        registerBtn.setPrefWidth(200);
        loginBtn.setPrefWidth(200);
        startBtn.setPrefWidth(200);
        exitBtn.setPrefWidth(200);

        //Button Actions
        registerBtn.setOnAction(e -> System.out.println("Register Clicked"));
        loginBtn.setOnAction(e -> System.out.println("Login CLicked"));
        startBtn.setOnAction(e -> System.out.println("Start Clicked"));
        exitBtn.setOnAction(e -> stage.close());

        //Layout
        VBox pane = new VBox(15);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(title, subtitle, registerBtn, loginBtn, startBtn, exitBtn);
        Scene scene = new Scene(pane, 700, 450);

        stage.setTitle("SQL Assist");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
