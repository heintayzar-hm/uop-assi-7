package org.example.assi_7;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/assi_7/view/App.fxml")));
//      stage.initStyle(javafx.stage.StageStyle.UNDECORATED);

      root.setOnMousePressed(event -> {
          root.getScene().getWindow().setX(event.getScreenX() - root.getScene().getWindow().getX());
          root.getScene().getWindow().setY(event.getScreenY() - root.getScene().getWindow().getY());
      });

        root.setOnMouseDragged(event -> {
            root.getScene().getWindow().setX(event.getScreenX() - root.getScene().getWindow().getX());
            root.getScene().getWindow().setY(event.getScreenY() - root.getScene().getWindow().getY());
        });



      stage.setTitle("Student Management System");
      stage.setResizable(true);

      stage.setScene(new Scene(root, 800, 600));
      stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}