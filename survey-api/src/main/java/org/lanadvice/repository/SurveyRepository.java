package org.lanadvice.repository;

import org.lanadvice.model.Survey;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SurveyRepository extends PagingAndSortingRepository<Survey, Long> {
}
