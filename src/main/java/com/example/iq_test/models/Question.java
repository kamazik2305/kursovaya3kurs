package com.example.iq_test.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Question")
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_question")
    private Long id;

    @Column(name = "Question_text", nullable = false)
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "Id_type_question", nullable = false)
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "Id_test", nullable = false)
    private Test test;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerVersion> answerVersions;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrueAnswer> trueAnswers;

    public Question(String questionText, QuestionType questionType, Test test) {
        this.questionType = questionType;
        this.test = test;
        this.questionText = questionText;
    }

}
