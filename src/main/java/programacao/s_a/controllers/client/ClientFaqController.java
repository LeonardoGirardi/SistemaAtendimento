package programacao.s_a.controllers.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import programacao.s_a.models.entities.FaqEntity;
import programacao.s_a.models.service.FaqService;
import programacao.s_a.views.FaqCellFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class ClientFaqController implements Initializable {

    private final FaqService faqService = new FaqService();

    @FXML
    private ListView<FaqEntity> faqListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        faqListView.setSelectionModel(null);
        loadFaqs();
    }

    private void loadFaqs() {
        List<FaqEntity> faqs = faqService.listAllFaqs();
        faqListView.getItems().setAll(faqs);
        faqListView.setCellFactory(param -> new FaqCellFactory());
    }
}