package com.example.project123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class Scene13Control implements Initializable {

    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label dateOfBirthLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label InterestsLabel;
    @FXML
    private Button backButton;

    private Customer customer; // Store the current customer object

    public void setCustomer(Customer customer) {
        this.customer = customer;

        if (customer != null) {
            usernameLabel.setText("Username: " + customer.getUsername());
            passwordLabel.setText("Password: " + "");
            dateOfBirthLabel.setText("Date of Birth: " + customer.getDate_of_birth());
            genderLabel.setText("Gender: " + customer.getGen());
            phoneLabel.setText("Phone Number: " + customer.getPhonenumber());
            addressLabel.setText("Address: " + customer.getAddress());
            balanceLabel.setText("Balance: " + String.format("%.2f", customer.getBalance()));
            InterestsLabel.setText("Interest: "+customer.getInterest());
        } else {
            // Handle the case where customer is null (e.g., display error message)
            System.out.println("No customer object received!");
        }
    }


    @FXML
    public void backToPreviousScene(ActionEvent event) throws IOException {

        try {
            // Load the FXML file for Scene 4
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene14.fxml"));
            Parent root = loader.load();

            // Get the Scene4 controller
            Scene14Control scene14Controller = loader.getController();

            // Pass the username and password to Scene4 controller
            scene14Controller.setCustomer(customer);

            // Switch to the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}