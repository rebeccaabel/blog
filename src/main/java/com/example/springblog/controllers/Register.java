package com.example.springblog.controllers;

import com.example.springblog.models.BlogUser;
import com.example.springblog.services.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Register {

    @Autowired
    private
    BlogUserService blogUserService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        BlogUser blogUser = new BlogUser();
        model.addAttribute("blogUser", blogUser);
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute BlogUser blogUser) {
        blogUserService.save(blogUser);

        return "redirect:/login";
    }
}
