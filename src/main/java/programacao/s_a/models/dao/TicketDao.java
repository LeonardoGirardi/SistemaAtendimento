package programacao.s_a.models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import programacao.s_a.models.entities.TicketEntity;
import programacao.s_a.models.utils.JpaUtil;

import java.util.List;
import java.util.UUID;

public class TicketDao {

    public void save(TicketEntity ticket) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving TicketEntity", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public List<TicketEntity> findByUserId(UUID userId) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        return entityManager.createQuery("SELECT t FROM TicketEntity t WHERE t.createdBy.id = :userId", TicketEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }


    public void update(TicketEntity ticket) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error updating TicketEntity", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    public TicketEntity findById(UUID id) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            return entityManager.find(TicketEntity.class, id);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public List<TicketEntity> findAll() {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        return entityManager.createQuery("SELECT t FROM TicketEntity t", TicketEntity.class).getResultList();
    }
}
