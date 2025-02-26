package programacao.s_a.models.utils;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

public class JpaUtil {
    @Getter
    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("CallCenterDB");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}

