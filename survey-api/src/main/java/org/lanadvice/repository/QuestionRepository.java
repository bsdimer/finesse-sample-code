package org.lanadvice.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.lanadvice.model.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
