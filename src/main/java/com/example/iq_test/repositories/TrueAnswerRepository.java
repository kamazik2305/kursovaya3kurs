package com.example.iq_test.repositories;

import com.example.iq_test.models.AnswerVersion;
import com.example.iq_test.models.Question;
import com.example.iq_test.models.TrueAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface TrueAnswerRepository extends CrudRepository<TrueAnswer, Long> {
    ArrayList<TrueAnswer> findAllByQuestion(Question question);
    TrueAnswer findByAnswerVersions(Optional<AnswerVersion> answerVersion);
}
