package com.oth.sw.mikesmovieshop.mikesmovieshop.service;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.CartServiceIF;
import com.oth.sw.mikesmovieshop.mikesmovieshop.model.CartItem;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartService implements CartServiceIF {

    private HashMap<Movie, Integer> products = new HashMap<>();
    ArrayList<CartItem> cart = new ArrayList<>();

    @Override
    public void addProduct(Movie movie) {
        for (CartItem cartItem : cart) {
            if (cartItem.getMovie().getMovieId() == movie.getMovieId()) {
                System.out.println("lnin");
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
        System.out.println("nio");
        if (cart.contains(movie)) {
            System.out.println("hwieor");
            for (CartItem cartItem : cart) {
                if (cartItem.getMovie() == movie) {
                    if (cartItem.getQuantity() > 1) {
                        System.out.println("lnweil");
                        cartItem.setQuantity(cartItem.getQuantity() - 1);
                    } else if (cartItem.getQuantity() == 1) {
                        System.out.println("hierwe");
                        cart.remove(movie);
                    }
                }
            }
        }

    }
}
