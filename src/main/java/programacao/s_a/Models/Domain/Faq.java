package programacao.s_a.Models.Domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Faq {

    private UUID id;

    private String title;

    private String description;
}
