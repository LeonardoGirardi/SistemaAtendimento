package programacao.s_a.models.service;

import programacao.s_a.models.dao.FaqDao;
import programacao.s_a.models.entities.FaqEntity;

import java.util.List;

public class FaqService {
    private final FaqDao faqDao;

    public FaqService() {
        this.faqDao = new FaqDao();
    }

    public void createFaq(FaqEntity faq) {
        if (faq == null || faq.getQuestion() == null || faq.getAnswer() == null) {
            throw new IllegalArgumentException("Pergunta e resposta são obrigatórias.");
        }
        faqDao.save(faq);
    }

    public List<FaqEntity> listAllFaqs() {
        return faqDao.findAll();
    }
}
