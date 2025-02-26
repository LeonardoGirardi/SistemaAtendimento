package programacao.s_a.views;

import javafx.scene.Parent;
import lombok.Getter;

public class FXMLView {
    @Getter
    private final Parent root;
    private final Object controller;

    public FXMLView(Parent root, Object controller) {
        this.root = root;
        this.controller = controller;
    }

    public <T> T getController() {
        return (T) controller;
    }
}
