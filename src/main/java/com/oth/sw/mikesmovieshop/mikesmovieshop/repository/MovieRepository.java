package com.oth.sw.mikesmovieshop.mikesmovieshop.repository;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
