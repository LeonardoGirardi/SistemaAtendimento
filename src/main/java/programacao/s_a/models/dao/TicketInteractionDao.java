package programacao.s_a.models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import programacao.s_a.models.entities.InteractionEntity;
import programacao.s_a.models.utils.JpaUtil;

import java.util.List;
import java.util.UUID;

public class TicketInteractionDao {

    public void save(InteractionEntity interaction) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(interaction);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving InteractionEntity", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public List<InteractionEntity> findByTicketId(UUID ticketId) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            return entityManager.createQuery("FROM InteractionEntity WHERE ticket.id = :ticketId", InteractionEntity.class)
                    .setParameter("ticketId", ticketId)
                    .getResultList();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
