package com.example.iq_test.repositories;

import com.example.iq_test.models.QuestionType;
import org.springframework.data.repository.CrudRepository;

public interface QuestionTypeRepository extends CrudRepository<QuestionType, Long> {
    QuestionType findByTypeDescription(String typeDescription);
    QuestionType findById(long id);
}
