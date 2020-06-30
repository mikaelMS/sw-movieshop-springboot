package com.oth.sw.mikesmovieshop.mikesmovieshop.service;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.CartServiceIF;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.CartItem;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartService implements CartServiceIF {

    ArrayList<CartItem> cart = new ArrayList<>();

    @Override
    public void addProduct(Movie movie) {
        for (CartItem cartItem : cart) {
            if (cartItem.getMovie().getMovieId() == movie.getMovieId()) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }
        cart.add(new CartItem(movie, 1));
    }

    @Override
    public ArrayList<CartItem> getAllProducts() {
        return cart;
    }

    @Override
    public Double getTotal() {
        Double sum = 0.0;

        for (CartItem cartItem : cart) {
            sum += cartItem.getMovie().getPrice() * cartItem.getQuantity();
        }

        return sum;
    }

    @Override
    public void removeProduct(Movie movie) {
        for (CartItem cartItem : cart) {
            if (cartItem.getMovie().getMovieId() == movie.getMovieId()) {
                if (cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    return;
                } else if (cartItem.getQuantity() == 1) {
                    cart.remove(cartItem);
                    return;
                }
            }
        }
    }

    @Override
    public void clearCart() {
        cart = new ArrayList<>();
    }
}
