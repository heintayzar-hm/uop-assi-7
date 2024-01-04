package org.example.assi_7.controllers;


import com.almasb.fxgl.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.assi_7.helpers.DataStorage;
import org.example.assi_7.model.Course;
import org.example.assi_7.model.Student;

import java.io.IOException;

public class ViewStudentController {

    public ViewStudentController() {

    }

    @FXML
    private Label studentNameLabel;

    @FXML
    private VBox showEnrolledCourses;


    public void setStudentName(String studentName) {
        studentNameLabel.setText(studentName);
    }

    private String studentCode;
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
        loadEnrolledCourses();
    }

    public void enrollStudent(javafx.event.ActionEvent actionEvent) {
        String path = "/org/example/assi_7/view/components/EnrollStudent.fxml";

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            // set student name
            EnrollStudentController enrollStudentController = loader.getController();
            enrollStudentController.setStudentName(studentNameLabel.getText());
            enrollStudentController.setStudentCode(studentCode);
            // new stage
            Stage stage = new Stage();
            stage.setTitle("Enroll Student");
            stage.setResizable(false);
            stage.setScene(new javafx.scene.Scene(root, 600, 400));

            // set the owner of the new stage
            stage.initOwner(studentNameLabel.getScene().getWindow());

            // show the new stage
            stage.showAndWait();

            // refresh the table view
            loadEnrolledCourses();
        } catch (IOException e) {
            Logger.get(AppController.class).fatal("Error loading " + path, e);
        }
    }

    public void assignGrade(javafx.event.ActionEvent actionEvent) {
        String path = "/org/example/assi_7/view/components/AssignGrade.fxml";

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            // set student name
            AssignGradeController assignGradeController = loader.getController();
            assignGradeController.setStudentName(studentNameLabel.getText());
            assignGradeController.setStudentCode(studentCode);
            // new stage
            Stage stage = new Stage();
            stage.setTitle("Assign Grade");
            stage.setResizable(false);
            stage.setScene(new javafx.scene.Scene(root, 600, 400));

            // set the owner of the new stage
            stage.initOwner(studentNameLabel.getScene().getWindow());

            // show the new stage
            stage.showAndWait();
            // refresh the table view
            loadEnrolledCourses();
        } catch (IOException e) {
            Logger.get(AppController.class).fatal("Error loading " + path, e);
        }
    }


    public void loadEnrolledCourses() {
        if (studentCode != null) {
            // clear the VBox
            showEnrolledCourses.getChildren().clear();
            System.out.println("Loading enrolled courses for student with code " + studentCode);

            if (DataStorage.getInstance().getStudentByCode(studentCode) == null) {
                System.out.println("Student with code " + studentCode + " not found");
                Logger.get(AppController.class).fatal("Student with code " + studentCode + " not found");
                return;
            }

            if (DataStorage.getInstance().getStudentByCode(studentCode).getEnrolledCourses() == null) {
                System.out.println("Student with code " + studentCode + " has no enrolled courses");
                Logger.get(AppController.class).fatal("Student with code " + studentCode + " has no enrolled courses");
                return;
            }
            int count = 1; // Initialize a counter for numbering the courses

            for (Course course : DataStorage.getInstance().getStudentByCode(studentCode).getEnrolledCourses()) {
                // student
                Student student = DataStorage.getInstance().getStudentByCode(studentCode);
                boolean isAssigned = student.isGradeAssigned(course);
                Label courseNameLabel = new Label(count++ + ". " + course.getCourseName() + " (" + course.getCourseCode() + ") " + (isAssigned ? " - Grade: " + student.getCourseGrade(course) : ""));
                courseNameLabel.setWrapText(true);
                courseNameLabel.setStyle("-fx-font-size: 14px;");
                showEnrolledCourses.getChildren().add(courseNameLabel);
            }
        }
    }
}