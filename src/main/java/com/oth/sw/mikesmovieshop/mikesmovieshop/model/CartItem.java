package com.oth.sw.mikesmovieshop.mikesmovieshop.model;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;

public class CartItem {
    public Movie movie;
    public int quantity;

    public CartItem(Movie movie, int quantity) {
        this.movie = movie;
        this.quantity = quantity;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
