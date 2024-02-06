module com.example.laba3_proekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.laba3_proekt to javafx.fxml;
    exports com.example.laba3_proekt;
}