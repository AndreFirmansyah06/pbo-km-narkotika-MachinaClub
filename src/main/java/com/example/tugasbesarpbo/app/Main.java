package com.example.tugasbesarpbo.app;

import com.example.tugasbesarpbo.controller.KnowledgeController;
import com.example.tugasbesarpbo.model.KnowledgeRepository;
import com.example.tugasbesarpbo.model.Putusan;
import com.example.tugasbesarpbo.view.FXMLController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static final KnowledgeRepository repository = new KnowledgeRepository();
    private static final KnowledgeController controller = new KnowledgeController(repository);
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("JavaFXView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        FXMLController fxController = fxmlLoader.getController();
        fxController.setController(controller);

        stage.setTitle("KMS Putusan Pengadilan Narkotika");
        stage.setScene(scene);
        stage.show();
    }
}
