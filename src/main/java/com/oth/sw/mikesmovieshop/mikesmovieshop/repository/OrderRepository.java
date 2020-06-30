package com.oth.sw.mikesmovieshop.mikesmovieshop.repository;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
