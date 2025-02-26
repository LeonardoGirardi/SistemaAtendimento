package programacao.s_a.controllers.general;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import programacao.s_a.models.entities.InteractionEntity;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class MessageCellController implements Initializable {

    @FXML
    private Label userNameLabel;

    @FXML
    private Label timestampLabel;

    @FXML
    private Label messageLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setInteraction(InteractionEntity interaction) {
        userNameLabel.setText(interaction.getSentByUser().getUsername());
        timestampLabel.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(interaction.getCreateAt()));
        messageLabel.setText(interaction.getMessage());
    }
}
