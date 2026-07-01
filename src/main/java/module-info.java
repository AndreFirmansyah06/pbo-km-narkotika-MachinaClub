module com.example.tugasbesarpbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.csv;


    opens com.example.tugasbesarpbo to javafx.fxml;
    exports com.example.tugasbesarpbo;
}