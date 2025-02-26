package programacao.s_a.models.service;

import programacao.s_a.models.dao.UserDao;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.models.utils.PasswordUtil;
import programacao.s_a.views.AccountType;

import java.util.Date;
import java.util.Optional;

public class AdminInitializer {
    private final UserDao userDao;

    public AdminInitializer() {
        this.userDao = new UserDao();
    }

    public void initializeAdmin() {
        Optional<UserEntity> existingAdmin = userDao.findByUsername("Admin");

        if (existingAdmin.isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setUsername("Admin");
            admin.setEmail("admin@system.com");
            admin.setPassword(PasswordUtil.hashPassword("admin123"));
            admin.setAccountType(AccountType.ADMIN);
            admin.setCreatedAt(new Date());

            userDao.save(admin);
            System.out.println("Admin created: " + admin.getEmail());
        } else {
            System.out.println("Admin already exists: " + existingAdmin.get().getEmail());
        }
    }
}
