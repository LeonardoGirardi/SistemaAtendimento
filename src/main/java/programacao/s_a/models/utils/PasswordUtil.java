package programacao.s_a.models.utils;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String providedPassword, String storedHash) {
        return BCrypt.checkpw(providedPassword, storedHash);
    }
}

