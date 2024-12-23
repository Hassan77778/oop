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
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Scene8Control implements Initializable {

    private Customer customer;
    public Order order=new Order();
    @FXML
    private VBox productPane; // VBox for dynamically displaying products

    @FXML
    private Label totalPriceLabel; // Label to display total price

    @FXML
    private Label customerNameLabel; // Label to display customer name

    @FXML
    private Label customerNumberLabel; // Label to display customer number

    @FXML
    private Label customerAddressLabel; // Label to display customer address
    private double totalPrice = 0.0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic if needed
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
//this.customer.o.setCustomername(customer.getUsername());
//this.customer.o.adress=customer.getAddress();
//this.customer.o.phoneno=customer.getPhonenumber();
        if (productPane == null || totalPriceLabel == null || customerNameLabel == null || customerNumberLabel == null || customerAddressLabel == null) {
            throw new IllegalStateException("FXML components are not initialized. Check FXML file.");
        }

        // Set customer details
        customerNameLabel.setText(customer.getUsername());
        customerNumberLabel.setText(customer.getPhonenumber());
        customerAddressLabel.setText(customer.getAddress());

        // Display products in the cart
        productPane.getChildren().clear(); // Clear any existing content

        for (Map.Entry<Product, Integer> entry : customer.getcartt().getCartMap().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double price = product.getPrice() * quantity;

            HBox productBox = new HBox(20); // 20px spacing between items

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
    public  void balance(ActionEvent event) {
        try {
            if (customer.getBalance()< totalPrice) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cart is Empty");
                alert.setHeaderText(null);
                alert.setContentText("insufficent balance. ");
                alert.showAndWait();
                return; // Exit the method without transitioning to Scene8
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cart is Empty");
                alert.setHeaderText(null);
                alert.setContentText("order placed ,tahanks for shopping at wael store. ");
                alert.showAndWait();
                this.customer.setBalance(customer.getBalance()-totalPrice);
                updateStock();
                order.corder(customer);
                this.customer.setorder(order);
                this.customer.getcartt().getCartMap().clear();
                Database.orders.add(order);
                return;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    void addproducts(ActionEvent event){


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene14.fxml"));
            Parent root = loader.load();
            Scene7Control scene7Controller = loader.getController();
            scene7Controller.setCustomer(customer);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to load the previous scene. Please try again.");
            alert.showAndWait();
            e.printStackTrace();
        }


    }
    public void updateStock() {
        for (Map.Entry<Product, Integer> entry : customer.getcartt().getCartMap().entrySet()) {
            int item = entry.getKey().getStock();//stock of product
            int quantity = entry.getValue();
            entry.getKey().setStock(item - quantity);
        }
    }

}