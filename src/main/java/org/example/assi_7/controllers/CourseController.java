package org.example.assi_7.controllers;

import com.almasb.fxgl.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.assi_7.model.Course;

import java.io.IOException;
import java.util.Objects;

public class CourseController implements Initializable {

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> courseCodeColumn;

    @FXML
    private TableColumn<Course, String> courseNameColumn;

    @FXML
    private TableColumn<Course, Integer> maxCapacityColumn;

    @FXML
    private TableColumn<Course, Integer> totalEnrolledColumn;

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        // course table should be full width
        courseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
        maxCapacityColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("maxCapacity"));
        totalEnrolledColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("totalEnrolled"));

        courseTable.setItems(getCourses());

        courseTable.setEditable(true);
    }

    // dummy data
    public ObservableList<Course> getCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList();

        // get courses from DataStorage
        courses.addAll(Objects.requireNonNull(org.example.assi_7.helpers.DataStorage.getInstance()).getCourses());
            return courses;
    }

    public void enrollStudent(javafx.event.ActionEvent actionEvent) {
        String path = "/org/example/assi_7/view/components/EnrollStudent.fxml";
        // new window to add student
        try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
    Parent root = loader.load();

    // new stage
            Stage stage = new Stage();
            stage.setTitle("Enroll Student");
            stage.setResizable(false);
            stage.setScene(new javafx.scene.Scene(root, 400, 300));

            // set the owner of the new stage
            stage.initOwner(courseTable.getScene().getWindow());

            // show the new stage
            stage.show();


        } catch (IOException e) {
            Logger.get(AppController.class).fatal("Error loading " + path, e);
        }
    }
}
