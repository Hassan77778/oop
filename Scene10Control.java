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
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class Scene10Control implements Initializable {
    @FXML
    private ChoiceBox<String> Admins;

    @FXML
    private Button aboutUs;

    @FXML
    private Button back;

    @FXML
    private DatePicker date;

    @FXML
    private Spinner<Integer> hours;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password2;

    @FXML
    private Button remove;

    @FXML
    private ChoiceBox<String> roles;

    @FXML
    private Button submit;
    private Admin atest;
    @FXML
    private TextField username;
    SpinnerValueFactory<Integer> svf =new SpinnerValueFactory.IntegerSpinnerValueFactory(1,48,8,1);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Admins.getItems().addAll(Database.admins.stream().map(Admin::getUsername).toList());
        roles.getItems().addAll("Boss","junior","developer");
        hours.setValueFactory(svf);
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
    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
    public void submit(ActionEvent event) throws IOException {
        String Name= username.getText().trim();
        String Password= password.getText().trim();
        String ConfirmPassword = password.getText().trim();
        Integer workinghours = hours.getValue();
        String Role= roles.getSelectionModel().getSelectedItem();
        LocalDate dateOfBirth = date.getValue();
        if(Name.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty() || Role.isEmpty() || dateOfBirth==null){
            showTimedPopup("No fields should be empty");
            return;
        }
        if(Password.length()<8){
            showTimedPopup("Password should be at least 8 characters");
            return;
        }
        if(!(Password.equals(ConfirmPassword)) ){
            showTimedPopup("Passwords don't match");
            return;
        }
        Admin atest = new Admin().Login(Name,Password);
        if(atest != null){
            showTimedPopup("Name already in database , Sign up using another username ");
            return;
        }
        int age = calculateAge(dateOfBirth, LocalDate.now());
        if (age < 18) {
            showTimedPopup("Your age is invalid ");
            return;
        }
        Admin a= new Admin(Name,Password,dateOfBirth.toString(),Role,workinghours);
        showTimedPopup("New Admin Added Successfully!");
        Admins.getItems().add(Name);


    }
    public void remove(){
        String adminToBeRemoved = Admins.getSelectionModel().getSelectedItem();
        for (int i = 0; i < Database.admins.size(); i++) {
            if (adminToBeRemoved.equals(Database.admins.get(i).getUsername()) ) {
                Database.admins.remove(Database.admins.get(i));
                break;
            }
        }
        showTimedPopup("Admin removed successfully");
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
