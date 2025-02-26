package programacao.s_a.controllers.general;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import programacao.s_a.models.entities.AttachmentEntity;
import programacao.s_a.models.entities.InteractionEntity;
import programacao.s_a.models.entities.TicketEntity;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.models.service.SessionService;
import programacao.s_a.models.service.TicketService;
import programacao.s_a.views.MessageCellFactory;
import programacao.s_a.views.TicketStatus;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;


public class InteractionViewController implements Initializable {
    public VBox interactionsContainer;
    @FXML
    private Button attachButton;
    @FXML
    private Button viewAttachButton;
    @FXML
    private ListView<InteractionEntity> interactionsListView;
    @FXML
    private TextField messageInput;
    @FXML
    private Button sendButton;

    private final TicketService ticketService = new TicketService();
    public TicketEntity currentTicket;
    private UserEntity currentUser = SessionService.getInstance().getLoggedUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendButton.setOnAction(event -> sendMessage());
        attachButton.setOnAction(event -> handleAttachFile());
        viewAttachButton.setOnAction(event -> handleViewAttachments());
    }

    private void handleViewAttachments() {
        if (currentTicket != null) {
            List<AttachmentEntity> attachments = ticketService.getAttachmentsForTicket(currentTicket.getId());
            if (!attachments.isEmpty()) {
                byte[] pdfData = attachments.getFirst().getFileData();
                openPDFInBrowser(pdfData);
            }
        }
    }

    private void openPDFInBrowser(byte[] pdfData) {
        try {

            Path tempFile = Files.createTempFile("tempPDF", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile.toFile())) {
                fos.write(pdfData);
            }
            Desktop.getDesktop().open(tempFile.toFile());
            tempFile.toFile().deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTicket(TicketEntity ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket não pode ser nulo.");
        }
        this.currentTicket = ticket;
        loadTicket(currentTicket, currentUser);
    }

    public void loadTicket(TicketEntity ticket, UserEntity user) {
        this.currentTicket = ticket;
        this.currentUser = user;

        System.out.println("interactionsListView: " + interactionsListView); // Verifica se não é null
        if (interactionsListView == null) {
            return;
        }

        List<InteractionEntity> interactions = ticketService.getInteractionsForTicket(ticket.getId());
        System.out.println("Interações carregadas: " + interactions.size());

        for (InteractionEntity interaction : interactions) {
            System.out.println("Mensagem: " + interaction.getMessage());
        }

        interactionsListView.getItems().clear();
        interactionsListView.getItems().addAll(interactions);
        interactionsListView.setCellFactory(param -> new MessageCellFactory());;
    }

    private void sendMessage() {
        String messageText = messageInput.getText().trim();
        if (!messageText.isEmpty() && currentTicket != null && currentUser != null) {
            InteractionEntity interaction = new InteractionEntity();
            interaction.setTicket(currentTicket);
            interaction.setSentByUser(currentUser);
            interaction.setMessage(messageText);
            interaction.setCreatedBy(currentUser);
            ticketService.addInteractionToTicket(currentTicket.getId(), interaction);
            interactionsListView.getItems().add(interaction);
            messageInput.clear();
        }
    }

    private void handleAttachFile() {
    }

    public void setView(TicketEntity ticket) {
        if( ticket.getStatus() == TicketStatus.CLOSED || ticket.getStatus() == TicketStatus.CANCELLED) {
            messageInput.setVisible(false);
            attachButton.setVisible(false);
            sendButton.setVisible(false);
        }
    }
}