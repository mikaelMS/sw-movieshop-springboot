package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/cart")
    public String viewCart(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User)
            model.addAttribute("loggedIn", true);

        model.addAttribute("products", cartService.getAllProducts());
        model.addAttribute("total", cartService.getTotal());

        return "sites/cart";
    }

    @RequestMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") long id, Model model) {

        cartService.addProduct(id);

        return viewCart(model);
    }

    @RequestMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") long id, Model model) {
        cartService.removeProduct(id);

        return viewCart(model);
    }
}
