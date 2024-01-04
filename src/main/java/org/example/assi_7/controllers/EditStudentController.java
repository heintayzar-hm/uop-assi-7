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

public class EditStudentController {
    public EditStudentController(){

    }
    @FXML
    private Button updateStudentButton;
    @FXML
    private Label studentNameLabel;

    @FXML
    private TextField studentNameTextField;

    private String studentCode;

    @FXML
    private Label studentNameErrorLabel;


    public void setStudentName(String studentName) {
        studentNameTextField.setText(studentName);
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }



    public void updateStudent(javafx.event.ActionEvent actionEvent) {

        if (!Validator.forString(studentNameTextField.getText()).hasLengthBetween(2,10).isValid()) {
            // make it red
            studentNameErrorLabel.setStyle("-fx-text-fill: red");
            studentNameTextField.setStyle("-fx-border-color: red");
            studentNameErrorLabel.setText("Student name must be between 2 and 10 characters");
            return;
        }

        String studentName = studentNameTextField.getText();

        // find student by studentCode
        Student student = DataStorage.getInstance().getStudentByCode(studentCode);

        // update student name
        student.setName(studentName);

        // update student in DataStorage
        DataStorage.getInstance().updateStudent(student);

        // close window
        closeWindow();
    }

    private void closeWindow() {
        // Get the current stage (window) and close it
        Stage stage = (Stage) studentNameTextField.getScene().getWindow();
        stage.close();
    }



}