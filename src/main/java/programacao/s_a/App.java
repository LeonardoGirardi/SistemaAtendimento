package programacao.s_a;

import javafx.application.Application;

import javafx.stage.Stage;
import programacao.s_a.Views.ViewFactory;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ViewFactory viewFactory = new ViewFactory(stage);
        viewFactory.showLoginWindow();
    }
    public static void main(String[] args) {
        launch();
    }
}

