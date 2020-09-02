package org.lanadvice.surveyapi.spring.repository;


import org.lanadvice.surveyapi.spring.model.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
