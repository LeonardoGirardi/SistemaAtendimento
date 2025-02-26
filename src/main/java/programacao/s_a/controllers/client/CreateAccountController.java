package programacao.s_a.controllers.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.models.exceptions.BusinessException;
import programacao.s_a.models.service.UserService;
import programacao.s_a.views.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class CreateAccountController implements Initializable {

    @FXML
    public Label usernameLabel;
    @FXML
    public TextField usernameField;
    @FXML
    public Label passwordLabel;
    @FXML
    public TextField passwordField;
    @FXML
    public Label emailLabel;
    @FXML
    public TextField emailField;
    @FXML
    public Button createAccBtn;

    private final UserService userService = new UserService();

    public CreateAccountController() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAccBtn.setOnAction(e -> {
            try {
                handleSignUp();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void handleSignUp() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        if (username == null || username.isBlank() ||
                password == null || password.isBlank() ||
                email == null || email.isBlank()) {

            String error = "Fields cannot be empty!";
            showAlert(error);
            throw new BusinessException(error);
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        try {
            userService.createUser(newUser);
            ViewFactory.getInstance().showLoginWindow();
        } catch (BusinessException e) {
            showAlert(e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
