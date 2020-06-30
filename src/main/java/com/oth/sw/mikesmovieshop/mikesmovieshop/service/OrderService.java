package com.oth.sw.mikesmovieshop.mikesmovieshop.service;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.CartItem;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Order;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.OrderServiceIF;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.CartItemRepository;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.MovieRepository;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class OrderService implements OrderServiceIF {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        Collection<CartItem> cart = (Collection<CartItem>) cartItemRepository.saveAll(order.getItems());
        order.setItems(cart);
        return orderRepository.save(order);
    }

    @Override
    public Collection<Order> getUserOrders(UserDetails user) {
        User currentUser = userService.findUser(user.getUsername());
        return currentUser.getOrders();
    }

    @Override
    public Collection<CartItem> getUserOrderItems(UserDetails user) {
        User currentUser = userService.findUser(user.getUsername());
        Collection<Order> orders = currentUser.getOrders();
        Collection<CartItem> items = null;

        for (Order order : orders) {
            items = order.getItems();
        }

        return items;
    }
}
