package com.pp231.controllers;

import com.pp231.models.User;
import com.pp231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showAll(Model model){
        model.addAttribute("allUsers", userService.getAll());
        return "all-users";
    }

    @RequestMapping("/addUser")
    public String showAddNewForm(Model model){
        model.addAttribute("new_user", new User());
        return "user-info";
    }

    @RequestMapping("/saveUser")
    public String saveNew(@ModelAttribute("new_user") User user, BindingResult result){
        if (result.hasErrors()) {
            return "user-info";
        }
        userService.saveNew(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") byte id, User user, BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        userService.update(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}
