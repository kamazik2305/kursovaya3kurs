package com.example.iq_test.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Question_type")
@Getter
@Setter
@NoArgsConstructor
public class QuestionType {

    @Id
    @Column(name = "Id_question_type")
    private long id;

    @Column(name = "Type_description")
    private String typeDescription;

    @OneToMany(mappedBy = "questionType", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    public QuestionType(long id, String typeDescription, List<Question> questions) {
        this.id = id;
        typeDescription = typeDescription;
        this.questions = questions;
    }

}
