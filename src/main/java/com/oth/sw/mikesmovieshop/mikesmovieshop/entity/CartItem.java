package com.oth.sw.mikesmovieshop.mikesmovieshop.entity;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;

import javax.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id", updatable = false, nullable = false)
    private long cartItemId;

    @ManyToOne
    public Movie movie;
    public int quantity;

    public CartItem(Movie movie, int quantity) {
        this.movie = movie;
        this.quantity = quantity;
    }

    public long getCartItemId() {
        return cartItemId;
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
