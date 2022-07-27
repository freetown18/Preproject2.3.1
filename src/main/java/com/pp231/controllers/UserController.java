package com.pp231.controllers;

import com.pp231.models.User;
import com.pp231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAll(Model model){
        model.addAttribute("allUsers", userService.getAll());
        return "users/all-users";
    }

    @GetMapping("/new")
    public String showAddNewForm(Model model){
        model.addAttribute("new_user", new User());
        return "users/user-info";
    }

    @PostMapping("/saveUser")
    public String saveNew(@ModelAttribute("new_user") User user, BindingResult result){
        if (result.hasErrors()) {
            return "users/user-info";
        }
        userService.saveNew(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "users/update-user";
    }

    @PatchMapping("/{id}/update")
    public String update(@PathVariable("id") int id, User user, BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id);
            return "users/update-user";
        }
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
