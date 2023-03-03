package com.example.iq_test.controllers;

import com.example.iq_test.models.SectionTest;
import com.example.iq_test.models.Test;
import com.example.iq_test.repositories.SectionTestRepository;
import com.example.iq_test.repositories.TestRepository;
import com.example.iq_test.services.SectionTestService;
import com.example.iq_test.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class TestsController {

    @Autowired
    private TestService testService;
    @Autowired
    private SectionTestService sectionTestService;


    @GetMapping("/test-sections/{id_test_section}")
    public String testSectionSelect(@PathVariable(value = "id_test_section") long Id_section, Model model)
    {
        Iterable<Test> tests = testService.findTestsBySection(sectionTestService.findSectionById(Id_section));
        model.addAttribute("tests", tests);
        model.addAttribute("sectionTest", sectionTestService.findSectionById(Id_section));
        return "test/test-page";
    }


    @GetMapping("/test-sections/{id_test_section}/add-test")
    public String addGetTest(@PathVariable(value = "id_test_section") long idSection,  Model model)
    {
        SectionTest sectionTest = sectionTestService.findSectionById(idSection);
        model.addAttribute("section", sectionTest);
        return "test/test-add";
    }


    @PostMapping("/test-sections/{id_test_section}/add-test")
    public String addTest(@PathVariable(value = "id_test_section") long idSection,
                              @RequestParam String testName)
    {
        testService.addNewTestToSection(sectionTestService.findSectionById(idSection), testName);
        return "redirect:/test-sections/{id_test_section}";
    }


    @GetMapping("/test-sections/{id_test_section}/{id_test}/edit")
    public String getTest(@PathVariable(value = "id_test") long idTest,
                          Model model)
    {
        Test test = testService.findTestById(idTest);
        model.addAttribute("test", test);
        return "test/test-edit";
    }

    @PostMapping("/test-sections/{id_test_section}/{id_test}/edit")
    public String editTest(@PathVariable(value = "id_test") long idTest,
                           @RequestParam String testName)
    {
        testService.updateTestName(idTest, testName);
        return "redirect:/test-sections/{id_test_section}";
    }


    @PostMapping("/test-sections/{id_test_section}/{id_test}/remove")
    public String deleteTest(@PathVariable(value = "id_test") long idTest)
    {
        testService.deleteTestById(idTest);
        return "redirect:/test-sections/{id_test_section}";
    }


}
