package programacao.s_a.Controllers.Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    public Label usernameLabel;
    public TextField usernameField;
    public Label passwordLabel;
    public TextField passwordField;
    public Label emailLabel;
    public TextField emailField;
    public Button createAccBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void goToFirstPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/General/BaseView.fxml"));
        BorderPane page = loader.load();

        Scene createBaseScene = new Scene(page);

        Stage stage = (Stage) createAccBtn.getScene().getWindow();
        stage.setScene(createBaseScene);
        stage.show();
    }
}


