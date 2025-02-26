package programacao.s_a.models.service;

import jakarta.persistence.EntityNotFoundException;
import programacao.s_a.models.dao.TicketDao;
import programacao.s_a.models.dao.TicketInteractionDao;
import programacao.s_a.models.dao.TicketAttachmentDao;
import programacao.s_a.models.entities.TicketEntity;
import programacao.s_a.models.entities.InteractionEntity;
import programacao.s_a.models.entities.AttachmentEntity;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.views.InteractionStatus;
import programacao.s_a.views.TicketStatus;

import java.util.List;
import java.util.UUID;

public class TicketService {

    private final TicketDao ticketDao;
    private final TicketInteractionDao interactionDao;
    private final TicketAttachmentDao attachmentDao;

    public TicketService() {
        this.ticketDao = new TicketDao();
        this.interactionDao = new TicketInteractionDao();
        this.attachmentDao = new TicketAttachmentDao();
    }

    public List<TicketEntity> getAllTickets(){
        return ticketDao.findAll();
    }

    public List<TicketEntity> getTicketsByUserId(UserEntity user){
        return ticketDao.findByUserId(user.getId());
    }

    public void updateTicketStatus(TicketEntity ticket) {
        if (ticket == null || ticket.getId() == null) {
            throw new IllegalArgumentException("Ticket ou ID do ticket não pode ser nulo");
        }

        TicketEntity existingTicket = findTicketById(ticket.getId());
        existingTicket.setStatus(ticket.getStatus());
        ticketDao.update(existingTicket);
    }

    public void createTicket(TicketEntity newTicket) {
        if (newTicket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        newTicket.setStatus(TicketStatus.OPEN);
        ticketDao.save(newTicket);
    }

    public void addInteractionToTicket(UUID ticketId, InteractionEntity interaction) {
        if (ticketId == null || interaction == null) {
            throw new IllegalArgumentException("Ticket ID or Interaction cannot be null");
        }

        TicketEntity ticket = findTicketById(ticketId);
        if (ticket.getStatus() == TicketStatus.CLOSED || ticket.getStatus() == TicketStatus.CANCELLED) {
            throw new IllegalArgumentException("Cannot add interaction to a closed ticket");
        }

        interaction.setTicket(ticket);
        if(interaction.getStatus() == null) {
            interaction.setStatus(InteractionStatus.SENT);
        }
        interactionDao.save(interaction);
    }


    public List<InteractionEntity> getInteractionsForTicket(UUID ticketId) {
        return interactionDao.findByTicketId(ticketId);
    }


    public void addAttachmentToTicket(UUID ticketId, AttachmentEntity attachment) {
        if (ticketId == null || attachment == null) {
            throw new IllegalArgumentException("Ticket ID or Attachment cannot be null");
        }

        TicketEntity ticket = findTicketById(ticketId);
        attachment.setTicket(ticket);
        attachmentDao.save(attachment);
    }

      public List<AttachmentEntity> getAttachmentsForTicket(UUID ticketId) {
        return attachmentDao.findByTicketId(ticketId);
    }

    private TicketEntity findTicketById(UUID ticketId) {
        TicketEntity ticket = ticketDao.findById(ticketId);
        if (ticket == null) {
            throw new EntityNotFoundException("Ticket not found");
        }
        return ticket;
    }
}

