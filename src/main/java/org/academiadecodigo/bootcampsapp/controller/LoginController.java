package org.academiadecodigo.bootcampsapp.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.academiadecodigo.bootcampsapp.Navigation;
import org.academiadecodigo.bootcampsapp.model.User;
import org.academiadecodigo.bootcampsapp.service.ServiceRegistry;
import org.academiadecodigo.bootcampsapp.service.UserService;


public class LoginController implements Controller {

    private boolean login = false;

    private UserService userService;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public TextField email;
    @FXML
    private Hyperlink modeSwapper;
    @FXML
    private Button button;
    @FXML
    private Label informationText;
    @FXML
    private Label emailText;

    @FXML
    void modeSwap() {

        if (login) {
            registerMode();
        } else {
            loginMode();
        }
    }

    @FXML
    void onButtonClick() {

        if (login) {
            onLogin();
        } else {
            onRegister();
        }
    }

    public void initialize() {

      userService = (UserService) ServiceRegistry.getInstance().getService("User");
    }

    private void onLogin() {

        if (userService.authenticate(username.getText(), password.getText())) {

            setInformation("Login Successful.");
            informationText.setTextFill(Color.GREEN);
            fieldsClear();
            setInformation("");

            Navigation.getInstance().loadScreen("BootcampsForm");

        } else {

            setInformation("Incorrect Username or Password.");
            informationText.setTextFill(Color.GREEN);
        }

        fieldsClear();
    }

    private void onRegister() {


        if (validateRegistration() && fieldsAvailability()) {

            successfulRegister();
        }
    }

    private void fieldsClear() {

        username.clear();
        password.clear();
        email.clear();
    }

    private void setInformation(String text) {

        informationText.setText(text);
        informationText.setAlignment(Pos.CENTER);
    }

    private void loginMode() {
        login = true;
        emailText.setVisible(false);
        email.setVisible(false);
        modeSwapper.setText("Register");
        button.setText("Login");
        informationText.setText("");
    }

    private void registerMode() {
        login = false;
        emailText.setVisible(true);
        email.setVisible(true);
        modeSwapper.setText("codecadet_ Login");
        button.setText("Register");
        informationText.setText("");
    }

    private boolean validateRegistration() {

        if (username.getText().length() < 1 || password.getText().length() < 4) {
            setInformation("Invalid Username or Password. Password must be at least 4 letters.");
            informationText.setTextFill(Color.RED);
            fieldsClear();
            return false;
        }
        if (!email.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            fieldsClear();
            setInformation("Invalid e-mail");
            informationText.setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    private boolean fieldsAvailability() {

        if (userService.findByName(username.getText()) != null) {
            fieldsClear();
            setInformation("You are already registered, please go to login.");
            informationText.setTextFill(Color.RED);
            return false;
        }
        if (!userService.emailAvailability(email.getText())) {
            fieldsClear();
            setInformation("That e-mail is already registered.");
            informationText.setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    private void successfulRegister() {

        userService.addUser(new User(username.getText(), password.getText(), email.getText()));
        fieldsClear();
        setInformation("You have registered successfully.");
        informationText.setTextFill(Color.GREEN);

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
