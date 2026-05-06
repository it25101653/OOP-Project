package com.example.medicalstore.order;

public class Order {

    private String orderId;
    private String userId;
    private String medId;
    private String medName;
    private int    quantity;
    private double totalPrice;
    private String status;

    public Order(String orderId, String userId, String medId,
                 String medName, int quantity, double totalPrice, String status) {
        this.orderId    = orderId;
        this.userId     = userId;
        this.medId      = medId;
        this.medName    = medName;
        this.quantity   = quantity;
        this.totalPrice = totalPrice;
        this.status     = status;
    }

    public String getOrderId() {
        return orderId; }
    public String getUserId() {
        return userId; }
    public String getMedId() {
        return medId; }
    public String getMedName(){
        return medName; }
    public int    getQuantity() {
        return quantity; }
    public double getTotalPrice() {
        return totalPrice; }
    public String getStatus() {
        return status; }

    public void setStatus(String status) { this.status = status; }

    public double calculateTotal(double unitPrice, int qty) {
        return unitPrice * qty;
    }

    public String toFileString() {
        return orderId+","+userId+","+medId+","+
                medName+","+quantity+","+totalPrice+","+status;
    }

    public static Order fromFileString(String line) {
        String[] p = line.split(",", 7);
        return new Order(p[0], p[1], p[2], p[3],
                Integer.parseInt(p[4]),
                Double.parseDouble(p[5]), p[6]);
    }
}