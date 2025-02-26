package programacao.s_a.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import programacao.s_a.controllers.general.TicketCellController;
import programacao.s_a.controllers.general.TicketListRefreshEvent;
import programacao.s_a.models.entities.TicketEntity;
import programacao.s_a.models.service.TicketService;

import java.io.IOException;

public class TicketCellFactory extends ListCell<TicketEntity>{

    TicketService ticketService = new TicketService();

    @Override
    protected void updateItem(TicketEntity ticket, boolean empty) {
        super.updateItem(ticket, empty);

        if (empty || ticket == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/general/TicketCell.fxml"));
                Node cellContent = loader.load();
                TicketCellController controller = loader.getController();

                controller.ticketNameLbl.setText(ticket.getSubject());
                controller.ticketOpenDateLbl.setText(ticket.getCreateAt().toString());
                controller.ticketStatusLbl.setText(ticket.getStatus().toString());

                if (ticket.getStatus() == TicketStatus.CLOSED || ticket.getStatus() == TicketStatus.CANCELLED) {
                    controller.ticketIcon.setFill(javafx.scene.paint.Color.RED);
                } else {
                    controller.ticketIcon.setFill(javafx.scene.paint.Color.BLUE);
                }

                controller.viewTicketBtn.setOnAction(event -> {
                    try {
                        ViewFactory.getInstance().showTicketDetails(ticket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                if (ticket.getStatus() == TicketStatus.CLOSED || ticket.getStatus() == TicketStatus.CANCELLED) {
                    controller.closeTicketBtn.setVisible(false);
                    controller.cancelTicketBtn.setVisible(false);
                } else {
                    controller.closeTicketBtn.setVisible(true);
                    controller.cancelTicketBtn.setVisible(true);
                    controller.cancelTicketBtn.setOnAction(event -> {
                        ticket.setStatus(TicketStatus.CANCELLED);
                        ticketService.updateTicketStatus(ticket);
                        updateItem(ticket, false);
                        fireEvent(new TicketListRefreshEvent());
                    });
                    controller.closeTicketBtn.setOnAction(event -> {
                        ticket.setStatus(TicketStatus.CLOSED);
                        ticketService.updateTicketStatus(ticket);
                        updateItem(ticket, false);
                        fireEvent(new TicketListRefreshEvent());
                    });
                }
                setGraphic(cellContent);
            } catch (IOException e) {
                e.printStackTrace();
                setText("Error: Failed in generate TicketCell.");
            }
        }
    }
}