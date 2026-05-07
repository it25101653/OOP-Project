package com.example.medicalstore.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String viewCart(@RequestParam String userId, Model model) {
        model.addAttribute("cart", cartService.getCartForUser(userId));
        model.addAttribute("userId", userId);
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addItem(@RequestParam String userId,
                          @RequestParam String medId,
                          @RequestParam String medName,
                          @RequestParam int quantity,
                          @RequestParam double unitPrice) {
        cartService.addItem(new CartItem(userId, medId, medName, quantity, unitPrice));
        return "redirect:/cart?userId=" + userId;
    }

    @PostMapping("/cart/update")
    public String updateQty(@RequestParam String userId,
                            @RequestParam String medId,
                            @RequestParam int quantity) {
        cartService.updateQuantity(userId, medId, quantity);
        return "redirect:/cart?userId=" + userId;
    }

    @GetMapping("/cart/remove")
    public String removeItem(@RequestParam String userId,
                             @RequestParam String medId) {
        cartService.removeItem(userId, medId);
        return "redirect:/cart?userId=" + userId;
    }
}