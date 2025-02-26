package programacao.s_a.models.service;

import lombok.Getter;
import lombok.Setter;
import programacao.s_a.models.entities.UserEntity;

@Setter
@Getter
public class SessionService {
    private static SessionService instance;
    private UserEntity loggedUser;

    private SessionService() {
    }

    public static SessionService getInstance() {
        if (instance == null) {
            instance = new SessionService();
        }
        return instance;
    }

    public void logout() {
        loggedUser = null;
    }
}