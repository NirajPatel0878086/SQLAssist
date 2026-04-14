package com.example.sqlassist.tabs;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CreditTab extends Tab {

    private static CreditTab instance;

    private CreditTab() {
        this.setText("Credits");

        VBox root = new VBox(15);
        //Centre everything
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20;");

        // Title
        Text title = new Text("Credits");
        title.setFont(new Font(22));
        title.setStyle("-fx-font-weight: bold;");

        // Project info
        Text projectInfo = new Text(
                "This project is developed based on concepts learned in class.\n" +
                        "It helps to understand database connectivity and JavaFX."
        );
        projectInfo.setFont(new Font(14));
        projectInfo.setStyle("-fx-font-weight: bold;");

        // Learning sources
        Text learning = new Text(
                "Learning Sources:\n" +
                        "Class lectures and notes\n" +
                        "JavaFX and JDBC tutorials\n" +
                        "Youtube"
        );
        learning.setFont(new Font(14));
        learning.setStyle("-fx-font-weight: bold;");

        // Websites
        Text websites = new Text(
                "Websites Used:\n" +
                        "W3Schools\n" +
                        "GeeksforGeeks"
        );
        websites.setFont(new Font(14));
        websites.setStyle("-fx-font-weight: bold;");

        // Tools
        Text tools = new Text(
                "Tools Used:\n" +
                        "IntelliJ IDEA\n" +
                        "MySQL\n" +
                        "JavaFX"
        );
        tools.setFont(new Font(14));
        tools.setStyle("-fx-font-weight: bold;");

        // Guidance
        Text guidance = new Text(
                "Guidance:\n" +
                        "Instructor support during lectures"
        );
        guidance.setFont(new Font(14));
        guidance.setStyle("-fx-font-weight: bold;");

        root.getChildren().addAll(
                title,
                projectInfo,
                learning,
                websites,
                tools,
                guidance
        );

        this.setContent(root);
    }

    public static CreditTab getInstance() {
        if (instance == null) {
            instance = new CreditTab();
        }
        return instance;
    }
}