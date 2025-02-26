package programacao.s_a.controllers.general;

import javafx.event.Event;
import javafx.event.EventType;

public class TicketListRefreshEvent extends Event {
    public static final EventType<TicketListRefreshEvent> TICKET_LIST_REFRESH = new EventType<>(Event.ANY, "TICKET_LIST_REFRESH");

    public TicketListRefreshEvent() {
        super(TICKET_LIST_REFRESH);
    }
}