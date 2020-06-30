package com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.CartItem;

import java.util.ArrayList;

public interface CartServiceIF {
    void addProduct(Long id);

    ArrayList<CartItem> getAllProducts();

    Double getTotal();

    void removeProduct(Long id);

    void clearCart();
}
