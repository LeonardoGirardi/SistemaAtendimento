package programacao.s_a.controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import programacao.s_a.models.entities.FaqEntity;
import programacao.s_a.models.entities.UserEntity;
import programacao.s_a.models.service.FaqService;
import programacao.s_a.models.service.SessionService;
import programacao.s_a.views.FaqCellFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class AdminFaqController implements Initializable {

    @FXML
    public Button createFaqButton;
    @FXML
    private ListView<FaqEntity> faqListView;
    @FXML
    private TextField questionField;
    @FXML
    private TextArea answerField;

    private final FaqService faqService = new FaqService();
    UserEntity Admin = SessionService.getInstance().getLoggedUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        faqListView.setSelectionModel(null);
        createFaqButton.setOnAction(event -> handleCreateFaq());
        loadFaqs();
    }

    private void loadFaqs() {
        List<FaqEntity> faqs = faqService.listAllFaqs();
        faqListView.getItems().setAll(faqs);
        faqListView.setCellFactory(param -> new FaqCellFactory());
    }

    @FXML
    private void handleCreateFaq() {
        String question = questionField.getText();
        String answer = answerField.getText();

        if (!question.isEmpty() && !answer.isEmpty()) {
            FaqEntity newFaq = new FaqEntity();
            newFaq.setCreatedBy(Admin);
            newFaq.setQuestion(question);
            newFaq.setAnswer(answer);

            faqService.createFaq(newFaq);
            loadFaqs();
            questionField.clear();
            answerField.clear();
        }
    }
}