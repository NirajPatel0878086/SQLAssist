module com.example.sqlassist {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sqlassist to javafx.fxml;
    exports com.example.sqlassist;
}