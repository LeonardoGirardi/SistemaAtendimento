package programacao.s_a.controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import programacao.s_a.models.entities.TicketEntity;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.models.service.TicketService;
import programacao.s_a.models.service.UserService;
import programacao.s_a.views.ClientCellFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminListViewController implements Initializable {
    public Text titleOfList;
    @FXML
    private ListView<UserEntity> listOfViews;

    UserService userService = new UserService();

    public AdminListViewController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfViews.setSelectionModel(null);
        loadUsers();
    }

    private void loadUsers() {
        List<UserEntity> users = userService.getAllClients();
        listOfViews.getItems().setAll(users);
        listOfViews.setCellFactory(param -> new ClientCellFactory());
    }
}
