package com.oth.sw.mikesmovieshop.mikesmovieshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", updatable = false, nullable = false)
    private long orderId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date createdAt = new Timestamp(new Date().getTime());

    @Column(updatable = false, nullable = false)
    private Double total;

    @OneToMany(cascade = {CascadeType.PERSIST})
    private Collection<CartItem> items;

    public Order(ArrayList<CartItem> items, Double total) {
        this.total = total;
        this.items = items;
    }

    public long getOrderId() {
        return orderId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Collection<CartItem> getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }

    public void setItems(Collection<CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", createdAt=" + createdAt +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}
