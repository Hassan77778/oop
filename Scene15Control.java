package com.example.project123;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Scene15Control {

    @FXML
    private AnchorPane root;
    @FXML
    private ComboBox<Product> productComboBox;
    @FXML
    private TextArea productDetailsTextArea;
    @FXML
    private Spinner<Integer> stockSpinner;
    @FXML
    private TextField priceTextField;
    @FXML
    private Button removeButton;
    @FXML
    private Button changeStockButton;
    @FXML
    private Button changePriceButton;
    @FXML
    private Pane imagePane; // Pane to display the image

    private ObservableList<Product> productObservableList;

    @FXML
    public void initialize() {
        productObservableList = FXCollections.observableArrayList(Database.products);
        productComboBox.setItems(productObservableList);

        // Display only the product names in the ComboBox
        productComboBox.setCellFactory(param -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName()); // Display the product name
                }
            }
        });

        productComboBox.setOnAction(event -> showProductDetails(productComboBox.getValue()));

        stockSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0));

        removeButton.setOnAction(event -> removeSelectedProduct());
        changeStockButton.setOnAction(event -> changeStock());
        changePriceButton.setOnAction(event -> changePrice());
    }
public Admin admin;
    private void showProductDetails(Product product) {
        if (product != null) {
            // Show the product details in the TextArea
            StringBuilder details = new StringBuilder();
            details.append("Name: ").append(product.getName()).append("\n");
            details.append("Price: ").append(product.getPrice()).append("\n");
            details.append("Stock: ").append(product.getStock()).append("\n");

            productDetailsTextArea.setText(details.toString());

            // Set the stock in the spinner
            stockSpinner.getValueFactory().setValue(product.getStock());

            // Display the image if available
            if (product.getImagePath() != null && !product.getImagePath().isEmpty()) {
                File imageFile = new File(product.getImagePath());
                if (imageFile.exists()) {
                    ImageView imageView = new ImageView(new Image(imageFile.toURI().toString()));
                    imageView.setFitWidth(productDetailsTextArea.getWidth()); // Set image width to match the TextArea width
                    imageView.setPreserveRatio(true);

                    // Add the image to the imagePane
                    imagePane.getChildren().clear(); // Clear any previous images
                    imagePane.getChildren().add(imageView); // Add the new image
                } else {
                    System.out.println("Image file does not exist: " + product.getImagePath());
                    imagePane.getChildren().clear(); // Clear the imagePane if the image is not found
                }
            } else {
                imagePane.getChildren().clear(); // Clear the imagePane if no image path is provided
            }
        } else {
            productDetailsTextArea.clear();
            imagePane.getChildren().clear();
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
            scene5Controller.setAdmin(admin);

            // Switch to the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        if (admin != null) {
            System.out.println("Admin Name: " + admin.getUsername());
        } else {
            System.out.println("Admin is null.");
        }
    }
    private void removeSelectedProduct() {
        Product selectedProduct = productComboBox.getValue();
        if (selectedProduct != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Remove Product");
            confirmationAlert.setHeaderText("Are you sure you want to remove this product?");
            confirmationAlert.setContentText(selectedProduct.getName());

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Database.products.remove(selectedProduct);
                productObservableList.remove(selectedProduct);
                productComboBox.getSelectionModel().clearSelection();
                productDetailsTextArea.clear();
                stockSpinner.getValueFactory().setValue(0);
                imagePane.getChildren().clear(); // Clear the imagePane
            }
        }
    }

    private void changeStock() {
        Product selectedProduct = productComboBox.getValue();
        if (selectedProduct != null) {
            int newStock = stockSpinner.getValue();
            selectedProduct.setStock(newStock);
            showProductDetails(selectedProduct); // Refresh the product details
        }
    }

    private void changePrice() {
        Product selectedProduct = productComboBox.getValue();
        if (selectedProduct != null) {
            try {
                double newPrice = Double.parseDouble(priceTextField.getText());
                selectedProduct.setPrice(newPrice);
                showProductDetails(selectedProduct); // Refresh the product details
                priceTextField.clear();
            } catch (NumberFormatException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Invalid Price");
                errorAlert.setHeaderText("Please enter a valid price.");
                errorAlert.showAndWait();
            }
        }
    }
}