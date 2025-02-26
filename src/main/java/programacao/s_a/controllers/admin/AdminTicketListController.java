package programacao.s_a.controllers.admin;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import programacao.s_a.controllers.general.TicketListRefreshEvent;
import programacao.s_a.models.entities.TicketEntity;
import programacao.s_a.models.service.TicketService;
import programacao.s_a.views.TicketCellFactory;
import programacao.s_a.views.TicketStatus;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminTicketListController implements Initializable {

    public ListView<TicketEntity> ticketListView;
    public Text titleOfList;

    TicketService ticketService = new TicketService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ticketListView.setSelectionModel(null);
        loadTickets();
        ticketListView.addEventFilter(TicketListRefreshEvent.TICKET_LIST_REFRESH, event -> refreshTicketList());
    }
    private List<TicketEntity> allTickets = new ArrayList<>();

    public void setFilterStatuses(List<TicketStatus> statuses) {
        List<TicketEntity> filtered = allTickets.stream()
                .filter(ticket -> statuses.contains(ticket.getStatus()))
                .collect(Collectors.toList());
        ticketListView.getItems().setAll(filtered);
        ticketListView.setCellFactory(list -> new TicketCellFactory());
    }

    public void setAllTickets(List<TicketEntity> tickets) {
        this.allTickets = tickets;
        setFilterStatuses(Arrays.asList(TicketStatus.OPEN, TicketStatus.IN_PROGRESS));
    }


    public void loadTickets() {
        List<TicketEntity> tickets = ticketService.getAllTickets();
        setAllTickets(tickets);
    }

    public void refreshTicketList() {
        loadTickets();
    }
}
