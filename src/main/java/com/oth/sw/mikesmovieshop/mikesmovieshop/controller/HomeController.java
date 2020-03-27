package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

//    @Autowired
//    private KundeServiceIF kundeService;

    @RequestMapping("/")
    public String start() {
        return "index";
    }

    @RequestMapping("/products")
    public String viewAllProducts() {
        return "sites/products";
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
