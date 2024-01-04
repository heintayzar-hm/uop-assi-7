package org.example.assi_7.controllers;

import com.almasb.fxgl.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.assi_7.helpers.DataStorage;
import org.example.assi_7.model.Student;

import java.io.IOException;
import java.util.List;

public class StudentController implements Initializable {

    @FXML
    private TableView<Student> studentTableView;

    @FXML
    private TableColumn<Student, String> studentNameColumn;

    @FXML
    private TableColumn<Student, String> studentIDColumn;

    @FXML
    private TableColumn<Student, String> studentOverallGradeColumn;

    @FXML
    private TableColumn<Student, String> studentActionsColumn;

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        // student table should be full width
        studentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
        studentOverallGradeColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("overallGrade"));
        studentTableView.setItems(getStudents());

        // add edit and delete buttons to each row
        studentActionsColumn.setCellFactory(param -> getStudentActionsCell());

        studentTableView.setEditable(true);
    }

    // dummy data
    public ObservableList<Student> getStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        // get students from DataStorage
        List<Student> studentList = DataStorage.getInstance().getStudents();

        // clear the list
        studentTableView.getItems().clear();
        students.addAll(studentList);
        return students;
    }

    public TableCell<Student, String> getStudentActionsCell() {
        return new TableCell<Student, String>() {
            // create buttons
            final javafx.scene.control.Button editButton = new javafx.scene.control.Button("Update Student");

            final javafx.scene.control.Button viewButton = new javafx.scene.control.Button("View Student");

            // add buttons to cell
            {
                editButton.getStyleClass().add("action-button");
                viewButton.getStyleClass().add("action-button");
                editButton.setOnAction((javafx.event.ActionEvent event) -> {
                    Student student = getTableView().getItems().get(getIndex());
                    editStudent(student);
                });

                viewButton.setOnAction((javafx.event.ActionEvent event) -> {
                    Student student = getTableView().getItems().get(getIndex());
                    viewStudent(student);
                });
            }

            // display buttons if the row is not empty
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                // Set buttons in the cell
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttonsBox = new HBox(5, editButton, viewButton);
                    setGraphic(buttonsBox);
                }
            }
        };
    }

    public void editStudent(Student student) {
        String path = "/org/example/assi_7/view/components/UpdateStudent.fxml";
        // new window to add student
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            EditStudentController editStudentController = loader.getController();

            editStudentController.setStudentName(student.getName());
            editStudentController.setStudentCode(student.getStudentID());

            // new stage
            Stage stage = new Stage();
            stage.setTitle("Edit Student");
            stage.setResizable(false);
            stage.setScene(new javafx.scene.Scene(root, 600, 400));

            // set the owner of the new stage
            stage.initOwner(studentTableView.getScene().getWindow());

            // show the new stage
            stage.showAndWait();

            // refresh the table view
            studentTableView.setItems(getStudents());

        } catch (IOException e) {
            Logger.get(AppController.class).fatal("Error loading " + path, e);
        }
    }

    public void viewStudent(Student student) {
        String path = "/org/example/assi_7/view/components/ViewStudent.fxml";
        // new window to add student
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            // set student name
            ViewStudentController viewStudentController = loader.getController();
            viewStudentController.setStudentName(student.getName());
            viewStudentController.setStudentCode(student.getStudentID());

            // new stage
            Stage stage = new Stage();
            stage.setTitle("View Student");
            stage.setResizable(false);
            stage.setScene(new javafx.scene.Scene(root, 600, 400));

            // set the owner of the new stage
            stage.initOwner(studentTableView.getScene().getWindow());

            // show the new stage
            stage.showAndWait();

            // refresh the table view
            studentTableView.setItems(getStudents());
        } catch (IOException e) {
            Logger.get(AppController.class).fatal("Error loading " + path, e);
        }
    }
    public void addStudent(javafx.event.ActionEvent actionEvent) {
        String path = "/org/example/assi_7/view/components/AddStudent.fxml";
        // new window to add student
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            // new stage
            Stage stage = new Stage();
            stage.setTitle("Add Student");
            stage.setResizable(false);
            stage.setScene(new javafx.scene.Scene(root, 600, 400));

            // set the owner of the new stage
            stage.initOwner(studentTableView.getScene().getWindow());

            // show the new stage
            stage.showAndWait();

            // refresh the table view
            studentTableView.setItems(getStudents());


        } catch (IOException e) {
            Logger.get(AppController.class).fatal("Error loading " + path, e);
        }
    }
}
