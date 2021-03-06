package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {

    @Autowired
    private ProductService movieService;

    @GetMapping("/products")
    public String showAllProducts(HttpServletRequest request, Model model) {
        int page = 0;
        int size = 3;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("movies", movieService.getAllMovies(PageRequest.of(page, size)));
        return "sites/products";
    }

    @GetMapping("/products/{id}")
    public String showProductDetails(@PathVariable("id") long id, Model model) {
        Movie movie = movieService.findMovie(id);

        model.addAttribute("movie", movie);
        return "sites/productDetails";
    }
}

