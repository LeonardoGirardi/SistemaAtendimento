module programacao.s_a {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires static lombok;
    requires jbcrypt;

    exports programacao.s_a.Views;
    opens programacao.s_a.Views to javafx.fxml;
    exports programacao.s_a.Controllers;
    opens programacao.s_a.Controllers to javafx.fxml;
    exports programacao.s_a;
    opens programacao.s_a to javafx.fxml;
    exports programacao.s_a.Controllers.Admin;
    opens programacao.s_a.Controllers.Admin to javafx.fxml;
    exports programacao.s_a.Controllers.Client to javafx.fxml;
    opens programacao.s_a.Controllers.Client to javafx.fxml;
    exports programacao.s_a.Controllers.General to javafx.fxml;
    opens programacao.s_a.Controllers.General to javafx.fxml;
    exports programacao.s_a.Models.Domain;
    opens programacao.s_a.Models.Domain to javafx.fxml;
    exports programacao.s_a.Models.dto;
    exports programacao.s_a.Models.Service;
}