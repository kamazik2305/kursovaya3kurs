package com.example.iq_test.repositories;

import com.example.iq_test.models.AnswerVersion;
import com.example.iq_test.models.Question;
import com.example.iq_test.models.QuestionType;
import com.example.iq_test.models.Test;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    Question findByid(long id);
    ArrayList<Question> findAllBytest(Optional<Test> test);
    ArrayList<Question> findByQuestionType(Optional<QuestionType> questionType);
}
