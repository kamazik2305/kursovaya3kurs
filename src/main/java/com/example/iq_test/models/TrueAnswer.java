package com.example.iq_test.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "True_answer")
@Getter
@Setter
@NoArgsConstructor
public class TrueAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_true_answer")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_answer_version")
    private AnswerVersion answerVersions;

    @ManyToOne
    @JoinColumn(name = "Id_question")
    private Question question;

    public TrueAnswer(AnswerVersion answerVersion, Question question)
    {
        this.answerVersions = answerVersion;
        this.question = question;
    }

}
