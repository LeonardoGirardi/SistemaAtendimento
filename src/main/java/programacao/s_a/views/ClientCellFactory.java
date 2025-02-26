package programacao.s_a.views;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.fxml.FXMLLoader;
import programacao.s_a.controllers.admin.ClientCellController;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.models.service.UserService;

import java.io.IOException;

public class ClientCellFactory extends ListCell<UserEntity> {
    @Override
    protected void updateItem(UserEntity user, boolean empty) {
        super.updateItem(user, empty);

        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/ClientCell.fxml"));
                Node cellContent = loader.load();
                ClientCellController controller = loader.getController();

                controller.nameLbl.setText(user.getUsername());
                controller.emailLbl.setText(user.getEmail());
                controller.dateLbl.setText(user.getCreatedAt().toString());

                controller.deleteClientBtn.setOnAction(event -> {
                    UserService userService = new UserService();
                    userService.deleteUser(user);

                    // Atualiza a lista removendo o item
                    getListView().getItems().remove(user);
                });

                setGraphic(cellContent);
            } catch (IOException e) {
                e.printStackTrace();
                setText("Erro ao carregar a célula.");
            }
        }
    }
}
