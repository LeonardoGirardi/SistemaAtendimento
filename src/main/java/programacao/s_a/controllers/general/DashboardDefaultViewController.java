package programacao.s_a.controllers.general;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardDefaultViewController implements Initializable {

    @FXML
    private Label welcomeLabel;

    public DashboardDefaultViewController() {}

    public void setUserName(String userName) {
        welcomeLabel.setText("Bem-vindo(a), " + userName + "!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}