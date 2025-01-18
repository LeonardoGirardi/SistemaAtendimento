package programacao.s_a.Models.Domain;

import lombok.Data;
import programacao.s_a.Views.TicketStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class TicketInteraction {
    private String message;

    private TicketStatus status;

    private UUID userId;

    private UUID ticketId;

    private UUID id;

    private Ticket ticket;

    private List<Attachment> attachments;

    private User sentByUser;

    private User createdBy;

    private Date createAt;

    private UUID updatedBy;

    private Date updatedAt;
}
