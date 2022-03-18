module email {
    requires javafx.controls;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.fxml;
    requires activation;
    requires java.mail;

    opens com.hubert;
    opens com.hubert.view;
    opens com.hubert.controller;
}