package org.example.assi_7.controllers;

import com.almasb.fxgl.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class AppController implements Initializable{


    @FXML
    private VBox contentPane;
    public void loadView(String fxmlPath) {
        try {
            // Load the specified FXML file
            Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));

            // Clear existing content and set the new view
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

        } catch (IOException e) {
            Logger.get(AppController.class).fatal("Error loading " + fxmlPath, e);
        }
    }
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        loadView("/org/example/assi_7/view/Student.fxml");
    }

    public void studentView(javafx.event.ActionEvent actionEvent) throws IOException {
        loadView("/org/example/assi_7/view/Student.fxml");
    }

    public void courseView(javafx.event.ActionEvent actionEvent) throws IOException {
        loadView("/org/example/assi_7/view/Course.fxml");
    }

    public void gradeView(javafx.event.ActionEvent actionEvent) throws IOException {
        loadView("/org/example/assi_7/view/Grade.fxml");
    }
}
