package programacao.s_a.views;

import javafx.scene.control.ListCell;
import programacao.s_a.controllers.general.FaqCellController;
import programacao.s_a.models.entities.FaqEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class FaqCellFactory extends ListCell<FaqEntity> {

    @Override
    protected void updateItem(FaqEntity faq, boolean empty) {
        super.updateItem(faq, empty);

        if (empty || faq == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/general/FaqCell.fxml"));
                Node cell = loader.load();
                FaqCellController controller = loader.getController();

                controller.questionLabel.setText(faq.getQuestion());
                controller.answerLabel.setText(faq.getAnswer());

                setGraphic(cell);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}