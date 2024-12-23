
package com.example.project123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene4Control {
    private Admin atest;
    @FXML
    private ScrollPane productScrollPane;

    @FXML
    private VBox categoryVBox; // Use a VBox to hold categories and their products

    public void initialize() {
        refreshCategories();
    }

    private void refreshCategories() {
        categoryVBox.getChildren().clear(); // Clear previous content

        // Fetch all categories from the database
        for (Category category : Database.categories) {
            // Create a VBox for each category
            VBox categoryBox = new VBox(10);
            categoryBox.setAlignment(Pos.TOP_LEFT);
            categoryBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: #e8e8e8;");

            // Add category name
            Label categoryLabel = new Label("Category: " + category.getName());
            categoryLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            categoryBox.getChildren().add(categoryLabel);

            // Create a GridPane to display the products under this category
            GridPane productGrid = new GridPane();
            productGrid.setHgap(20);
            productGrid.setVgap(20);

            int col = 0, row = 0;
            // Fetch products of the category
            for (Product product : category.getProducts()) {
                VBox productBox = new VBox(5);
                productBox.setAlignment(Pos.CENTER);
                productBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: #f9f9f9;");

                // Check if the product has an image
                if (product.getImagePath() != null && !product.getImagePath().isEmpty()) {
                    try {
                        // Display image for the product
                        ImageView imageView = new ImageView(new Image("file:" + product.getImagePath()));
                        imageView.setFitWidth(150);
                        imageView.setFitHeight(150);
                        productBox.getChildren().add(imageView);
                    } catch (Exception e) {
                        System.out.println("Error loading image: " + product.getImagePath() + ", " + e.getMessage());
                    }
                }

                // Add product details
                Label nameLabel = new Label(product.getName());
                nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                productBox.getChildren().add(nameLabel);

                Label priceLabel = new Label("Price: $" + product.getPrice());
                priceLabel.setStyle("-fx-font-size: 14px;");
                productBox.getChildren().add(priceLabel);

                Label stockLabel = new Label("Stock: " + product.getStock());
                stockLabel.setStyle("-fx-font-size: 14px;");
                productBox.getChildren().add(stockLabel);

                // Add product box to the grid
                productGrid.add(productBox, col, row);

                // Adjust grid position
                col++;
                if (col == 3) { // 3 products per row
                    col = 0;
                    row++;
                }
            }

            // Add the product grid to the category box
            categoryBox.getChildren().add(productGrid);

            // Add the category box to the main VBox
            categoryVBox.getChildren().add(categoryBox);
        }

        // Set the VBox as the content of the ScrollPane
        productScrollPane.setContent(categoryVBox);
    }

    public void refreshScene() {
        refreshCategories(); // Call this to refresh the view when new products are added
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