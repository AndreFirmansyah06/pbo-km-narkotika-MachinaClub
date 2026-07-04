module com.example.tugasbesarpbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.csv;

    exports com.example.tugasbesarpbo.view;
    opens com.example.tugasbesarpbo.view to javafx.fxml;
    opens com.example.tugasbesarpbo.app to javafx.fxml;
    exports com.example.tugasbesarpbo.app;
}