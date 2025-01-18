package programacao.s_a.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programacao.s_a.Controllers.General.BaseViewController;
import programacao.s_a.Controllers.LoginController;

import java.io.IOException;


public class ViewFactory {
    private Stage stage;

    public ViewFactory(Stage stage) {
        this.stage = stage;
    }

    //////////////////// Loader Methods ////////////////
    private Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        if (fxmlPath.equals("/Fxml/Login.fxml")) {
            LoginController controller = loader.getController();
            controller.initData(this);  // Passa a ViewFactory para o controlador
        }
        return root;
    }
    private void setScene(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //////////////////// App Views //////////////////

    public void showCreateAccountWindow() throws IOException {
        setScene(loadFXML("/Fxml/Client/CreateAccount.fxml"));
    }

    public void showDashboard(AccountType accountType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/General/BaseView.fxml"));
            Parent root = loader.load();

            BaseViewController baseController = loader.getController();
            baseController.setAccountType(accountType);

            // Passa o tipo de conta para configurar a interface
            if (accountType == AccountType.ADMIN) {
                baseController.loadView("General/ListView.fxml"); // Página padrão do admin
            } else {
                baseController.loadView("Client/NewTicket.fxml"); // Página padrão do cliente
            }

            setScene(root); // Define a nova cena
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLoginWindow() throws IOException {
        setScene(loadFXML("/Fxml/Login.fxml"));
    }
}