package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Order;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.CartServiceIF;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.MovieServiceIF;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.CartItem;
import com.oth.sw.mikesmovieshop.mikesmovieshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class CartController {

    @Autowired
    private MovieServiceIF movieService;

    @Autowired
    private CartServiceIF cartService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("products", cartService.getAllProducts());
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
        Movie movie = movieService.findMovie(id);
        System.out.println(movie.toString());
        cartService.removeProduct(movie);

        return viewCart(model);
    }

    @RequestMapping("/cart/checkout")
    public String checkOut(Model model) {
        // check if user is logged in
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(principal instanceof UserDetails)) {
            return "redirect:/login";
        } else {
            ArrayList<CartItem> boughtItems = cartService.getAllProducts();
            Double total = cartService.getTotal();
            orderService.saveOrder(new Order(boughtItems, total));
            cartService.clearCart();
            model.addAttribute("success", true);

            return viewCart(model);
        }
    }
}
