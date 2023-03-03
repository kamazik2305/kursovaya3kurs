package com.example.iq_test.repositories;

import com.example.iq_test.models.AnswerVersion;
import com.example.iq_test.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface AnswerVersionRepository extends CrudRepository<AnswerVersion, Long> {
    ArrayList<AnswerVersion> findAllByQuestion(Question  question);
    Optional<AnswerVersion> findByTextAnswerAndQuestion(@Param("inputAnswer") String inputAnswer, Question question);
    AnswerVersion findByQuestion(Question question);
}
