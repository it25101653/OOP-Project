package com.example.medicalstore.order;

public class OnlineOrder extends Order {

    private static final double DELIVERY = 50.0;

    public OnlineOrder(String orderId, String userId, String medId,
                       String medName, int quantity,
                       double totalPrice, String status) {
        super(orderId, userId, medId, medName, quantity, totalPrice, status);
    }

    @Override
    public double calculateTotal(double unitPrice, int qty) {
        return (unitPrice * qty) + DELIVERY; 
    }
}
