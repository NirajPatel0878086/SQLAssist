module com.example.sqlassist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sqlassist to javafx.fxml;
    exports com.example.sqlassist;
    exports com.example.sqlassist.pages;
    opens com.example.sqlassist.pages to javafx.fxml;
    exports com.example.sqlassist.database;
    opens com.example.sqlassist.database to javafx.fxml;
    exports com.example.sqlassist.main;
    opens com.example.sqlassist.main to javafx.fxml;
}