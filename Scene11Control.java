package com.example.project123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Scene11Control {

    @FXML
    private TextField categoryNameField;
    private Admin atest;
    @FXML
    private TextField categoryIdField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productPriceField;

    @FXML
    private TextField productStockField;

    @FXML
    private TextField imagePathField; // TextField to display the image path

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ComboBox<String> categoryChoiceComboBox;

    @FXML
    private ListView<String> categoriesListView;

    private String selectedImagePath; // Store the image path as a String

    @FXML
    public void addProduct() {
        String productName = productNameField.getText();
        String priceText = productPriceField.getText();
        String stockText = productStockField.getText();
        String categoryName = categoryComboBox.getValue();

        if (productName.isEmpty() || priceText.isEmpty() || stockText.isEmpty() || categoryName == null || selectedImagePath == null) {
            showAlert("Error", "All fields must be filled, and an image must be selected to add a product.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            int stock = Integer.parseInt(stockText);

            for (Category category : Database.categories) {
                if (category.getName().equals(categoryName)) {
                    // Save the image path as a string directly
                    Product product = new Product(productName, price, stock, selectedImagePath);
                    category.getProducts().add(product);
                    showAlert("Success", "Product added successfully.");
                    return;
                }
            }

        } catch (NumberFormatException e) {
            showAlert("Error", "Price and stock must be valid numbers.");
        }
    }

    @FXML
    public void initialize() {
        updateCategoryComboBox();
        updateCategoryChoiceComboBox(); // Populate the ComboBox with categories
    }

    @FXML
    public void addCategory() {
        String name = categoryNameField.getText();
        String id = categoryIdField.getText();

        if (name.isEmpty() || id.isEmpty()) {
            showAlert("Error", "Both fields are required for adding a category.");
            return;
        }

        for (Category category : Database.categories) {
            if (category.getName().equals(name)) {
                showAlert("Error", "Category already exists.");
                return;
            }
        }

        Category category = new Category(name, id);
        // Database.categories.add(category); // Uncomment this line to add category to the database
        updateCategoryComboBox();
        updateCategoryChoiceComboBox(); // Update the ComboBox after adding a new category
        showAlert("Success", "Category added successfully.");
    }

    @FXML
    public void uploadProductImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Product Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            selectedImagePath = file.getAbsolutePath(); // Store the image path as a string
            // Set the path of the selected image in the TextField
            imagePathField.setText(selectedImagePath);
        }
    }

    @FXML
    public void removeCategory() {
        String selectedCategoryName = categoryChoiceComboBox.getValue();

        if (selectedCategoryName == null) {
            showAlert("Error", "Please select a category to remove.");
            return;
        }

        // Search for the category and remove it
        boolean found = false;
        for (int i = 0; i < Database.categories.size(); i++) {
            if (selectedCategoryName.equals(Database.categories.get(i).getName())) {
                Database.categories.remove(Database.categories.get(i));
                updateCategoryChoiceComboBox();
                updateCategoryComboBox(); // Update the ComboBox after removal
                showAlert("Success", "Category removed successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            showAlert("Error", "Category not found.");
        }
    }

    private void updateCategoryComboBox() {
        categoryComboBox.getItems().clear();
        for (Category category : Database.categories) {
            categoryComboBox.getItems().add(category.getName());
        }
    }

    private void updateCategoryChoiceComboBox() {
        categoryChoiceComboBox.getItems().clear();
        for (Category category : Database.categories) {
            categoryChoiceComboBox.getItems().add(category.getName());
        }
    }

    public void LogoutHandle(ActionEvent event) throws IOException { //change to scene9 (about us)
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

    public void goBackToadminMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
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