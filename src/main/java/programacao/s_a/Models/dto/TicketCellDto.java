package programacao.s_a.Models.dto;

import lombok.Data;
import programacao.s_a.Views.TicketStatus;
import java.util.Date;

@Data
public class TicketCellDto {

    private String username;
    private Date createAt;
    private TicketStatus status;

    public TicketCellDto(String username, TicketStatus Status, Date createAt) {
        this.username = username;
        this.createAt = createAt;
        this.status = Status;
    }
}


