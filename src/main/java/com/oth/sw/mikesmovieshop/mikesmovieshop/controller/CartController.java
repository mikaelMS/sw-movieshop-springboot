package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Order;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.CartServiceIF;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.MovieServiceIF;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.CartItem;
import com.oth.sw.mikesmovieshop.mikesmovieshop.service.OrderService;
import com.oth.sw.mikesmovieshop.mikesmovieshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private MovieServiceIF movieService;

    @Autowired
    private CartServiceIF cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

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
    public String checkOut(Model model, @AuthenticationPrincipal UserDetails user) {
        // check if user is logged in
        if(user == null) {
            return "redirect:/login";
        } else {
            System.out.println("LNkl" + user.getUsername());
            System.out.println("hwoe");
            // get all cart items and total
            ArrayList<CartItem> boughtItems = cartService.getAllProducts();
            Double total = cartService.getTotal();

            // create new order
            Order createdOrder = orderService.saveOrder(new Order(boughtItems, total));

            // find currently logged in user and set his orders
            User updatedUser = userService.findUser(user.getUsername());
            updatedUser.setOrders(createdOrder);

            // persist everything
            userService.saveUser(updatedUser);
            cartService.clearCart();

            model.addAttribute("success", true);
            return viewCart(model);
        }
    }
}
