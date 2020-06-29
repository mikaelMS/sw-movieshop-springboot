package com.oth.sw.mikesmovieshop.mikesmovieshop.service;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.CartItem;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Order;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.OrderServiceIF;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.CartItemRepository;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.MovieRepository;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class OrderService implements OrderServiceIF {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public void saveOrder(Order order) {
        Collection<CartItem> cart = (Collection<CartItem>) cartItemRepository.saveAll(order.getItems());
        order.setItems(cart);
        Order savedOrder = orderRepository.save(order);
        System.out.println(savedOrder.toString());
    }
}
