package org.example.assi_7.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.assi_7.helpers.DataStorage;
import org.example.assi_7.model.Course;
import org.example.assi_7.model.Student;

import java.util.List;

public class EnrollStudentController implements Initializable {

    @FXML
    private Label studentNameLabel;

    @FXML
    private ComboBox<String> dropDownCourses;

    @FXML
    private Label errorLabel;

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
    }

    public ObservableList<String> getCourses() {
        ObservableList<String> courses = FXCollections.observableArrayList();
        // get courses from DataStorage
        List<Course> courseList = DataStorage.getInstance().getCourses();
        for (Course course : courseList) {
            courses.add(course.getCourseName());
        }
        return courses;
    }
    public ObservableList<String> getCourses(List<Course> userCourseList) {
        ObservableList<String> courses = FXCollections.observableArrayList();
        // get courses from DataStorage
        List<Course> courseList = DataStorage.getInstance().getCourses();
        for (Course course : courseList) {
            // if the course is not in the user's course list
            if (!userCourseList.contains(course)){
                courses.add(course.getCourseName());
            }
        }
        return courses;
    }

    public void setStudentName(String studentName) {
        studentNameLabel.setText(studentName);
    }

    private String studentCode;
    public void setStudentCode(String studentCode) {

        this.studentCode = studentCode;
        // clear
        dropDownCourses.getItems().clear();
        dropDownCourses.getItems().addAll(getCourses(DataStorage.getInstance().getStudentByCode(studentCode).getEnrolledCourses()));
    }

    public void enroll(javafx.event.ActionEvent actionEvent) {
      // enroll into course
        if (dropDownCourses.getValue() == null) {
            // make it red
            errorLabel.setStyle("-fx-text-fill: red");
            dropDownCourses.setStyle("-fx-border-color: red");
            errorLabel.setText("There are no courses to enroll in");
            return;
        }
        // find student by studentCode
        Student student = DataStorage.getInstance().getStudentByCode(studentCode);

        // update student course
        // find course by course name
        Course course = DataStorage.getInstance().getCourseByName(dropDownCourses.getValue());
        student.enrollInCourse(course);

        // update student in DataStorage
        DataStorage.getInstance().updateStudent(student);

        // close window
        closeWindow();
    }

    private void closeWindow() {
        // Get the current stage (window) and close it
        Stage stage = (Stage) studentNameLabel.getScene().getWindow();
        stage.close();
    }
}