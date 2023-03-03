package com.example.iq_test.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Answer_version")
public class AnswerVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_answer_version")
    private Long id;

    @Column(name = "Text_answer")
    private String textAnswer;
    
    @ManyToOne
    @JoinColumn(name = "Id_question", nullable = false)
    private Question question;

    @OneToOne(mappedBy = "answerVersions", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private TrueAnswer trueAnswers;


    public AnswerVersion(){}

    public AnswerVersion(String textAnswer, Question question)
    {
        this.textAnswer = textAnswer;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public TrueAnswer getTrueAnswers() {
        return trueAnswers;
    }

    public void setTrueAnswers(TrueAnswer trueAnswers) {
        this.trueAnswers = trueAnswers;
    }
}
