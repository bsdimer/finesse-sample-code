package org.lanadvice.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.lanadvice.model.Question;
import org.lanadvice.model.Questionnaire;
import org.lanadvice.model.Survey;
import org.lanadvice.repository.QuestionRepository;
import org.lanadvice.repository.QuestionnaireRepository;
import org.lanadvice.repository.SurveyRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class QuestionnaireService {

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    QuestionRepository questionRepository;

    @Inject
    SurveyRepository surveyRepository;

    @Transactional
    public Questionnaire create(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
        return questionnaire;
    }

    public List<Questionnaire> findAll() {
        return (List<Questionnaire>) questionnaireRepository.findAll();
    }

    @Transactional
    public Survey addSurvey(String clid, String answer, Long question) {
        Survey survey = new Survey();
        survey.setSource(clid);
        survey.setAnswer(answer);
        questionRepository.findById(question).ifPresent(survey::setQuestion);
        surveyRepository.save(survey);
        return survey;
    }
}
