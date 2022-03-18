package com.hubert.view;

import com.hubert.EmailManager;
import com.hubert.controller.BaseController;
import com.hubert.controller.LoginWindowController;
import com.hubert.controller.MainWindowController;
import com.hubert.controller.OptionsWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ViewFactory {
    private EmailManager emailManager;
    private ArrayList<Stage> activeStages;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        activeStages = new ArrayList<Stage>();
    }

    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }


    public void showLoginWindow(){
        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        initStage(controller);
    }

    public void showMainWindow() {
        System.out.println("MainWindow called");
        BaseController controller = new MainWindowController(emailManager, this, "MainWindow.fxml");
        initStage(controller);
    }

    public void showOptionsWindow(){
        System.out.println("OptionsWindow called");
        BaseController controller = new OptionsWindowController(emailManager, this, "OptionsWindow.fxml");
        initStage(controller);
    }

    public void closeStage(Stage stage) {
        stage.close();
        activeStages.remove(stage);
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
        activeStages.add(stage);
    }

    public void updateStyles() {
        for(Stage stage : activeStages) {
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    Objects.requireNonNull(ColorTheme.getCssPath(colorTheme)))).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    Objects.requireNonNull(FontSize.getCssPath(fontSize)))).toExternalForm());

        }
    }
}
