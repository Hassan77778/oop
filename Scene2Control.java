package com.example.project123;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.scene.control.Alert.AlertType;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


public class Scene2Control {

    @FXML
    private ToggleButton peekButton;

    @FXML
    private TextField peekfield;
    @FXML
    public Button submitButton;
    @FXML
    private TextField name;
    @FXML
    private Customer ctest;
    private Admin atest;
    @FXML
    private PasswordField password;

    private String n;
    private String p;

    @FXML
    public void initialize() {
        // Verify that components are loaded
        System.out.println("Name field reference: " + name);
        System.out.println("Password field reference: " + password);
        peekfield.textProperty().bindBidirectional(password.textProperty());

        // Add listener to ToggleButton
        peekButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Show the plain text field
                peekfield.setVisible(true);
                password.setVisible(false);
            } else {
                // Show the password field
                peekfield.setVisible(false);
                password.setVisible(true);
            }
        });
    }

    public void showPopup() {
        Alert alert = new Alert(AlertType.INFORMATION); // You can change the AlertType to ERROR, WARNING, etc.
        alert.setTitle("Login Status");
        alert.setHeaderText("Login Successful");
        alert.setContentText("Welcome to the application!");

        alert.showAndWait(); // Shows the alert and waits for user response
    }
    public void showTimedPopup(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Temporary Message");
        alert.setHeaderText(null); // No header
        alert.setContentText(message);

        alert.show(); // Show the alert

        // Create a PauseTransition for 6 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> alert.close()); // Close the alert after 6 seconds
        delay.play(); // Start the timer
    }
    String message1= "Login successfully as a Customer ";
    String message2="Login successfully as an Admin";
    String message3="Login Failed, Login again or Signup";
    @FXML
    public void submit(ActionEvent event) {
        try {

            n = name.getText().trim(); // Use .trim() to remove extra spaces
            System.out.println("Name: " + n);

            p = password.getText().trim();
            System.out.println("Password: " + p);

//            // Update label (Optional)
//            if (welcomeText != null) {
//                welcomeText.setText("Welcome, " + n + "!");
//            }
            // Database d=new Database();


             ctest = new Customer().Login(n,p);
             atest= new Admin().Login(n,p);

            if (ctest != null) {
                System.out.println("Login as customer");
                System.out.println("Welcome " + ctest.getUsername());
                showTimedPopup(message1);
                changeToScene14(event);

            }
            else if(atest != null){
                System.out.println("Login as Admin");
                System.out.println("Welcome " + atest.getUsername());
                showTimedPopup(message2);
                changeToScene5(event);
            }
            else {
                System.out.println("Login failed, try again");
                showTimedPopup(message3);
                changeToScene1(event);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        //changeToScene3(event); // Pass event to the method
    }
    public void changeToScene1(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project123/Scene1.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void changeToScene14(ActionEvent event) {
        try {
            // Load the FXML file for Scene 4
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene14.fxml"));
            Parent root = loader.load();

            // Get the Scene4 controller
            Scene14Control scene14Controller = loader.getController();

            // Pass the username and password to Scene4 controller
            scene14Controller.setCustomer(ctest);

            // Switch to the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToScene9(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project123/Scene9.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToScene5(ActionEvent event) {
        try {
            // Load the FXML file for Scene 4
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene5.fxml"));
            Parent root = loader.load();

            // Get the Scene4 controller
            Scene5Control scene5Controller = loader.getController();

            // Pass the username and password to Scene4 controller
            scene5Controller.setAdmin(atest);

            // Switch to the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}