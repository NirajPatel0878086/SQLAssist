package com.example.sqlassist.main;

import com.example.sqlassist.pages.AccountSettingPage;
import com.example.sqlassist.pages.DashBoardPage;
import com.example.sqlassist.pages.DashBoardPage;
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
    public void start(Stage stage) {

        Label title = new Label("SQL ASSIST");
        title.setFont(new Font("Arial", 40));

        Label subtitle = new Label("Database Management Application");

        Button settingsBtn = new Button("Account Settings");
        Button dashboardBtn = new Button("Dashboard");
        Button exitBtn = new Button("Exit");

        settingsBtn.setPrefWidth(220);
        dashboardBtn.setPrefWidth(220);
        exitBtn.setPrefWidth(220);

        VBox pane = new VBox(15);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(
                title,
                subtitle,
                settingsBtn,
                dashboardBtn,
                exitBtn
        );

        Scene homeScene = new Scene(pane, 700, 450);

        settingsBtn.setOnAction(e -> AccountSettingPage.show(stage));
        dashboardBtn.setOnAction(e -> DashBoardPage.show(stage));
        exitBtn.setOnAction(e -> stage.close());

        stage.setTitle("SQL Assist");
        stage.setScene(homeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}