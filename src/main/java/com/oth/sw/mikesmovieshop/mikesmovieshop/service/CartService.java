package com.oth.sw.mikesmovieshop.mikesmovieshop.service;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.CartServiceIF;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartService implements CartServiceIF {

//    private final ProductRepository productRepository;

    private Map<Movie, Integer> products = new HashMap<>();

    @Override
    public void addProduct(Movie movie) {
        if (products.containsKey(movie)) {
            products.replace(movie, products.get(movie) + 1);
        } else {
            products.put(movie, 1);
        }
    }

    @Override
    public Map<Movie, Integer> getAllProducts() {
        return Collections.unmodifiableMap(products);
    }

    @Override
    public Double getTotal() {
        Double sum = 0.0;

        for (Map.Entry<Movie, Integer> entry : products.entrySet()) {
            Movie movie = entry.getKey();
            sum += movie.getPrice();
        }

        return sum;
    }
}
