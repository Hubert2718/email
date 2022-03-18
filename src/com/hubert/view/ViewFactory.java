package com.hubert.view;

import com.hubert.EmailManager;
import com.hubert.controller.BaseController;
import com.hubert.controller.LoginWindowController;
import com.hubert.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private EmailManager emailManager;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    public void showLoginWindow(){
        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        initStage(controller);
    }

    public void showMainWindow() {
        System.out.println("MainWindow showed");
        BaseController controller = new MainWindowController(emailManager, this, "MainWindow.fxml");
        initStage(controller);
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    private void initStage(BaseController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        }
        catch (IOException e) {
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
