package com.example.medicalstore.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @PostMapping("/orders/place")
    public String placeOrder(@RequestParam String userId,
                             @RequestParam String medId,
                             @RequestParam String medName,
                             @RequestParam int quantity,
                             @RequestParam double unitPrice) {
        orderService.placeOrder(userId, medId, medName, quantity, unitPrice);
        return "redirect:/orders";
    }

    @PostMapping("/orders/status")
    public String updateStatus(@RequestParam String orderId,
                               @RequestParam String status) {
        orderService.updateStatus(orderId, status);
        return "redirect:/orders";
    }

    @GetMapping("/orders/delete")
    public String deleteOrder(@RequestParam String id) {
        orderService.delete(id);
        return "redirect:/orders";
    }
}