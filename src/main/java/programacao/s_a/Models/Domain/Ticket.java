package programacao.s_a.Models.Domain;

import lombok.Data;
import programacao.s_a.Models.Entitys.UserEntity;
import programacao.s_a.Views.TicketStatus;

import java.util.UUID;
import java.util.Date;
import java.util.List;

@Data
public class Ticket {
    private UUID id;

    private User supportUser;

    private String subject;

    private String description;

    private TicketStatus status;

    private UserEntity createdBy;

    private List<Attachment> attachments;

    private UUID createdByUserId;

    private Date createAt;

    private UUID updatedBy;

    private Date updatedAt;
}