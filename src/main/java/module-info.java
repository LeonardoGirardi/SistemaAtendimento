module programacao.s_a {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    exports programacao.s_a.Views;
    opens programacao.s_a.Views to javafx.fxml;
    exports programacao.s_a.Controllers;
    opens programacao.s_a.Controllers to javafx.fxml;
    exports programacao.s_a.Models;
    opens programacao.s_a.Models to javafx.fxml;
    exports programacao.s_a;
    opens programacao.s_a to javafx.fxml;
    exports programacao.s_a.Controllers.Agent;
    opens programacao.s_a.Controllers.Agent to javafx.fxml;
}