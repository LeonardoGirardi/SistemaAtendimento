package programacao.s_a.Models.Domain;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class User {
    UUID id;

    String username;

    String password;

    String email;

    Date createAt;

    Boolean active;

    public User(UUID uuid, String janeSmith, String password456, String mail, Date date, boolean b) {
    }
}







