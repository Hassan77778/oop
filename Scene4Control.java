
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
    private VBox categoryVBox; 
    public void initialize() {
        refreshCategories();
    }

    private void refreshCategories() {
        categoryVBox.getChildren().clear(); 

        for (Category category : Database.categories) {
    
            VBox categoryBox = new VBox(10);
            categoryBox.setAlignment(Pos.TOP_LEFT);
            categoryBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: #e8e8e8;");

            Label categoryLabel = new Label("Category: " + category.getName());
            categoryLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            categoryBox.getChildren().add(categoryLabel);

            GridPane productGrid = new GridPane();
            productGrid.setHgap(20);
            productGrid.setVgap(20);

            int col = 0, row = 0;

            for (Product product : category.getProducts()) {
                VBox productBox = new VBox(5);
                productBox.setAlignment(Pos.CENTER);
                productBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: #f9f9f9;");

                // Check if the product has an image
                if (product.getImagePath() != null && !product.getImagePath().isEmpty()) {
                    try {
             
                        ImageView imageView = new ImageView(new Image("file:" + product.getImagePath()));
                        imageView.setFitWidth(150);
                        imageView.setFitHeight(150);
                        productBox.getChildren().add(imageView);
                    } catch (Exception e) {
                        System.out.println("Error loading image: " + product.getImagePath() + ", " + e.getMessage());
                    }
                }

             ]
                Label nameLabel = new Label(product.getName());
                nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                productBox.getChildren().add(nameLabel);

                Label priceLabel = new Label("Price: $" + product.getPrice());
                priceLabel.setStyle("-fx-font-size: 14px;");
                productBox.getChildren().add(priceLabel);

                Label stockLabel = new Label("Stock: " + product.getStock());
                stockLabel.setStyle("-fx-font-size: 14px;");
                productBox.getChildren().add(stockLabel);

      
                productGrid.add(productBox, col, row);

        
                col++;
                if (col == 3) { // 3 products per row
                    col = 0;
                    row++;
                }
            }

    ]
            categoryBox.getChildren().add(productGrid);
]
            categoryVBox.getChildren().add(categoryBox);
        }
]
        productScrollPane.setContent(categoryVBox);
    }

    public void refreshScene() {
        refreshCategories(); 
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
]
            Scene5Control scene5Controller = loader.getController();

 ]
            scene5Controller.setAdmin(atest);

   
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
