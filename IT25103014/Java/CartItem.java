package com.example.medicalstore.cart;

public class CartItem {

    private String userId;
    private String medId;
    private String medName;
    private int    quantity;
    private double unitPrice;

    public CartItem(String userId, String medId, String medName, int quantity, double unitPrice) {
        this.userId    = userId;
        this.medId     = medId;
        this.medName   = medName;
        this.quantity  = quantity;
        this.unitPrice = unitPrice;
    }

    public String getUserId() {
        return userId;
    }
    public String getMedId() {
        return medId;
    }
    public String getMedName() {
        return medName;
    }
    public int    getQuantity() {
        return quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemTotal() {
        return unitPrice * quantity;
    }

    public String toFileString() {
        return userId+","+medId+","+medName+","+quantity+","+unitPrice;
    }

    public static CartItem fromFileString(String line) {
        String[] p = line.split(",", 5);
        return new CartItem(p[0], p[1], p[2],
                Integer.parseInt(p[3]),
                Double.parseDouble(p[4]));
    }
}