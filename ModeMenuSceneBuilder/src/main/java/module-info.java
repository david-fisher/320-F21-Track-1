module com.example.testcontroller {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.testcontroller to javafx.fxml;
    exports com.example.testcontroller;
}