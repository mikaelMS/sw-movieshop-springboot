package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.config.RestTemplateBuilderConfiguration;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
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

//    @RequestMapping("/auth/login")
//    @ResponseBody
//    public String errorWhileLoginIn(@RequestParam String error) {
//        System.out.println("error" + error);
//        return "error";
//    }

//    //TODO: login Postmapping!
//    @PostMapping("/auth/login")
//    public String login(
//            @ModelAttribute("email") String email,
//            @ModelAttribute("password") String password,
//            Model model
//    ) {
//        System.out.println("Email " + email);
//        User newUser = new User(email, password);
//        newUser = userService.createCustomer(newUser);
//
//        // TODO: clean up
//        if (!(newUser.isActive())) {
//            System.out.println("Email already exists");
//            return "sites/auth/registration";
//        } else {
//            System.out.println("Sucessfully created user id: " + newUser.getUserId());
//            model.addAttribute("user", newUser.getUserId());
//            return "sites/auth/login";
//        }
//    }

    @PostMapping("/auth/registration")
    public String register(
            @ModelAttribute("email") String email,
            @ModelAttribute("password") String password,
            Model model
    ) {
        System.out.println("Email " + email);
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser = userService.registerCustomer(newUser);

        // TODO: clean up and stop zerreißen der seite bei doppelt email
        if (!(newUser.isActive())) {
            System.out.println("Email already exists");
            return "sites/auth/registration";
        } else {
            System.out.println("Sucessfully created user id: " + newUser.getUserId());
            model.addAttribute("user", newUser.getUserId());
            return "sites/auth/login";
        }
    }
}
