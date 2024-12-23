package com.example.project123;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class Scene9Control {

    private Stage stage;
    private Scene scene;
    private Customer customer;
    private Admin atest;
    public void setCustomer(Customer customer) {
        this.customer = customer;

        if (customer != null) {
            System.out.println("Customer : " + customer.getUsername());

        } else {
            System.out.println("Customer is null");
        }
    }

    // Navigate back to the main scene
    public void changeToScene14(ActionEvent event) throws IOException {
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

    // Open Instagram Page 1
    public void openInstagram1(ActionEvent event) {
        openWebPage("https://www.instagram.com/yourpage1");
    }

    // Open Instagram Page 2
    public void openInstagram2(ActionEvent event) {
        openWebPage("https://www.instagram.com/yourpage2");
    }

    // Utility method to open URLs
    private void openWebPage(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(URI.create(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
