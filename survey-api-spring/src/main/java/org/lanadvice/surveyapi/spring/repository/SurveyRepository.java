package org.lanadvice.surveyapi.spring.repository;

import org.lanadvice.surveyapi.spring.model.Survey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SurveyRepository extends PagingAndSortingRepository<Survey, Long> {
    List<Survey> findByCreatedBetween(LocalDateTime periodStart, LocalDateTime periodEnd);
}
