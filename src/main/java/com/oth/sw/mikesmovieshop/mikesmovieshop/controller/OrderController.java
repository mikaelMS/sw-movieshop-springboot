package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.CartItem;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Order;
import com.oth.sw.mikesmovieshop.mikesmovieshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/orders")
    public String viewOrders(Model model, @AuthenticationPrincipal UserDetails user) {
        Collection<Order> orders = orderService.getUserOrders(user);
        Collection<CartItem> items = orderService.getUserOrderItems(user);

        model.addAttribute("orders", orders);
        model.addAttribute("items", items);

        return "sites/orders";
    }
}
