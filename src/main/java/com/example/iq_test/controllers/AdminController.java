package com.example.iq_test.controllers;

import com.example.iq_test.models.ChildGroup;
import com.example.iq_test.models.User;
import com.example.iq_test.services.GroupService;
import com.example.iq_test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;

    @GetMapping("/admin")
    public String getAdminPage()
    {
        return "admin-profile";
    }

    @GetMapping("/admin/groups")
    public String getAllGroups(Model model)
    {
        Iterable<ChildGroup> childGroups = groupService.fillAllGroups();
        model.addAttribute("childGroups", childGroups);
        return "children-groups";
    }

    @GetMapping("/admin/users")
    public String getAllUsers(Model model)
    {
        model.addAttribute("users", userService.findUndefinedUsers());
        model.addAttribute("children", userService.findUserChild());
        model.addAttribute("teachers", userService.findUserTeacher());
        return "users";
    }

    @PostMapping(value = "/admin/users", params = {"buttonTeacher"})
    public String setRoleTeacher(@RequestParam(value = "buttonTeacher") String buttonTeacher)
    {
        User user = userService.findUserByName(buttonTeacher);
        userService.setRoleTeacher(user);
        return "redirect:/admin/users";
    }

    @PostMapping(value = "/admin/users", params = {"buttonChild"})
    public String setRoleChild(@RequestParam(value = "buttonChild") String buttonChild)
    {
        User user = userService.findUserByName(buttonChild);
        userService.setRoleChild(user);
        return "redirect:/admin/users";
    }

    @PostMapping(value = "/admin/users", params = {"buttonUser"})
    public String setRoleUser(@RequestParam(value = "buttonUser") String buttonUser)
    {
        User user = userService.findUserByName(buttonUser);
        userService.setRoleUserOnly(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/{id_user}/remove")
    public String deleteUser(@PathVariable(value = "id_user") long idUser)
    {
        userService.deleteUserById(idUser);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/teachers")
    public String getAllTeachers(Model model)
    {
        model.addAttribute("teachers", userService.findUserTeacher());
        return "teachers";
    }

}
