package com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;

import java.util.Map;

public interface CartServiceIF {
    public void addProduct(Movie movie);

    public Map<Movie, Integer> getAllProducts();

    public Double getTotal();
}
