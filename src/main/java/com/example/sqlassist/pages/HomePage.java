package com.example.sqlassist.pages;

import com.example.sqlassist.SoundPlayer;
import com.example.sqlassist.utils.Animations;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomePage {

    //This method show the home screen
    public static void show(Stage stage) {
        //Title of application
        Label title = new Label("SQL ASSIST");
        title.setFont(new Font("Arial", 32));
        title.setStyle("-fx-font-weight: bold;");
        Label subtitle = new Label("Database Management Application");
        subtitle.setStyle("-fx-font-weight: bold;");
        //Login button
        Button loginBtn = new Button("Login");
        loginBtn.setPrefWidth(200);

        //When login button is clicked it will open account setting page
        loginBtn.setOnAction(e -> {

            //sound
            SoundPlayer.click();
            //Animation
            Animations.clickBounce(loginBtn);
            AccountSettingPage.show(stage);
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                title,
                subtitle,
                loginBtn
        );

        //Animation
        Animations.fadeIn(layout, 500);
        Animations.addHoverScale(loginBtn);
        Animations.slideIn(title, 600);
        Animations.slideIn(subtitle, 700);

        //Create scene and stage
        Scene scene = new Scene(layout, 700, 450);
        stage.setScene(scene);
        stage.show();
    }
}