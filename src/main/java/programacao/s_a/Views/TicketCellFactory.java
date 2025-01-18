package programacao.s_a.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import programacao.s_a.Controllers.General.TicketCellController;
import programacao.s_a.Models.dto.TicketCellDto;

public class TicketCellFactory extends ListCell<TicketCellDto> {
    @Override
    protected void updateItem(TicketCellDto user, boolean empty) {
        super.updateItem(user, empty);
        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/General/ticketCell.fxml"));
                setGraphic(loader.load());

                TicketCellController controller = loader.getController();
                controller.ticketNameLbl.setText(user.getUsername());
                controller.ticketStatusLbl.setText(user.getStatus().name());
                controller.ticketOpenDateLbl.setText(user.getCreateAt().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}