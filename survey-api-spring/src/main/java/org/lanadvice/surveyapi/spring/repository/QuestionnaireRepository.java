package org.lanadvice.surveyapi.spring.repository;


import org.lanadvice.surveyapi.spring.model.Questionnaire;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionnaireRepository extends PagingAndSortingRepository<Questionnaire, Long> {
}
