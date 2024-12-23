package com.example.project123;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome to Our App");
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setFullScreenExitHint(""); // Enable full-screen mode
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

