package com.example.medicalstore.order; 

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final String FILE = "data/orders.txt";

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty())
                    list.add(Order.fromFileString(line.trim()));
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public void placeOrder(String userId, String medId,
                           String medName, int qty, double unitPrice) {
        String id = "ORD" + System.currentTimeMillis();
        OnlineOrder temp = new OnlineOrder(id, userId, medId, medName, qty, 0, "PENDING");
        double total = temp.calculateTotal(unitPrice, qty);
        OnlineOrder saved = new OnlineOrder(id, userId, medId, medName, qty, total, "PENDING");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(saved.toFileString()); bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void updateStatus(String orderId, String newStatus) {
        List<Order> list = getAllOrders();
        for (Order o : list)
            if (o.getOrderId().equals(orderId)) o.setStatus(newStatus);
        saveAll(list);
    }

    public void delete(String orderId) {
        List<Order> list = getAllOrders().stream()
                .filter(o -> !o.getOrderId().equals(orderId))
                .collect(Collectors.toList());
        saveAll(list);
    }

    private void saveAll(List<Order> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, false))) {
            for (Order o : list) { bw.write(o.toFileString()); bw.newLine(); }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
