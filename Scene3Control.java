package com.example.project123;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.time.Period;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Scene3Control implements Initializable {
    @FXML
    public RadioButton male;
    @FXML
    public RadioButton female;
    @FXML
    private TextField Address;

    @FXML
    private Spinner<Double> Balance;

    @FXML
    private DatePicker Date_of_birth;

    @FXML
    private ToggleGroup Gender;
    @FXML
    private ToggleButton peekButton;

    @FXML
    private TextField peekfield;
    @FXML
    private ToggleButton peekButton2;

    @FXML
    private TextField peekfield2;
    @FXML
    private ChoiceBox<String> Interest;

    @FXML
    private TextField Name;

    @FXML
    private PasswordField Password;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private PasswordField ConfirmPassword;
    SpinnerValueFactory<Double> svf =new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,5000.0,500.0,50.0);
    @FXML
    Customer c;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate ChoiceBox with category names from Database
        Interest.getItems().addAll(Database.categories.stream()
                .map(Category::getName) // Extract category names
                .toList()
        );

        Balance.setValueFactory(svf);
        male.setSelected(true);
        peekfield.textProperty().bindBidirectional(Password.textProperty());

        // Add listener to ToggleButton
        peekButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Show the plain text field
                peekfield.setVisible(true);
                Password.setVisible(false);
            } else {
                // Show the password field
                peekfield.setVisible(false);
                Password.setVisible(true);
            }
        });
        peekfield2.textProperty().bindBidirectional(ConfirmPassword.textProperty());

        // Add listener to ToggleButton
        peekButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Show the plain text field
                peekfield2.setVisible(true);
                ConfirmPassword.setVisible(false);
            } else {
                // Show the password field
                peekfield2.setVisible(false);
                ConfirmPassword.setVisible(true);
            }
        });
    }


    public void showTimedPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> alert.close());
        delay.play();
    }



    // Method to calculate age
    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
    String name;
    String password;
    @FXML
    public void submit(ActionEvent event) throws IOException {
        name = Name.getText().trim();
        password = Password.getText().trim();
        String confirmpassword = ConfirmPassword.getText().trim();
        String address = Address.getText().trim();
        String phonenumber = PhoneNumber.getText().trim();
        double balance = Balance.getValue();
        String interest = Interest.getSelectionModel().getSelectedItem();
        boolean x= male.isSelected();

        gender g;
        if(x){
            g= gender.male;
        }
        else{
            g=gender.female;
        }
        LocalDate dateOfBirth = Date_of_birth.getValue();
        if (name.isEmpty() || password.isEmpty() || dateOfBirth==null || confirmpassword.isEmpty() || address.isEmpty() || phonenumber.isEmpty() || interest.isEmpty()  ) {
            showTimedPopup("No fields should be empty");
            return;
        }

        if(password.length()<8){
            showTimedPopup("Password should be at least 8 characters");
            return;
        }
        if(!(password.equals(confirmpassword)) ){
            showTimedPopup("Passwords don't match");
            return;
        }
        Customer ctest = new Customer().Login(name, password);
        if (ctest != null) {
            showTimedPopup("Name already in database , Sign up using another username or Log in");
            return;
        }
        boolean isValid = phonenumber.matches("\\d+"); // Checks if it contains only digits
        if (!isValid){
            showTimedPopup("Phone Number should only contain numbers");
            return;
        }
        if(phonenumber.length() !=11){
            showTimedPopup("Phone Number should have 11 numbers");
            return;
        }
        if(balance <0){
            showTimedPopup("Balance is invalid");
            return;
        }
        LocalDate dateofbirth = Date_of_birth.getValue();


        // Calculate age using Period.between()
        int age = calculateAge(dateofbirth, LocalDate.now());


        if (age < 12) {
            showTimedPopup("Your age is invalid ");
            return;
        }
         c= new Customer(name,password,dateofbirth.toString(),balance,g,interest,phonenumber,address);
        showTimedPopup("Sign Up successfully");
        changeToScene14(event);
    }
    public void changeToScene14(ActionEvent event) throws IOException {
        try {
            // Load the FXML file for Scene 4
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project123/Scene14.fxml"));
            Parent root = loader.load();

            // Get the Scene4 controller
            Scene14Control scene14Controller = loader.getController();

            // Pass the username and password to Scene4 controller

            scene14Controller.setCustomer(c);

            // Switch to the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void changeToScene1(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project123/Scene1.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("here");
        }
    }


}