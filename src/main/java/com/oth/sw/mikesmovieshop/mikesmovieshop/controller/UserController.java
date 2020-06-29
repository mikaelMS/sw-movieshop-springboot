package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String viewLoginForm(Model model) {
        System.out.println("hier");
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        // check if user is already logged in
//        if(!(principal instanceof UserDetails)) {
//            return "sites/login";
//        } else {
//            return "redirect:/cart/checkout";
//        }
        return "sites/login";
    }

    @RequestMapping("/registration")
    public String viewRegistrationForm() {
        return "sites/registration";
    }

    @RequestMapping("/login-error")
    public String loginError(
            HttpServletRequest request,
            Model model
    ) {
        HttpSession session = request.getSession(false);
        boolean error = false;

        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                error = true;
                System.out.println(ex.toString());
            }
        }
        System.out.println("Error" + error);
        model.addAttribute("error" , error);

        return viewLoginForm(model);
    }

    @PostMapping("/registration")
    public String register(
            @ModelAttribute("email") String email,
            @ModelAttribute("password") String password,
            Model model
    ) {
        System.out.println("Email " + email);
        User newUser = new User(email);
        newUser = userService.registerCustomer(newUser, password);

        if (!(newUser.isActive())) {
            System.out.println("Email already exists");
            model.addAttribute("exists", true);
            return "sites/registration";
        } else {
            System.out.println("Sucessfully created user id: " + newUser.getUserId());
            model.addAttribute("user", newUser.getUserId());
            return "sites/login";
        }
    }
}
