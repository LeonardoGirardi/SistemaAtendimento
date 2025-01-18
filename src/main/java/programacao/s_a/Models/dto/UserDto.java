package programacao.s_a.Models.dto;

import lombok.Data;

import java.util.Date;


@Data
public class UserDto {
    private String username;
    private String email;
    private Date createAt;
    private boolean active;

    public UserDto(String username, String email, Date createAt, boolean active) {
        this.username = username;
        this.email = email;
        this.createAt = createAt;
        this.active = active;
    }
}

