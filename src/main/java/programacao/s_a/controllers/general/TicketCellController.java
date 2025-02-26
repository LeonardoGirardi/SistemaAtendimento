package programacao.s_a.controllers.general;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketCellController implements Initializable {
    public Label ticketNameLbl;
    public Label ticketOpenDateLbl;
    public Label ticketStatusLbl;
    public Button viewTicketBtn;
    public Button closeTicketBtn;
    public Button cancelTicketBtn;
    public FontAwesomeIconView ticketIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
