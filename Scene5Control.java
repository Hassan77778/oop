package com.example.project123;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene5Control implements Initializable {
    @FXML
    private ChoiceBox<String> Options;
    public void showTimedPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> alert.close());
        delay.play();
    }
    public Admin admin;
    public void setAdmin(Admin admin) {
        this.admin = admin;
        if (admin != null) {
            System.out.println("Admin Name: " + admin.getUsername());
        } else {
            System.out.println("Admin is null.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Options.getItems().addAll("View Orders","Add new Products and Categories","Edit Admins","Edit Products","View Categories");

    }
    public void go(ActionEvent event) throws IOException {
        String option = Options.getSelectionModel().getSelectedItem();
        if (option.equals("View Orders")){
            changeToScene12(event);
        } else if (option.equalsIgnoreCase("Add new Products and Categories")) {
            changeToScene11(event);
        }
        else if(option.equals("Edit Admins")){
             if(admin.getRole().equalsIgnoreCase(("Boss"))){
                 changeToScene10(event);
            }
            else{
                showTimedPopup("You are not a boss to edit other admin details");
                return;
            }

        }
        else if (option.equals("View Categories")){
            changeToScene4(event);
        }
        else {
            changeToScene15(event);
        }
    }
    public void changeToScene10(ActionEvent event) {
        try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene10.fxml"));
            Parent root = loader.load();

           
            Scene10Control scene10Controller = loader.getController();

         
            scene10Controller.setAdmin(admin);

            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void changeToScene11(ActionEvent event) {
        try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene11.fxml"));
            Parent root = loader.load();

       
            Scene11Control scene11Controller = loader.getController();

        
            scene11Controller.setAdmin(admin);

            // Switch to the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToScene12(ActionEvent event) {
        try {
            // Load the FXML file for Scene 4
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene12.fxml"));
            Parent root = loader.load();
            Scene12Control scene12Controller = loader.getController();

 
            scene12Controller.setAdmin(admin);

            // Switch to the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToScene4(ActionEvent event) {
        try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene4.fxml"));
            Parent root = loader.load();

            Scene4Control scene4Controller = loader.getController();

            scene4Controller.setAdmin(admin);

         
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void changeToScene15(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene15.fxml"));
            Parent root = loader.load();
\
            Scene15Control scene15Controller = loader.getController();

 
            scene15Controller.setAdmin(admin);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToScene2(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project123/Scene2.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getMessage();
        }
    }




}
