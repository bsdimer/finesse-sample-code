package org.lanadvice.repository;

import org.lanadvice.model.Questionnaire;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface QuestionnaireRepository extends PagingAndSortingRepository<Questionnaire, Long> {
}
