package com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieServiceIF {
    public Movie addMovie(Movie movie);
    public Page<Movie> getAllMovies(Pageable pageable);
}
