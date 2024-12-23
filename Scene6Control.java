package com.example.project123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene6Control implements Initializable {
    @FXML
    private VBox productVBox; // The VBox where we will display products
    @FXML
    private GridPane productGrid; // For displaying products in a grid layout
    @FXML
    private Label sceneTitle; // Label to display the scene title (e.g., category name)

    private Category category; // Store the selected category
    private Customer customer; // Store the customer object
    private boolean isInitialized = false; // Flag to indicate FXML initialization
    private boolean pendingCategory = false; // Flag for delayed category display

    // Method to set the customer
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Method to set the category before initialize is called
    public void setCat(String catee) {
        if (catee == null || catee.isEmpty()) {
            System.out.println("Category name is null or empty.");
            return;
        }

        // Find the category in the database
        for (Category category : Database.categories) {
            if (category.getName().equalsIgnoreCase(catee)) {
                this.category = category;
                break;
            }
        }

        if (this.category != null) {
            System.out.println("Selected category is: " + category.getName());

            // Update the title if FXML is initialized
            if (isInitialized) {
                updateSceneTitle();
                DisplayRecommended();
            } else {
                pendingCategory = true; // Defer updates until initialization
            }
        } else {
            System.out.println("Category not found in the database.");
        }
    }

    // The initialize method from Initializable interface
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isInitialized = true; // Mark FXML initialization complete
        System.out.println("Scene6 initialized. Waiting for category to be set.");

        // If a category was set before initialization, update the title and display products
        if (pendingCategory) {
            updateSceneTitle();
            DisplayRecommended();
            pendingCategory = false; // Reset the flag
        }
    }

    // Update the scene title based on the selected category
    private void updateSceneTitle() {
        if (sceneTitle != null && category != null) {
            sceneTitle.setText(category.getName() + ": Products");
        }
    }

    // Method to display products based on the selected category
    private void DisplayRecommended() {
        if (category == null || productGrid == null) {
            System.out.println("Category or product grid is null.");
            return; // No category or GridPane available
        }

        productGrid.getChildren().clear(); // Clear previous products

        int col = 0, row = 0;
        for (Product product : category.getProducts()) {
            VBox productBox = new VBox(5);
            productBox.setAlignment(Pos.CENTER);
            productBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: #f9f9f9;");

            if (product.getImagePath() != null && !product.getImagePath().isEmpty()) {
                ImageView imageView = new ImageView(new Image("file:" + product.getImagePath()));
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                productBox.getChildren().add(imageView);
            }

            Label nameLabel = new Label(product.getName());
            nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            productBox.getChildren().add(nameLabel);

            Label priceLabel = new Label("Price: $" + product.getPrice());
            productBox.getChildren().add(priceLabel);

            Label stockLabel = new Label("Stock: " + product.getStock());
            productBox.getChildren().add(stockLabel);

            productGrid.add(productBox, col, row);
            col++;
            if (col == 3) { // 3 products per row
                col = 0;
                row++;
            }
        }
    }

    @FXML
    public void changeToScene1(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project123/Scene1.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backToPreviousScene(ActionEvent event) {
        try {
            // Load the FXML file for Scene 14
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene14.fxml"));
            Parent root = loader.load();

            // Get the Scene14 controller
            Scene14Control scene14Controller = loader.getController();

            // Pass the customer to Scene14 controller
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
}