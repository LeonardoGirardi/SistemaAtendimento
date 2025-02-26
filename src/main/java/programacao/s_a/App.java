package programacao.s_a;

import javafx.application.Application;
import javafx.stage.Stage;
import programacao.s_a.models.service.AdminInitializer;
import programacao.s_a.views.ViewFactory;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        initializeDatabase();
        ViewFactory viewFactory = ViewFactory.getInstance(stage);
        viewFactory.showLoginWindow();
    }

    private void initializeDatabase() {
        AdminInitializer initializer = new AdminInitializer();
        initializer.initializeAdmin();
    }

    public static void main(String[] args) {
        launch();
    }
}