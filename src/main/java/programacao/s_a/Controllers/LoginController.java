package programacao.s_a.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import programacao.s_a.Models.Service.UserService;
import programacao.s_a.Views.AccountType;
import programacao.s_a.Views.ViewFactory;

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

    private UserService userService;
    private ViewFactory viewFactory;

    private AccountType currAccType;

    public LoginController(){
    }

    public void initData(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;  // Inicializa a viewFactory
    }

    public LoginController(UserService userService, ViewFactory viewFactory) {
        this.userService = userService;
        this.viewFactory = viewFactory;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currAccType = AccountType.CLIENT;

        acc_selector.setItems(FXCollections.observableArrayList(AccountType.values()));
        acc_selector.valueProperty().addListener(new ChangeListener<AccountType>() {

            @Override
            public void changed(ObservableValue<? extends AccountType> observable, AccountType oldValue, AccountType newValue) {
                currAccType = newValue;
                if (newValue == AccountType.ADMIN) {
                    signUpBtn.setVisible(false);
                    signUpLabel.setVisible(false);
                } else {
                    signUpBtn.setVisible(true);
                    signUpLabel.setVisible(true);
                }
            }
        });
    }

    @FXML
    private void configLoginForAccountType() throws IOException {
       viewFactory.showDashboard(currAccType);
    }

    @FXML
    public void goToCreateAccount() throws IOException {
        viewFactory.showCreateAccountWindow();
    }
}


