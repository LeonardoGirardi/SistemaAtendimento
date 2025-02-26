package programacao.s_a.models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import programacao.s_a.models.entities.AttachmentEntity;
import programacao.s_a.models.utils.JpaUtil;

import java.util.List;
import java.util.UUID;

public class TicketAttachmentDao {

    public void save(AttachmentEntity attachment) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(attachment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao salvar anexo", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public List<AttachmentEntity> findByTicketId(UUID ticketId) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        return entityManager.createQuery("FROM AttachmentEntity a WHERE a.ticket.id = :ticketId", AttachmentEntity.class)
                .setParameter("ticketId", ticketId)
                .getResultList();
    }
}
