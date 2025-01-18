package programacao.s_a.Controllers.General;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import programacao.s_a.Models.dto.TicketCellDto;
import programacao.s_a.Models.dto.UserDto;
import programacao.s_a.Views.TicketStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListViewController<T> {
    public Text titleOfList;
    public ListView<T> listOfViews;

    public void setTitle(String title) {
        titleOfList.setText(title);
    }

    public void initialize(Callback<ListView<T>, ListCell<T>> cellFactory) {
        if (cellFactory != null) {
            listOfViews.setCellFactory(cellFactory);
        }
    }

    public void setItems(Iterable<T> items) {
        listOfViews.getItems().clear();
        for (T item : items) {
            listOfViews.getItems().add(item);
        }
    }

    public void updateListView(List<T> items, Callback<ListView<T>, ListCell<T>> cellFactory) {
        listOfViews.setCellFactory(cellFactory);

        listOfViews.getItems().clear();
        listOfViews.getItems().addAll(items);
    }

    public List<UserDto> generateSampleData() {
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto("John Doe", "john@example.com", new Date(), true));
        users.add(new UserDto("Jane Smith", "jane@example.com", new Date(), false));
        return users;
    }

    public List<TicketCellDto> generateSampleDataTickets() {
        List<TicketCellDto> tickets = new ArrayList<>();
        tickets.add(new TicketCellDto("John Doe", TicketStatus.OPEN , new Date()));
        tickets.add(new TicketCellDto("James Smith", TicketStatus.IN_PROGRESS , new Date()));
        return tickets;
    }
}

