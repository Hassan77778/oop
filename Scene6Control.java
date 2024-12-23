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
    private VBox productVBox; 
    @FXML
    private GridPane productGrid; 
    @FXML
    private Label sceneTitle; 

    private Category category; 
    private Customer customer; 
    private boolean isInitialized = false; 
    private boolean pendingCategory = false; 

    // Method to set the customer
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCat(String catee) {
        if (catee == null || catee.isEmpty()) {
            System.out.println("Category name is null or empty.");
            return;
        }

        for (Category category : Database.categories) {
            if (category.getName().equalsIgnoreCase(catee)) {
                this.category = category;
                break;
            }
        }

        if (this.category != null) {
            System.out.println("Selected category is: " + category.getName());

     
            if (isInitialized) {
                updateSceneTitle();
                DisplayRecommended();
            } else {
                pendingCategory = true; 
            }
        } else {
            System.out.println("Category not found in the database.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isInitialized = true; // Mark FXML initialization complete
        System.out.println("Scene6 initialized. Waiting for category to be set.");


        if (pendingCategory) {
            updateSceneTitle();
            DisplayRecommended();
            pendingCategory = false; // Reset the flag
        }
    }

    private void updateSceneTitle() {
        if (sceneTitle != null && category != null) {
            sceneTitle.setText(category.getName() + ": Products");
        }
    }

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene14.fxml"));
            Parent root = loader.load();

        
            Scene14Control scene14Controller = loader.getController();

         
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
