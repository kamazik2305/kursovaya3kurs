package com.example.iq_test.controllers;

import com.example.iq_test.models.User;
import com.example.iq_test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("userForm", new User());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String addUser(Model model, @ModelAttribute("userForm") User userForm)
    {

        if(!userForm.getPassword().equals(userForm.getPasswordConfirm()))
        {
            model.addAttribute("passwordError","Пароли не совпадают");
            return "auth/registration";
        }

        if(!userService.saveUser(userForm))
        {
            model.addAttribute("userExists", "Пользователь с таким именем уже существует");
            return "auth/registration";
        }
        return "redirect:/";
    }
}
