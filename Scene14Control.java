package com.example.project123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Scene14Control implements Initializable {
    @FXML
    private ChoiceBox<String> Categ;

    @FXML
    private Button select;

    @FXML
    private Button Logout;

    @FXML
    private Button aboutUs;

    @FXML
    private ListView<String> listView;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Button search;

    @FXML
    private TextField searchbar;

    @FXML
    private VBox vboxsearch;

    @FXML
    private VBox CategoryVbox;

    private Customer customer; // Stores the logged-in customer
    private ArrayList<String> searchdata = new ArrayList<>(); // Stores searchable product names

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateSearchData();

        // Populate categories in the ChoiceBox
        Categ.getItems().addAll(Database.categories.stream()
                .map(Category::getName) // Extract category names
                .toList()
        );

        // Populate listView with all products initially
        listView.getItems().addAll(searchdata);

        // Display recommendations if a category is found for the customer
        if (customer != null) {
            Category cat = findCategory();
            DisplayRecommended(cat);
        }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;

        if (customer != null) {
            System.out.println("Customer interest: " + customer.getInterest());
            Category category = findCategory();
            DisplayRecommended(category); // Display recommendations
        } else {
            System.out.println("Customer is null. Cannot display recommendations.");
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setSearchdata(ArrayList<String> searchdata) {
        this.searchdata = searchdata;
    }

    private void populateSearchData() {
        searchdata.clear(); // Clear to avoid duplicates

        // Add product names from Database.products
        for (Product p : Database.products) {
            searchdata.add(p.getName());
        }
    }

    @FXML
    public void search1(ActionEvent actionEvent) {
        listView.getItems().clear(); // Clear existing items

        // Add filtered items based on search
        listView.getItems().addAll(searchlist(searchbar.getText(), searchdata));
    }

    private List<String> searchlist(String searchWords, List<String> listOfStrings) {
        List<String> searchWordsArray = List.of(searchWords.trim().split(" ")); // Split by space for multi-word search

        return listOfStrings.stream()
                .filter(input -> searchWordsArray.stream()
                        .allMatch(searchTerm -> input.toLowerCase().contains(searchTerm.toLowerCase()))
                )
                .collect(Collectors.toList());
    }

    @FXML
    public void changeToScene13(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene13.fxml"));
            Parent root = loader.load();

            // Pass the customer object
            Scene13Control scene13Controller = loader.getController();
            scene13Controller.setCustomer(customer);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void changeToScene9(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene9.fxml"));
            Parent root = loader.load();

            // Pass the customer object
            Scene9Control scene9Controller = loader.getController();
            scene9Controller.setCustomer(customer);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeToScene1(@NotNull ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project123/Scene1.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Category findCategory() {
        if (customer == null || customer.getInterest() == null) {
            System.out.println("Customer or interest is null.");
            return null;
        }

        for (Category category : Database.categories) {
            if (category.getName().equalsIgnoreCase(customer.getInterest())) {
                return category; // Return the matched category
            }
        }

        System.out.println("No matching category found for interest: " + customer.getInterest());
        return null;
    }

    private void DisplayRecommended(Category cat) {
        if (cat == null) {
            System.out.println("No category to display.");
            return;
        }

        VBox categorybox = new VBox(10);
        categorybox.setAlignment(Pos.TOP_LEFT);
        categorybox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: #e8e8e8;");

        Label categoryLabel = new Label("Interest: " + cat.getName());
        categoryLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        categorybox.getChildren().add(categoryLabel);

        GridPane productGrid = new GridPane();
        productGrid.setHgap(20);
        productGrid.setVgap(20);

        int col = 0, row = 0;
        for (Product product : cat.getProducts()) {
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

        categorybox.getChildren().add(productGrid);
        CategoryVbox.getChildren().add(categorybox);
        scrollpane.setContent(CategoryVbox);
    }

    private Category findProductByName(String name) {
        for(Category cat:Database.categories) {
            for (Product product : cat.getProducts()) {
                if (product.getName().equalsIgnoreCase(name)) {
                    return cat;
                }
            }
        }
        return null;
    }

    public void handleItemSelected(javafx.scene.input.MouseEvent mouseEvent) {
        // Get the selected item from the ListView
        String selectedItem = listView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            System.out.println("Selected item: " + selectedItem);
            Category selectedCategory = findProductByName(selectedItem);
            if (selectedCategory != null) {
                try {
                    // Load Scene6.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene6.fxml"));
                    Parent root = loader.load();

                    // Get the controller instance for Scene6
                    Scene6Control scene6Controller = loader.getController();
                    // Set the category before initialize is called
                    scene6Controller.setCat(selectedCategory.getName());

                    // Set the customer object
                    scene6Controller.setCustomer(this.customer);

                    // Switch to the new scene
                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void select(ActionEvent event){
        System.out.println("selected category is "+Categ.getSelectionModel().getSelectedItem());
        changeToScene6(event);

    }

    public void changeToScene6(ActionEvent event) {
        try {
            // Load Scene6.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene6.fxml"));
            Parent root = loader.load();

            // Get the controller instance for Scene6
            Scene6Control scene6Controller = loader.getController();
            // Set the category before initialize is called
            String selectedCategory = this.Categ.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                scene6Controller.setCat(selectedCategory);
            } else {
                System.out.println("No category selected!");
                return;
            }

            // Set the customer object
            scene6Controller.setCustomer(this.customer);

            // Switch to the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
