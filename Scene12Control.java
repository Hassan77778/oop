package com.example.project123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class Scene12Control {

    @FXML
    private TextArea ordersTextArea;
    private Admin atest;
    @FXML
    public void initialize() {
        // Populate the TextArea when the application starts
        displayOrders();
    }

    private void displayOrders() {
        // Clear the TextArea
        ordersTextArea.clear();

        // Check if there are orders in the database
        if (Database.orders.isEmpty()) {
            ordersTextArea.setText("There are no orders available.\n");
        } else {
            // Iterate through the orders and append details to the TextArea
            StringBuilder orderDetails = new StringBuilder();
            for (Order order : Database.orders) {
                double total = 0;
                for (Map.Entry<Product, Integer> entry : order.getViewcart().entrySet()) {
                    total += entry.getKey().getPrice() * entry.getValue();
                }
                order.totalprice = total;
                int i=1;

                // Append order details
                StringBuilder append = orderDetails.append("=================================\n")

                        .append("Order" +"[" +i+"]" + ":" + "\n")
                        .append("Customer Name   : ").append(order.customername).append("\n")
                        .append("Phone Number    : ").append(order.phoneno).append("\n")
                        .append("Address         : ").append(order.adress).append("\n")
                        .append("Order Date      : ").append(order.orderDate).append("\n")
                        .append("Total Price     : $").append(order.totalprice).append("\n")
                        .append("Order Details   :\n");
                // Append products and their quantities
                for (Map.Entry<Product, Integer> entry : order.getViewcart().entrySet()) {
                    orderDetails.append("- Product: ").append(entry.getKey().getName())
                            .append(", Quantity: ").append(entry.getValue()).append("\n");
                }

                orderDetails.append("---------------------------------\n");
                i++;
            }

            // Set the details to the TextArea
            ordersTextArea.setText(orderDetails.toString());
        }
    }

    public void LogoutHandle(ActionEvent event) throws IOException {//change to scene9 (about us)
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAdmin(Admin admin) {
        atest = admin;
        if (admin != null) {
            System.out.println("Admin Name: " + admin.getUsername());
        } else {
            System.out.println("Admin is null.");
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