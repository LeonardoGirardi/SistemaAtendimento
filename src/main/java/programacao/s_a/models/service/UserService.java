package programacao.s_a.models.service;

import programacao.s_a.models.dao.UserDao;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.models.utils.PasswordUtil;
import programacao.s_a.views.AccountType;
import programacao.s_a.models.exceptions.BusinessException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public UserEntity loginUser(String username, String password) {
        validateInput(username, password);

        Optional<UserEntity> userEntity = userDao.findByUsername(username);

        if (userEntity.isEmpty()) {
            throw new BusinessException("Invalid username or password");
        }
        UserEntity foundUser = userEntity.get();

        if (!PasswordUtil.checkPassword(password, foundUser.getPassword())) {
            throw new BusinessException("Invalid password");
        }
        return foundUser;
    }

    public void createUser(UserEntity newUser) {

        if (newUser == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Optional<UserEntity> existentUser = userDao.findByUsername(newUser.getUsername());

        if (existentUser.isPresent()) {
            throw new BusinessException("Username already exists in the system");
        }

        validateEmail(newUser.getEmail());

        newUser.setCreatedAt(new Date());
        newUser.setAccountType(AccountType.CLIENT);
        newUser.setPassword(PasswordUtil.hashPassword(newUser.getPassword()));
        userDao.save(newUser);

    }

    public void deleteUser(UserEntity user) {
        Optional<UserEntity> existentUser = userDao.findByUsername(user.getUsername());
        existentUser.ifPresent(userDao::delete);
    }

    private void validateInput(String username, String password) {
        if (isNullOrBlank(username)) {
            throw new BusinessException("Username cannot be blank");
        }
        if (isNullOrBlank(password)) {
            throw new BusinessException("Password cannot be blank");
        }
    }

    private void validateEmail(String email) {
        if (isNullOrBlank(email)) {
            throw new BusinessException("Email cannot be null or empty");
        }
        if (!isValidEmail(email)) {
            throw new BusinessException("Invalid email format");
        }
    }

    private boolean isNullOrBlank(String input) {
        return input == null || input.isBlank();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-.]+@[\\w-.]+\\.[a-z]{2,4}$";
        return email.matches(emailRegex);
    }

    public List<UserEntity> getAllClients() {
        return userDao.getAllClients();
    }
}
