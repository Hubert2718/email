package com.hubert.controller;

import com.hubert.EmailManager;
import com.hubert.controller.services.LoginService;
import com.hubert.model.EmailAccount;
import com.hubert.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController extends BaseController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailAddressField;

    @FXML
    private Label errorLabel;

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void loginButtonAction() {
        if(fieldsAreValid()) {
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManager);
            LoginEmailResult loginEmailResult = loginService.login();

            switch (loginEmailResult) {
                case SUCCESS:
                    System.out.println("login successful" + emailAccount);
            }
        }
        System.out.println("click");
        super.viewFactory.showMainWindow();
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        super.viewFactory.closeStage(stage);
    }

    private boolean fieldsAreValid() {
        if(emailAddressField.getText().isEmpty()) {
            errorLabel.setText("Please fill email");
            return false;
        }
        if(passwordField.getText().isEmpty()) {
            errorLabel.setText("Please fill password");
            return false;
        }

        return true;
    }

}
