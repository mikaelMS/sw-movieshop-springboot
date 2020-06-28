package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.CartServiceIF;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.MovieServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class CartController {

    @Autowired
    private MovieServiceIF movieService;

    @Autowired
    private CartServiceIF cartService;

    @RequestMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("products", cartService.getAllProducts());
        System.out.println("ION: " + cartService.getAllProducts().toString());
        model.addAttribute("total", cartService.getTotal());

        return "sites/cart";
    }

    @RequestMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") long id, Model model) {
        Movie movie = movieService.findMovie(id);
        if (movie.getAvailableStatus()) {
            cartService.addProduct(movie);
        }

        return viewCart(model);
    }

    @RequestMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") long id, Model model) {
        System.out.println("hier");
        Movie movie = movieService.findMovie(id);
        System.out.println(movie.toString());
        cartService.removeProduct(movie);

        return viewCart(model);
    }

}
