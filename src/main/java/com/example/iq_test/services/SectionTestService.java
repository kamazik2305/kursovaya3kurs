package com.example.iq_test.services;

import com.example.iq_test.models.SectionTest;
import com.example.iq_test.repositories.SectionTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionTestService {

    @Autowired
    SectionTestRepository sectionTestRepository;

    public Iterable<SectionTest> findAllTestSections()
    {
        return  sectionTestRepository.findAll();
    }

    public List<SectionTest> findTestSectionsBySearchString(String searchString)
    {
        return sectionTestRepository.findAllByTestSectionTitleContains(searchString);
    }

    public void addNewSectionTest(String sectionTitle)
    {
        SectionTest sectionTest = new SectionTest(sectionTitle);
        sectionTestRepository.save(sectionTest);
    }

    public SectionTest findSectionById(long idSection)
    {
        return sectionTestRepository.findById(idSection).orElseThrow();
    }

    public void updateTestSection(long idSection,String sectionTitle)
    {
        SectionTest sectionTest = sectionTestRepository.findById(idSection).orElseThrow();
        sectionTest.setTestSectionTitle(sectionTitle);
        sectionTestRepository.save(sectionTest);
    }

    public void deleteSectionByID(long idSection)
    {
        sectionTestRepository.deleteById(idSection);
    }
}
