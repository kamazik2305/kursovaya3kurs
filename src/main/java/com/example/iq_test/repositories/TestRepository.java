package com.example.iq_test.repositories;

import com.example.iq_test.models.SectionTest;
import com.example.iq_test.models.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TestRepository extends CrudRepository<Test, Long> {
    Iterable<Test> findAllBySectionTest(SectionTest sectionTest);
    List<Test> findAllByTestNameContains(@Param("searchString") String searchString);

}
