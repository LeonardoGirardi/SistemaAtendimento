package programacao.s_a.controllers.general;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardViewController implements Initializable {
    @FXML
    public BorderPane basePane;

    @FXML
    public Pane centerContainer;

    @FXML
    public AnchorPane leftContainer;

    public DashboardViewController() {}

    @Setter
    @Getter
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setLeftView(Node view) {
        Platform.runLater(() -> {
            leftContainer.getChildren().clear();
            leftContainer.getChildren().add(view);
        });
    }

    public void setCenterView(Node view) {
        Platform.runLater(() -> {
            centerContainer.getChildren().clear();
            centerContainer.getChildren().add(view);
        });
    }
}