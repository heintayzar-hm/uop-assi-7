package org.example.assi_7.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.assi_7.helpers.DataStorage;
import org.example.assi_7.helpers.Validator;
import org.example.assi_7.model.Course;
import org.example.assi_7.model.Student;

public class AssignGradeController implements Initializable {

    @FXML
    private Label studentNameLabel;

    @FXML
    private ComboBox<String> dropDownCourses;

    @FXML
    private TextField grade;

    @FXML
    private Label errorLabel;

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
    }

    public ObservableList<String> getCourses(String studentCode) {
        ObservableList<String> courses = FXCollections.observableArrayList();
        // get courses from DataStorage
    for (Course course : DataStorage.getInstance().getStudentByCode(studentCode).getEnrolledCourses()) {
            courses.add(course.getCourseName());
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
        dropDownCourses.setItems(getCourses(studentCode));
    }

    public void assignGrade(javafx.event.ActionEvent actionEvent) {
        // enroll into course
        // find student by studentCode
        Student student = DataStorage.getInstance().getStudentByCode(studentCode);
        // update student course
        // find course by course name
        Course course = DataStorage.getInstance().getCourseByName(dropDownCourses.getValue());
        if (course == null) {
            // make it red
            errorLabel.setStyle("-fx-text-fill: red");
            dropDownCourses.setStyle("-fx-border-color: red");
            errorLabel.setText("Course not found");
            return;
        }

        // assign grade
        String gradeStr = this.grade.getText();
        if (!Validator.forString(gradeStr).isInteger().isValid()) {
            // make it red
            errorLabel.setStyle("-fx-text-fill: red");
            this.grade.setStyle("-fx-border-color: red");
            errorLabel.setText("Grade must be a number");
            return;
        }
        int grade = Integer.parseInt(gradeStr);


        if (!Validator.forInt(grade).isBetween(0, 100).isValid()) {
            // make it red
            errorLabel.setStyle("-fx-text-fill: red");
            this.grade.setStyle("-fx-border-color: red");
            errorLabel.setText("Grade must be between 0 and 100");
            return;
        }
        student.assignGrade(course, grade);

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