package com.example.iq_test.services;

import com.example.iq_test.models.*;
import com.example.iq_test.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private final TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerVersionRepository answerVersionRepository;
    @Autowired
    private TrueAnswerRepository trueAnswerRepository;
    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    public QuestionService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Question findQuestionById(long idQuestion)
    {
        return  questionRepository.findById(idQuestion).orElseThrow();
    }

    public ArrayList<Question> getQuestionList(long id_test)
    {
        Optional<Test> test = testRepository.findById(id_test);
        return questionRepository.findAllBytest(test);
    }

    public ArrayList<AnswerVersion> findAnswerVersions(Question question)
    {
        return answerVersionRepository.findAllByQuestion(question);
    }

    public TrueAnswer checkQuestionType1 (Long buttonAnswer)
    {
        Optional<AnswerVersion> answerVersionSelected = answerVersionRepository.findById(buttonAnswer);
        return trueAnswerRepository.findByAnswerVersions(answerVersionSelected);
    }

    public  TrueAnswer checkQuestionType2 (String inputAnswer, Question question)
    {
        Optional<AnswerVersion> answerVersionSelected = answerVersionRepository.findByTextAnswerAndQuestion(inputAnswer,question);
        return trueAnswerRepository.findByAnswerVersions(answerVersionSelected);
    }

    public boolean checkQuestionType3 (String[] checkBoxAnswer, Question question)
    {
        boolean ok = true;
        ArrayList<TrueAnswer> trueAnswers = trueAnswerRepository.findAllByQuestion(question);
        if(checkBoxAnswer.length == trueAnswers.size())
        {
            int i =0;
            while(ok && i < checkBoxAnswer.length)
            {
                Optional<AnswerVersion> answerVersionSelected = answerVersionRepository.findByTextAnswerAndQuestion(checkBoxAnswer[i], question);
                TrueAnswer trueAnswer = trueAnswerRepository.findByAnswerVersions(answerVersionSelected);
                if (trueAnswer == null)
                    ok = false;
                i++;
            }
        }
        else ok = false;
        return ok;
    }

    public void saveQuestion(Question question)
    {
        questionRepository.save(question);
    }

    public Question addNewQuestion(long idTest, long idTypeQuestion,String questionText)
    {
        Test test = testRepository.findById(idTest).orElseThrow();
        QuestionType questionType = questionTypeRepository.findById(idTypeQuestion);
        return new Question(questionText, questionType, test);
    }

    public void setAnswersToQuestion(String[] textAnswer, Question question)
    {
        for(String s : textAnswer)
        {
            if(!Objects.equals(s.replaceAll("( +)"," ").trim(), ""))
            {
                AnswerVersion answerVersion = new AnswerVersion(s, question);
                answerVersionRepository.save(answerVersion);
            }
        }
    }

    public  void addTrueAnswerType1( String textTrueAnswer, Question question)
    {
        AnswerVersion answerVersion = answerVersionRepository.findByTextAnswerAndQuestion(textTrueAnswer, question).orElseThrow();
        TrueAnswer trueAnswer = new TrueAnswer(answerVersion, question);
        trueAnswerRepository.save(trueAnswer);
    }

    public  void addTrueAnswerType23(String[] textTrueAnswer, Question question)
    {
        for(String s : textTrueAnswer)
        {
            if(!Objects.equals(s.replaceAll("( +)"," ").trim(), ""))
            {
                AnswerVersion answerVersion = answerVersionRepository.findByTextAnswerAndQuestion(s, question).orElseThrow();
                TrueAnswer trueAnswer = new TrueAnswer(answerVersion, question);
                trueAnswerRepository.save(trueAnswer);
            }
        }
    }

    public void deleteQuestionById(long idQuestion)
    {
        questionRepository.deleteById(idQuestion);
    }

    public  Iterable<QuestionType> findAllQuestionTypes()
    {
        return questionTypeRepository.findAll();
    }

}
