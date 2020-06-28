package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.CartServiceIF;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.MovieServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

//    @Autowired
//    private MovieServiceIF movieService;
//
//    @Autowired
//    private CartServiceIF cartService;
//
//    @RequestMapping("/cart")
//    public String viewCart(Model model) {
//        model.addAttribute("products", cartService.getAllProducts().keySet());
//        System.out.println(cartService.getAllProducts().entrySet());
//        System.out.println(cartService.getAllProducts().keySet());
//        model.addAttribute("total", cartService.getTotal());
//        System.out.println(cartService.getTotal());
//
//        return "sites/cart";
//    }
}