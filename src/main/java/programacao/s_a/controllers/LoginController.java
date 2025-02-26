package programacao.s_a.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.models.service.SessionService;
import programacao.s_a.views.AccountType;
import programacao.s_a.models.service.UserService;
import programacao.s_a.views.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label username_lbl;
    public TextField username_fld;
    public Label password_lbl;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;
    public Label signUpLabel;
    public Button signUpBtn;

    private final UserService userService = new UserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        login_btn.setOnAction(e -> {
            try {
                handleLogin();
            } catch (IOException ex) {
                error_lbl.setText("An error occurred while logging in.");
            }
        });


        signUpBtn.setOnAction(e -> {
            try {
                goToCreateAccount();
            } catch (IOException ex) {
                error_lbl.setText("Error navigating to sign-up page");
            }
        });


        acc_selector.setItems(FXCollections.observableArrayList(AccountType.values()));
        acc_selector.valueProperty().addListener((observable, oldValue, newValue) -> updateSignUpVisibility(newValue));


        acc_selector.setValue(AccountType.CLIENT);
    }

    private void updateSignUpVisibility(AccountType newValue) {
        boolean isAdmin = newValue == AccountType.ADMIN;
        signUpBtn.setVisible(!isAdmin);
        signUpLabel.setVisible(!isAdmin);
    }

    private void goToCreateAccount() throws IOException {
        Stage currentStage = (Stage) signUpBtn.getScene().getWindow();
        ViewFactory.getInstance(currentStage).showCreateAccountWindow();
    }

    private void handleLogin() throws IOException {
        String username = username_fld.getText();
        String password = password_fld.getText();


        if (username.isEmpty() || password.isEmpty()) {
            error_lbl.setText("Username or password cannot be empty.");
            error_lbl.setVisible(true);
            return;
        }


        UserEntity loggedInUser = userService.loginUser(username, password);
        if (loggedInUser != null) {
            SessionService.getInstance().setLoggedUser(loggedInUser);
            error_lbl.setVisible(false);
            error_lbl.setText("");


            Stage currentStage = (Stage) login_btn.getScene().getWindow();
            ViewFactory viewFactory = ViewFactory.getInstance(currentStage);
            viewFactory.showDashboardView(loggedInUser);
        } else {
            error_lbl.setText("Invalid username or password.");
            error_lbl.setVisible(true);
        }
    }
}

