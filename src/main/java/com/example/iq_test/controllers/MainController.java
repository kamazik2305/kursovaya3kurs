package com.example.iq_test.controllers;

import com.example.iq_test.models.SectionTest;
import com.example.iq_test.models.Test;
import com.example.iq_test.services.SectionTestService;
import com.example.iq_test.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private SectionTestService sectionTestService;
    @Autowired
    private TestService testService;
    

    //обработка URL адреса
    @GetMapping("/")
    public String greeting(Model model) {
        return "home/homepage";
    }

    @PostMapping("/")
    public String searchResult(@RequestParam String searchString, Model model)
    {

        List<SectionTest> sectionTests = sectionTestService.findTestSectionsBySearchString(searchString);
        model.addAttribute("sectionTests", sectionTests);

        List<Test> tests = testService.findTestsBySearchString(searchString);
        model.addAttribute("tests",tests);

        String title ="Результаты поиска:";
        model.addAttribute("searchTitle", title);
        return "home/search-result";
    }


}
