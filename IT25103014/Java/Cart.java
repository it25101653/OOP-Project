package com.example.medicalstore.cart;

import java.util.List;

public class Cart {

    private String userId;
    private List<CartItem> items;

    public Cart(String userId, List<CartItem> items) {
        this.userId = userId;
        this.items  = items;
    }

    public String getUserId() {
        return userId;
    }
    public List<CartItem> getItems()  {
        return items;
    }
    public int getCount() {
        return items.size();
    }

    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(CartItem::getItemTotal)
                .sum();
    }
}