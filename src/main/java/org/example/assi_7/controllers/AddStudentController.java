package org.example.assi_7.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.assi_7.helpers.DataStorage;
import org.example.assi_7.helpers.Validator;
import org.example.assi_7.model.Student;

import java.util.Random;

public class AddStudentController {
    public AddStudentController(){

    }
    @FXML
    private Button addStudentButton;
    @FXML
    private Label studentNameLabel;

    @FXML
    private TextField studentNameTextField;

    @FXML
    private Label studentNameErrorLabel;



    public void addStudent(javafx.event.ActionEvent actionEvent) {

        if (!Validator.forString(studentNameTextField.getText()).hasLengthBetween(2,10).isValid()) {
            // make it red
            studentNameErrorLabel.setStyle("-fx-text-fill: red");
            studentNameTextField.setStyle("-fx-border-color: red");
            studentNameErrorLabel.setText("Student name must be between 2 and 10 characters");
            return;
        }

        String studentName = studentNameTextField.getText();

        // random student ID for now
        String randomStudentID = generateRandomID(9, "0123456789");

        Student student = new Student(studentName, randomStudentID);

        DataStorage.getInstance().addStudent(student);

        // close window
        closeWindow();
    }

    private String generateRandomID(int length, String characters) {
        Random random = new Random();
        StringBuilder randomID = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomID.append(characters.charAt(index));
        }

        return randomID.toString();
    }

    private void closeWindow() {
        // Get the current stage (window) and close it
        Stage stage = (Stage) studentNameTextField.getScene().getWindow();
        stage.close();
    }



}