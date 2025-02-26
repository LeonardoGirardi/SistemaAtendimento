package programacao.s_a.controllers.client;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import programacao.s_a.controllers.general.TicketListRefreshEvent;
import programacao.s_a.models.entities.TicketEntity;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.models.service.SessionService;
import programacao.s_a.models.service.TicketService;
import programacao.s_a.views.TicketCellFactory;
import programacao.s_a.views.TicketStatus;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ClientListViewController implements Initializable {

    public Text titleOfList;
    public ListView<TicketEntity> listOfViews;

    private final TicketService ticketService = new TicketService();
    private UserEntity user;
    private List<TicketEntity> allTickets;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfViews.setSelectionModel(null);
        user = SessionService.getInstance().getLoggedUser();

        if (user == null) {
            System.out.println("Erro: Nenhum usuário logado.");
            return;
        }

        loadUserTickets();
        listOfViews.addEventFilter(TicketListRefreshEvent.TICKET_LIST_REFRESH, event -> refreshTicketList());
    }

    private void loadUserTickets() {
        if (user == null) return;

        List<TicketEntity> tickets = ticketService.getTicketsByUserId(user);
        setAllTickets(tickets);
    }

    public void setFilterStatuses(List<TicketStatus> statuses) {
        if (allTickets == null) return;

        List<TicketEntity> filtered = allTickets.stream()
                .filter(ticket -> statuses.contains(ticket.getStatus()))
                .collect(Collectors.toList());

        listOfViews.getItems().setAll(filtered);
        listOfViews.setCellFactory(list -> new TicketCellFactory());
    }

    private void setAllTickets(List<TicketEntity> tickets) {
        this.allTickets = tickets;
        setFilterStatuses(List.of(TicketStatus.OPEN, TicketStatus.IN_PROGRESS));
    }

    private void refreshTicketList() {
        loadUserTickets();
    }
}
