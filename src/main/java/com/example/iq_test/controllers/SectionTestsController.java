package com.example.iq_test.controllers;

import com.example.iq_test.models.SectionTest;
import com.example.iq_test.models.Test;
import com.example.iq_test.repositories.SectionTestRepository;
import com.example.iq_test.repositories.TestRepository;
import com.example.iq_test.services.SectionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SectionTestsController {

    @Autowired
    private SectionTestService sectionTestService;

    @GetMapping("/test-sections")
    public String TestsMain(Model model)
    {

        Iterable<SectionTest> sectionTests = sectionTestService.findAllTestSections();
        model.addAttribute("sectionTests", sectionTests);
        return "test-section/tests-section-page";
    }

    @GetMapping("test-sections/add")
    public String SectionTestsAdd()
    {
        return "test-section/test-section-add";
    }
    @PostMapping("test-sections/add")
    public String AddSection(@RequestParam String testSectionTitle)
    {
        sectionTestService.addNewSectionTest(testSectionTitle);
        return "redirect:/test-sections";
    }

    @GetMapping("/test-sections/{id_test_section}/edit")
    public String getSection(@PathVariable(value = "id_test_section") long idSection, Model model)
    {
        SectionTest sectionTest = sectionTestService.findSectionById(idSection);
        model.addAttribute("sectionTest" ,sectionTest);
        return "test-section/test-section-edit";
    }

    @PostMapping("/test-sections/{id_test_section}/edit")
    public String editSection(@PathVariable(value = "id_test_section") long id_section,
                              @RequestParam String testSectionTitle)
    {
        sectionTestService.updateTestSection(id_section, testSectionTitle);
        return "redirect:/test-sections";
    }

    @PostMapping("/test-sections/{id_test_section}/remove")
    public String deleteSection(@PathVariable(value = "id_test_section") long id_section)
    {
        sectionTestService.deleteSectionByID(id_section);
        return "redirect:/test-sections";
    }
}

