package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/auth/login")
    public String viewLoginForm() {
        return "sites/auth/login";
    }

    @RequestMapping("/auth/registration")
    public String viewRegistrationForm() {
        return "sites/auth/registration";
    }

    // 1. Get email
    // 2. Check db for that email
    // 3. Either successfully create customer or show fail message that email is already registered
    @PostMapping("/auth/registration")
    public String register(
            @ModelAttribute("email") String email,
            @ModelAttribute("password") String password,
            Model model
    ) {
        System.out.println("Email " + email);
        User newUser = new User(email, password);
        newUser = userService.createCustomer(newUser);

        if (!(newUser.isActive())) {
            System.out.println("Email already exists");
            return "index";
        } else {
            System.out.println("Sucessfully created user id: " + newUser.getUserId());
            model.addAttribute("user", newUser.getUserId());
            return "index";
        }
    }
}
