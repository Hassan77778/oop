package com.example.project123;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1Control {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/project123/Scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false); // Set full-screen mode
        stage.show();
    }

    public void changeToScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/project123/Scene3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false); // Set full-screen mode
        stage.show();
    }

    public void changeToScene9(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/project123/Scene9.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true); // Set full-screen mode
        stage.show();
    }
}
