package com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collection;

public interface OrderServiceIF {
    Order saveOrder(Order order);
}
