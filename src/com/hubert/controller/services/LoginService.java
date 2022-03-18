package com.hubert.controller.services;

import com.hubert.EmailManager;
import com.hubert.controller.LoginEmailResult;
import com.hubert.model.EmailAccount;

import javax.mail.*;
import java.util.Objects;

public class LoginService {
    EmailAccount emailAccount;
    EmailManager emailManager;

    public LoginService(EmailAccount emailAccount, EmailManager emailManager) {
        this.emailAccount = emailAccount;
        this.emailManager = emailManager;
    }

    public LoginEmailResult login() {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount.getAddress(), emailAccount.getPassword());
            }
        };
        try {
            Session session = Session.getInstance(emailAccount.getProperties(), authenticator);
            Store store = session.getStore("imaps");
            store.connect(emailAccount.getProperties().getProperty("incomingHost"),
                    emailAccount.getAddress(),
                    emailAccount.getPassword());
            emailAccount.setStore(store);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return LoginEmailResult.FAILED_BY_UNEXPECTED_ERROR;
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
            return LoginEmailResult.FAILED_BY_CREDENTIALS;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return LoginEmailResult.FAILED_BY_UNEXPECTED_ERROR;
        }
        return LoginEmailResult.SUCCESS;

    }
}
