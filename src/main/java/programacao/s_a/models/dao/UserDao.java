package programacao.s_a.models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.Data;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.models.utils.JpaUtil;
import programacao.s_a.views.AccountType;

import java.util.List;
import java.util.Optional;

@Data
public class UserDao{

    public Optional<UserEntity> findByUsername(String username) {
        try (EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            UserEntity user = entityManager.createQuery("FROM UserEntity WHERE username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public void save(UserEntity user) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try (entityManager) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving UserEntity", e);
        }
    }

    public void delete(UserEntity user) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try (entityManager) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            user = entityManager.merge(user);
            entityManager.remove(user);
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting UserEntity", e);
        }
    }

    public List<UserEntity> getAllClients() {
        try (EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return entityManager.createQuery("FROM UserEntity WHERE accountType = :accountType", UserEntity.class)
                    .setParameter("accountType", AccountType.CLIENT)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all clients", e);
        }
    }
}
