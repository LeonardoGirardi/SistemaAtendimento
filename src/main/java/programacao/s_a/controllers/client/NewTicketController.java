package programacao.s_a.controllers.client;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import programacao.s_a.models.entities.AttachmentEntity;
import programacao.s_a.models.entities.TicketEntity;
import programacao.s_a.models.service.TicketService;
import programacao.s_a.views.ViewFactory;
import programacao.s_a.views.TicketStatus;

public class NewTicketController implements Initializable {

    @FXML
    private TextField subjectField;

    @FXML
    private TextArea messageArea;

    @FXML
    private Button selectFileBtn;

    @FXML
    private Label selectedFileLbl;

    @FXML
    private Button createTicketBtn;

    @FXML
    private Label dropAreaLabel;

    @FXML
    private javafx.scene.layout.AnchorPane dropArea;

    private File selectedFile;

    private final TicketService ticketService = new TicketService();

    public NewTicketController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectFileBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecione um arquivo (PDF ou PNG)");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos PDF ou PNG", "*.pdf", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(selectFileBtn.getScene().getWindow());
            if (file != null) {
                selectedFile = file;
                selectedFileLbl.setText(selectedFile.getName());
            } else {
                selectedFileLbl.setText("Nenhum arquivo selecionado");
            }
        });

        // Configuração da área de drag and drop
        dropArea.setOnDragOver(this::handleDragOver);
        dropArea.setOnDragDropped(this::handleDragDropped);

        // Ação para abrir (criar) o Ticket
        createTicketBtn.setOnAction(event -> {
            String subject = subjectField.getText();
            String message = messageArea.getText();

            if (subject == null || subject.isEmpty() || message == null || message.isEmpty()) {
                System.out.println("Assunto e mensagem são obrigatórios!");
                return;
            }

            // Cria novo TicketEntity
            TicketEntity ticket = new TicketEntity();
            ticket.setSubject(subject);
            ticket.setDescription(message);
            ticket.setStatus(TicketStatus.OPEN);
            ticket.setCreatedBy(ViewFactory.getInstance().getLoggedUser());

            // Cria o ticket via TicketService
            ticketService.createTicket(ticket);

            // Se houver arquivo selecionado ou arrastado, anexa-o
            if (selectedFile != null) {
                try {
                    AttachmentEntity attachment = new AttachmentEntity();
                    attachment.setFilename(selectedFile.getName());
                    // Lê o conteúdo do arquivo e define no attachment
                    attachment.setFileData(Files.readAllBytes(selectedFile.toPath()));
                    // Define o usuário que criou o anexo
                    attachment.setCreatedBy(ViewFactory.getInstance().getLoggedUser());

                    ticketService.addAttachmentToTicket(ticket.getId(), attachment);
                } catch (IOException e) {
                    System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                }
            }

            System.out.println("Ticket criado com sucesso!");

            // Opcional: limpar os campos e resetar a área de anexo
            subjectField.clear();
            messageArea.clear();
            selectedFileLbl.setText("Nenhum arquivo selecionado");
            selectedFile = null;
            dropAreaLabel.setText("Arraste o arquivo para cá");
        });
    }

    // Handle Drag and Drop Method
    private void handleDragOver(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasFiles() && isValidFile(db.getFiles().get(0))) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            File file = db.getFiles().getFirst();
            if (isValidFile(file)) {
                selectedFile = file;
                selectedFileLbl.setText(selectedFile.getName());
                dropAreaLabel.setText("Arquivo selecionado: " + selectedFile.getName());
                success = true;
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    // Valida se o arquivo é PDF ou PNG
    private boolean isValidFile(File file) {
        String lowerName = file.getName().toLowerCase();
        return lowerName.endsWith(".pdf") || lowerName.endsWith(".png");
    }
}