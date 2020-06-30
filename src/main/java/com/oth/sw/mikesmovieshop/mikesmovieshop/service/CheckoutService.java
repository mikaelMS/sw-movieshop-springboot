package com.oth.sw.mikesmovieshop.mikesmovieshop.service;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.CartItem;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Order;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.CheckoutServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CheckoutService implements CheckoutServiceIF {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    public void finishCheckout(UserDetails user) {
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
    }

}
