package programacao.s_a.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import programacao.s_a.Controllers.Admin.ClientCellController;
import programacao.s_a.Models.dto.UserDto;

public class ClientCellFactory extends ListCell<UserDto> {
    @Override
    protected void updateItem(UserDto user, boolean empty) {
        super.updateItem(user, empty);
        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ClientCell.fxml"));
                setGraphic(loader.load());

                ClientCellController controller = loader.getController();
                controller.nameLbl.setText(user.getUsername());
                controller.emailLbl.setText(user.getEmail());
                controller.dateLbl.setText(user.getCreateAt().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
