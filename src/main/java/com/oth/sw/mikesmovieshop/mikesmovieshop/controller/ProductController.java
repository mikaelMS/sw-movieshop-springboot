package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.MovieServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {

    @Autowired
    private MovieServiceIF movieService;

//    @RequestMapping("/products")
//    public String viewAllProducts1() {
//
//        Iterable<Movie> allMovies = movieService.getAllMovies();
//        if (allMovies == null) {
//            // TODO: add proper logging
//            System.out.println("TCL: allMovies was null");
//            return "sites/products";
//        }
//        for (Movie movie : allMovies) {
//            System.out.println(movie.toString());
//        }
//
//        return "sites/products";
//    }

    @RequestMapping("/products")
    public String customersPage(HttpServletRequest request, Model model) {
        int page = 0;
        int size = 1;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("movies", movieService.getAllMovies(PageRequest.of(page, size)));
        return "sites/products";
    }
}

