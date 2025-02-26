package programacao.s_a.controllers.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import programacao.s_a.views.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {

    @FXML
    public Button newTicketBtn;
    @FXML
    public Button openTicketBtn;
    @FXML
    public Button closedTicketBtn;
    @FXML
    public Button faqBtn;
    @FXML
    public Button exitBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newTicketBtn.setOnAction(e -> {
            try {
                ViewFactory.getInstance().showNewTicketView();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        openTicketBtn.setOnAction(e -> {
            try {
                ViewFactory.getInstance().showOpenedTicketListForClient();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        closedTicketBtn.setOnAction(e -> {
            try {
                ViewFactory.getInstance().showClosedTicketListForClient();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        faqBtn.setOnAction(e -> {
            try {
                ViewFactory.getInstance().showFaqForClient();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        exitBtn.setOnAction(e -> {
            try {
                ViewFactory.getInstance().closeCurrentStage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}



