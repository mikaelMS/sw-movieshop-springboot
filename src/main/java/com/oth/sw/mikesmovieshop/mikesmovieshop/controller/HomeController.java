package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.Movie;
import com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces.MovieServiceIF;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String start() {
        return "index.html";
    }

//    @RequestMapping("/products")
//    public String viewAllProducts() {
//        return "sites/products";
//    }

    @RequestMapping("/cart")
    public String viewCart() {
        return "sites/cart";
    }

    @RequestMapping("/account/login")
    public String login() {
        return "sites/account/login";
    }

    @RequestMapping("/account/registration")
    public String register() {
        return "sites/account/registration";
    }

//    @RequestMapping("/registrieren")
//    public String registrieren(
//            @ModelAttribute("vorname") String vname,
//            @ModelAttribute("nachname") String nname,
//            Model model
//    ) {
//        Kunde kunde = new Kunde();
//        kunde.setVorname(vname);
//        kunde.setNachname(nname);
//        kunde = kundeService.kundeAnlegen(kunde);
//        model.addAttribute("kundennr", kunde.getKundenNr());
//
//        return "kundenkonto";
//    }

}
