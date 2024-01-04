module org.example.assi_7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.jfoenix;

    opens org.example.assi_7 to javafx.fxml;
    exports org.example.assi_7;
    exports org.example.assi_7.controllers;
    opens org.example.assi_7.controllers to javafx.fxml;
    exports org.example.assi_7.model;
    opens org.example.assi_7.model to javafx.fxml;
}