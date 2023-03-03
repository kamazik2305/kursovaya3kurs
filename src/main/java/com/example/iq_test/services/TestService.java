package com.example.iq_test.services;

import com.example.iq_test.models.SectionTest;
import com.example.iq_test.models.Test;
import com.example.iq_test.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public  Test findTestById(long idTest)
    {
        return testRepository.findById(idTest).orElseThrow();
    }

    public List<Test> findTestsBySearchString(String searchString)
    {
        return testRepository.findAllByTestNameContains(searchString);
    }

    public Iterable<Test> findTestsBySection(SectionTest sectionTest)
    {
        return testRepository.findAllBySectionTest(sectionTest);
    }

    public void addNewTestToSection(SectionTest sectionTest, String testName)
    {
        Test test = new Test(sectionTest, testName);
        testRepository.save(test);
    }

    public void updateTestName(long idTest, String testName)
    {
        Test test = testRepository.findById(idTest).orElseThrow();
        test.setTestName(testName);
        testRepository.save(test);
    }

    public void deleteTestById(long idTest)
    {
        testRepository.deleteById(idTest);
    }
}
