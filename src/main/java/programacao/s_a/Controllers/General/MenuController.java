package programacao.s_a.Controllers.General;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import lombok.Setter;
import programacao.s_a.Models.dto.TicketCellDto;
import programacao.s_a.Models.dto.UserDto;
import programacao.s_a.Views.AccountType;
import programacao.s_a.Views.ClientCellFactory;
import programacao.s_a.Views.MenuOptions;
import programacao.s_a.Views.TicketCellFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class MenuController implements Initializable {
    public Button listClientsBtn;
    public Button newTicketBtn;
    public Button openTicketBtn;
    public Button closedTicketBtn;
    public Button faqBtn;
    public Button exitBtn;

    private AccountType accountType;

    @Setter
    private BaseViewController baseController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void configureMenu(AccountType accountType) {
        // Configurações iniciais
        this.accountType = accountType;

        resetMenuVisibility();

        // Configura com base no tipo de conta
        switch (accountType) {
            case ADMIN:
                setMenuOptionsVisibility(MenuOptions.CLIENTS, true);
                setMenuOptionsVisibility(MenuOptions.NEW_TICKET, false);
                break;
            case CLIENT:
                setMenuOptionsVisibility(MenuOptions.CLIENTS, false);
                setMenuOptionsVisibility(MenuOptions.NEW_TICKET, true);
                break;
            default:
                throw new IllegalStateException("Tipo de conta não reconhecido: " + accountType);
        }
    }


    private void resetMenuVisibility() {
        for (MenuOptions option : MenuOptions.values()) {
            setMenuOptionsVisibility(option, true);
        }
    }

    private void setMenuOptionsVisibility(MenuOptions option, boolean isVisible) {
        switch (option) {
            case CLIENTS:
                listClientsBtn.setVisible(isVisible);
                listClientsBtn.setDisable(!isVisible);
                break;
            case NEW_TICKET:
                newTicketBtn.setVisible(isVisible);
                newTicketBtn.setDisable(!isVisible);
                break;
            case OPEN_TICKETS:
                openTicketBtn.setVisible(isVisible);
                openTicketBtn.setDisable(!isVisible);
                break;
            case CLOSED_TICKETS:
                closedTicketBtn.setVisible(isVisible);
                closedTicketBtn.setDisable(!isVisible);
                break;
            case FAQ:
                faqBtn.setVisible(isVisible);
                faqBtn.setDisable(!isVisible);
                break;
            case EXIT:
                exitBtn.setVisible(isVisible);
                exitBtn.setDisable(!isVisible);
                break;
        }
    }

    @FXML
    private void onListClientsClick() {
        baseController.loadView("General/ListView.fxml");
        updateListViewTitle("Lista de Clientes");
        Callback<ListView<UserDto>, ListCell<UserDto>> cellFactory = listView -> new ClientCellFactory();

        ListViewController<UserDto> listViewController = baseController.getController();
        if (listViewController != null) {
            listViewController.updateListView(listViewController.generateSampleData(), cellFactory);
        }
    }
    @FXML
    private void onNewTicketClick() {
        baseController.loadView("Client/NewTicket.fxml");
    }
    @FXML
    private void onOpenTicketClick() {
        baseController.loadView("General/ListView.fxml");
        updateListViewTitle("Tickets: Abertos | Em Progresso ");
        if(accountType == AccountType.ADMIN){
            updateListViewTitle("Tickets: Abertos | Em Progresso | Aguardando Resposta");
            getTicketFactory();
        }
        getTicketFactory();
    }
    @FXML
    private void onClosedTicketClick() {
        baseController.loadView("General/ListView.fxml");
        updateListViewTitle("Tickets: Fechados | Cancelados ");
        getTicketFactory();
    }
    @FXML
    private void onFaqClick() {
        baseController.loadView("General/ListView.fxml");
        updateListViewTitle("Perguntas Frequentes");
    }
    @FXML
    private void onExitClick() {
        System.exit(0);
    }

    private void getTicketFactory(){

        Callback<ListView<TicketCellDto>, ListCell<TicketCellDto>> cellFactory = listView -> new TicketCellFactory();

        ListViewController<TicketCellDto> listViewController = baseController.getController();
        if (listViewController != null) {
            listViewController.updateListView(listViewController.generateSampleDataTickets(), cellFactory);
        }
    }

    private void updateListViewTitle(String title) {
        ListViewController listViewController = baseController.getController();
        if (listViewController != null) {
            listViewController.setTitle(title);
        } else {
            System.out.println("Erro ao obter o controlador ListViewController.");
        }
    }
}




