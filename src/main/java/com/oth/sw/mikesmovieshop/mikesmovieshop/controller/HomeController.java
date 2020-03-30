package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String start() {
        return "index";
    }

    @RequestMapping("/cart")
    public String viewCart() {
        return "sites/cart";
    }
}
