package org.lanadvice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Questionnaire {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="qrSeq")
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Question> questions = new ArrayList<>();
}
