package programacao.s_a.models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import programacao.s_a.models.entities.FaqEntity;
import programacao.s_a.models.utils.JpaUtil;

import java.util.List;

public class FaqDao {

    public void save(FaqEntity faq) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(faq);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao salvar FAQ", e);
        } finally {
            entityManager.close();
        }
    }

    public List<FaqEntity> findAll() {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            return entityManager.createQuery("SELECT f FROM FaqEntity f", FaqEntity.class).getResultList();
        } finally {
            entityManager.close();
        }
    }
}
