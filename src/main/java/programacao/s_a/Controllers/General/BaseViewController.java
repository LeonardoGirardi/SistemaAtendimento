package programacao.s_a.Controllers.General;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import programacao.s_a.Controllers.Client.NewTicketController;
import programacao.s_a.Views.AccountType;

import java.io.IOException;

public class BaseViewController {

     private AccountType accountType;

     public BorderPane basePane;

     private FXMLLoader currentLoader;

     public void setAccountType(AccountType accountType) {
          this.accountType = accountType;
          configureUI(); // Configura a interface com base no tipo de conta
     }

     private void configureUI() {
          try {
               String fxmlPath = "/Fxml/General/Menu.fxml";
               System.out.println("Carregando menu: " + fxmlPath);

               FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
               Parent menu = loader.load();

               MenuController menuController = loader.getController();
               menuController.setBaseController(this); // Passa o controlador base
               menuController.configureMenu(accountType);

               basePane.setLeft(menu); // Insere o menu na lateral esquerda
          } catch (IOException e) {
               e.printStackTrace();
          }

          // Define a página inicial padrão

          if (accountType == AccountType.ADMIN) {
               loadView("General/ListView.fxml");
          } else {
               loadView("Client/NewTicket.fxml");
          }
     }

     public void loadView(String viewName) {
          try {
               String fxmlPath = "/Fxml/" + viewName;
               System.out.println("Carregando: " + fxmlPath); // Log para depuração

               FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
               Parent view = loader.load();
               basePane.setCenter(view);

               currentLoader = loader;

               if (viewName.equals("Client/NewTicket.fxml")) {
                    NewTicketController newTicketController = loader.getController();
                    if (newTicketController == null) {
                         System.out.println("Erro ao obter o controlador NewTicketController.");
                    } else {
                         System.out.println("Controlador NewTicketController carregado com sucesso.");
                    }
               } else if (viewName.equals("General/ListView.fxml")) {
                    ListViewController listViewController = loader.getController();
                    if (listViewController == null) {
                         System.out.println("Erro ao obter o controlador ListViewController.");
                    } else {
                         System.out.println("Controlador ListViewController carregado com sucesso.");
                    }
               }
          } catch (IOException e) {
               e.printStackTrace();
               System.out.println("Erro ao carregar a view: " + viewName);
          }
     }

     public <T> T getController() {
          return currentLoader.getController();
     }
     
}