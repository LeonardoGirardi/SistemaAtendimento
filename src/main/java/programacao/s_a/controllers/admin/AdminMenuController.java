package programacao.s_a.controllers.admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import programacao.s_a.views.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminMenuController implements Initializable {

    public Button listClientsBtn;
    public Button openTicketBtn;
    public Button closedTicketBtn;
    public Button faqBtn;
    public Button exitBtn;

    public AdminMenuController() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listClientsBtn.setOnAction(e -> {
            try {
                ViewFactory.getInstance().showListOfClients();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        openTicketBtn.setOnAction(e -> {
           try {
               ViewFactory.getInstance().showOpenedTicketListForAdmin();
           } catch (IOException ex) {
               throw new RuntimeException(ex);
           }
        });
        closedTicketBtn.setOnAction(e -> {
            try {
                ViewFactory.getInstance().showClosedTicketListForAdmin();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        faqBtn.setOnAction(e -> {
            try {
                ViewFactory.getInstance().showFaqForAdmin();
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

