package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.CartItem;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Order;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Controller
public class OrderController {

    @Autowired
    private UserService userService;

    @RequestMapping("/orders")
    public String viewOrders(Model model, @AuthenticationPrincipal UserDetails user) {
        User currentUser = userService.findUser(user.getUsername());
        Collection<Order> orders = currentUser.getOrders();
        Collection<CartItem> items = null;

        for (Order order : orders) {
            items = order.getItems();
        }

        model.addAttribute("orders", orders);
        model.addAttribute("items", items);

        return "sites/orders";
    }
}
