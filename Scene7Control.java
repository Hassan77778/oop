package com.example.project123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Scene7Control implements Initializable {
    private Customer customer;

    @FXML
    private VBox productPane; // Changed to VBox for better layout handling
    @FXML
    private Label totalPriceLabel; // Label to display total price

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (productPane == null) {
            System.out.println("Error: productPane is null. Check FXML file for fx:id mismatch.");
        }
        if (totalPriceLabel == null) {
            System.out.println("Error: totalPriceLabel is null. Check FXML file for fx:id mismatch.");
        }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;

        if (productPane == null || totalPriceLabel == null) {
            throw new IllegalStateException("FXML components are not initialized. Check FXML file.");
        }

        double totalPrice = 0.0;
        productPane.getChildren().clear(); // Clear any existing content

        for (Map.Entry<Product, Integer> entry : customer.getcartt().getCartMap().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double price = product.getPrice() * quantity;

            // Create HBox for each product
            HBox productBox = new HBox(20); // 20px spacing between items

            // Create and style labels
            Label productName = new Label("Product: " + product.getName());
            Label productQuantity = new Label("Quantity: " + quantity);
            Label productPrice = new Label("Price: $" + String.format("%.2f", price));

            // Add all components to HBox
            productBox.getChildren().addAll(productName, productQuantity, productPrice);

            // Add productBox to productPane
            productPane.getChildren().add(productBox);

            // Add to total price
            totalPrice += price;
        }

        // Set total price
        totalPriceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));
    }

    public void backToPreviousScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene14.fxml"));
            Parent root = loader.load();

            Scene14Control scene14Controller = loader.getController();
            scene14Controller.setCustomer(customer);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to load the previous scene. Please try again.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    public void changeToScene8(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene8.fxml"));
            Parent root = loader.load();

            // Pass the customer object
            Scene8Control scene8Controller = loader.getController();
            scene8Controller.setCustomer(customer);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}