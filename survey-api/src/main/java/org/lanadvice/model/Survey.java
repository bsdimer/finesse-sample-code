package org.lanadvice.model;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="qrSeq")
    private Long id;
    @OneToOne
    private Question question;
    private String source;
    private String answer;
    private LocalDateTime created = LocalDateTime.now();
}
