package programacao.s_a.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import programacao.s_a.controllers.general.MessageCellController;
import programacao.s_a.models.entities.InteractionEntity;

import java.io.IOException;

public class MessageCellFactory extends ListCell<InteractionEntity> {
    @Override
    protected void updateItem(InteractionEntity interaction, boolean empty) {
        super.updateItem(interaction, empty);
        if (empty || interaction == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/general/MessageCell.fxml"));
            Parent cellLayout;
            try {
                cellLayout = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MessageCellController controller = loader.getController();
            controller.setInteraction(interaction);
            setGraphic(cellLayout);
        }
    }
}