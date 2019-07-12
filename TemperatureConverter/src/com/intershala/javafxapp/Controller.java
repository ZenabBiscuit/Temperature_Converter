package com.intershala.javafxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ChoiceBox;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Label welcomeLabel;

    @FXML
    public ChoiceBox<String> choiceBox;

    @FXML
    public TextField userInputField;

    @FXML
    public Button convertButton;

    private static final String C_TO_F = "Celsius to Fahrenheit";
    private static final String F_TO_C = "Fahrenheit to Celsius";

    private boolean isC_TO_F = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceBox.getItems().add(C_TO_F);
        choiceBox.getItems().add(F_TO_C);
        choiceBox.setValue(C_TO_F);

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == C_TO_F){
                isC_TO_F = true;
            }else{
                isC_TO_F = false;
            }

        });

        convertButton.setOnAction(event -> {
            convert();
        });

    }

    private void convert() {
        String input = userInputField.getText();
        float enteredTemperature = 0.0f;

        try {
            enteredTemperature = Float.parseFloat(input);
        }catch (Exception ex){
            warnUser();
            return;
        }

        float newTemperature = 0.0f;
        if(isC_TO_F){
            newTemperature = (enteredTemperature * 9/5) + 32;
        }else{
            newTemperature = (enteredTemperature - 32) * 5/9;
        }
        display(newTemperature);
    }

    private void warnUser() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occured!");
        alert.setHeaderText("Invalid Temperature Entered.");
        alert.setContentText("Please enter a valid temperature.");
        alert.show();
    }

    private void display(float newTemperature) {
        String unit = isC_TO_F ? " F" : " C";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("New Temperature: " + newTemperature + unit);
        alert.show();
    }
}
