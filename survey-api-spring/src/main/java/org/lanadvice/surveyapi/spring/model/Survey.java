package org.lanadvice.surveyapi.spring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qrSeq")
    Long id;
    @OneToOne
    Question question;
    String source;
    String answer;
    @JsonFormat
    LocalDateTime created = LocalDateTime.now();
}
