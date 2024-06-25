package com.example.projektInz.controller;

import com.example.projektInz.model.User;
import com.example.projektInz.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
