package programacao.s_a.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;
import programacao.s_a.controllers.admin.AdminFaqController;
import programacao.s_a.controllers.admin.AdminListViewController;
import programacao.s_a.controllers.admin.AdminTicketListController;
import programacao.s_a.controllers.client.ClientFaqController;
import programacao.s_a.controllers.client.ClientListViewController;
import programacao.s_a.controllers.general.DashboardDefaultViewController;
import programacao.s_a.controllers.general.DashboardViewController;
import programacao.s_a.controllers.general.InteractionViewController;
import programacao.s_a.models.entities.TicketEntity;
import programacao.s_a.models.entities.UserEntity;

import java.io.IOException;
import java.util.Arrays;

@Data
public class ViewFactory {
    private static ViewFactory instance;
    private final Stage stage;
    private DashboardViewController dashboard;
    private ClientListViewController client;
    private UserEntity loggedUser;

    private ViewFactory(Stage stage) {
        this.stage = stage;
    }

    public static ViewFactory getInstance(Stage stage) {
        if (instance == null) {
            instance = new ViewFactory(stage);
        }
        return instance;
    }

    public static ViewFactory getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ViewFactory ainda não foi inicializada. Chame getInstance(Stage) primeiro.");
        }
        return instance;
    }

    private FXMLView loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        return new FXMLView(root, loader.getController());
    }

    private void loadAndSetScene(String fxmlPath) throws IOException {
        FXMLView view = loadFXML(fxmlPath);
        Scene scene = new Scene(view.getRoot());
        stage.setScene(scene);
        stage.show();
    }

    //////////////////// App Views //////////////////

    public void showCreateAccountWindow() throws IOException {
        loadAndSetScene("/fxml/client/CreateAccount.fxml");
    }

    public Node showDefaultDashboardView(UserEntity user) throws IOException {
        FXMLView defaultView = loadFXML("/fxml/general/DashboardDefaultView.fxml");
        DashboardDefaultViewController dashboardDefault = defaultView.getController();
        dashboardDefault.setUserName(user.getUsername());
        return defaultView.getRoot();
    }


    public void showDashboardView(UserEntity user) throws IOException {
        loggedUser = user;
        FXMLView dashboardView = loadFXML("/fxml/general/DashboardView.fxml");
        dashboard = dashboardView.getController();

        Scene scene = new Scene(dashboardView.getRoot());
        stage.setScene(scene);
        stage.show();

        if (user.getAccountType().equals(AccountType.CLIENT)) {
            dashboard.setLeftView(loadFXML("/fxml/client/ClientMenu.fxml").getRoot());
        } else if (user.getAccountType().equals(AccountType.ADMIN)) {
            dashboard.setLeftView(loadFXML("/fxml/admin/AdminMenu.fxml").getRoot());
        }
        dashboard.setCenterView(showDefaultDashboardView(user));
    }

    public void closeCurrentStage() throws IOException {
        stage.close();
        showLoginWindow();
    }

    public void showLoginWindow() throws IOException {
        loadAndSetScene("/fxml/Login.fxml");
    }

   // Admin List of Clients methods

    public Node loadClientsList() throws IOException {
        FXMLView listView = loadFXML("/fxml/admin/AdminListView.fxml");
        AdminListViewController admin = listView.getController();
        admin.titleOfList.setText("Lista de Clientes");
        return listView.getRoot();
    }


    public void showListOfClients() throws IOException {
        if (dashboard == null) {
            throw new IllegalStateException("Dashboard not initialized!");
        }
        dashboard.setCenterView(loadClientsList());
    }

    // Admin list of Tickets methods

    public Node loadOpenTicketList() throws IOException {
        FXMLView listView = loadFXML("/fxml/admin/AdminTicketListView.fxml");
        AdminTicketListController controller = listView.getController();
        controller.titleOfList.setText("Lista de Tickets: Abertos e Em Andamento");

        controller.setFilterStatuses(Arrays.asList(TicketStatus.OPEN, TicketStatus.IN_PROGRESS));
        return listView.getRoot();
    }

    public Node loadClosedTicketList() throws IOException {
        FXMLView listView = loadFXML("/fxml/admin/AdminTicketListView.fxml");
        AdminTicketListController controller = listView.getController();
        controller.titleOfList.setText("Lista de Tickets: Fechados e Cancelados");
        controller.setFilterStatuses(Arrays.asList(TicketStatus.CLOSED, TicketStatus.CANCELLED));
        return listView.getRoot();
    }

    public void showOpenedTicketListForAdmin() throws IOException {
        if (dashboard == null) {
            throw new IllegalStateException("Dashboard not initialized!");
        }
        dashboard.setCenterView(loadOpenTicketList());
    }

    public void showClosedTicketListForAdmin() throws IOException {
        if (dashboard == null) {
            throw new IllegalStateException("Dashboard not initialized!");
        }
        dashboard.setCenterView(loadClosedTicketList());
    }

    // Clients Methods

    // Create NewTicket Methods

    public Node loadNewTicketView() throws IOException {
        FXMLView ticketView = loadFXML("/fxml/client/NewTicket.fxml");
        return ticketView.getRoot();
    }

    public void showNewTicketView() throws IOException {
        if (dashboard == null) {
            throw new IllegalStateException("Dashboard not initialized!");
        }
        dashboard.setCenterView(loadNewTicketView());
    }

    /*
                List Client Tickets Methods
     */

    public Node loadOpenTickets() throws IOException {
        FXMLView listView = loadFXML("/fxml/client/ClientListView.fxml");
        ClientListViewController controller = listView.getController();
        controller.titleOfList.setText("Lista de Tickets: Abertos e Em Andamento");

        controller.setFilterStatuses(Arrays.asList(TicketStatus.OPEN, TicketStatus.IN_PROGRESS));
        return listView.getRoot();
    }

    public Node loadClosedTickets() throws IOException {
        FXMLView listView =loadFXML("/fxml/client/ClientListView.fxml");
        ClientListViewController controller = listView.getController();
        controller.titleOfList.setText("Lista de Tickets: Fechados e Cancelados");
        controller.setFilterStatuses(Arrays.asList(TicketStatus.CLOSED, TicketStatus.CANCELLED));
        return listView.getRoot();
    }

    public void showOpenedTicketListForClient() throws IOException {
        if (dashboard == null) {
            throw new IllegalStateException("Dashboard not initialized!");
        }
        dashboard.setCenterView(loadOpenTickets());
    }

    public void showClosedTicketListForClient() throws IOException {
        if (dashboard == null) {
            throw new IllegalStateException("Dashboard not initialized!");
        }
        dashboard.setCenterView(loadClosedTickets());
    }

    // Faq View Methods

    public Node loadAdminFaq() throws IOException {
        FXMLView faqView = loadFXML("/fxml/admin/AdminFaq.fxml");
        AdminFaqController controller = faqView.getController();
        return faqView.getRoot();
    }

    public Node loadClientFaq() throws IOException {
        FXMLView faqView = loadFXML("/fxml/client/ClientFaq.fxml");
        ClientFaqController controller = faqView.getController();
        return faqView.getRoot();
    }

    public void showFaqForAdmin() throws IOException {
        dashboard.setCenterView(loadAdminFaq());
    }

    public void showFaqForClient() throws IOException {
        dashboard.setCenterView(loadClientFaq());
    }

    // Ticket Interactions And Details

    public void showTicketDetails(TicketEntity ticket) throws IOException {
        FXMLView ticketView = loadFXML("/fxml/general/InteractionView.fxml");
        InteractionViewController controller = ticketView.getController();
        controller.setTicket(ticket);

        controller.setView(ticket);

        dashboard.setCenterView(ticketView.getRoot());
    }
}

