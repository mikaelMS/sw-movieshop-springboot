package com.oth.sw.mikesmovieshop.mikesmovieshop.controller;

import com.oth.sw.mikesmovieshop.mikesmovieshop.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private CartController cartController;

    @RequestMapping("/cart/checkout")
    public String checkOut(Model model, @AuthenticationPrincipal UserDetails user) {
        // check if user is logged in
        if(user == null) {
            return "redirect:/login";
        } else {
            checkoutService.finishCheckout(user);

            model.addAttribute("success", true);
            return cartController.viewCart(model);
        }
    }
}
