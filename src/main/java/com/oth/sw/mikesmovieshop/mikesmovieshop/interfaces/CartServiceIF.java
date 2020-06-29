package com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.CartItem;

import java.util.ArrayList;

public interface CartServiceIF {
    void addProduct(Movie movie);

    ArrayList<CartItem> getAllProducts();

    Double getTotal();

    void removeProduct(Movie movie);
}
