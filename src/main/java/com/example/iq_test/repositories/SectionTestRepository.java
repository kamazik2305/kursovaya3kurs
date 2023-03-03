package com.example.iq_test.repositories;

import com.example.iq_test.models.SectionTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionTestRepository extends CrudRepository<SectionTest, Long> {
    List<SectionTest> findAllByTestSectionTitleContains(@Param("searchString") String searchString);
}
