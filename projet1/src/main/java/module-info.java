module com.example.projet1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projet1 to javafx.fxml;
    exports com.example.projet1;
}