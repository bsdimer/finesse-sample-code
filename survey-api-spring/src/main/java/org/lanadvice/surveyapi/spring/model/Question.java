package org.lanadvice.surveyapi.spring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qSeq")
    private Long id;
    private String text;
    @JsonBackReference
    @ManyToOne
    private Questionnaire questionnaire;

}
