package com.example.medicalstore.cart;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {

    private static final String FILE = "data/cart.txt";

    public Cart getCartForUser(String userId) {
        List<CartItem> items = getAllItems().stream()
                .filter(i -> i.getUserId().equals(userId))
                .collect(Collectors.toList());
        return new Cart(userId, items);
    }

    private List<CartItem> getAllItems() {
        List<CartItem> list = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty())
                    list.add(CartItem.fromFileString(line.trim()));
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public void addItem(CartItem item) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(item.toFileString()); bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void updateQuantity(String userId, String medId, int newQty) {
        List<CartItem> all = getAllItems();
        for (CartItem i : all)
            if (i.getUserId().equals(userId) && i.getMedId().equals(medId))
                i.setQuantity(newQty);
        saveAll(all);
    }

    public void removeItem(String userId, String medId) {
        List<CartItem> all = getAllItems().stream()
                .filter(i -> !(i.getUserId().equals(userId) && i.getMedId().equals(medId)))
                .collect(Collectors.toList());
        saveAll(all);
    }

    public void clearCart(String userId) {
        List<CartItem> all = getAllItems().stream()
                .filter(i -> !i.getUserId().equals(userId))
                .collect(Collectors.toList());
        saveAll(all);
    }

    private void saveAll(List<CartItem> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, false))) {
            for (CartItem i : list) { bw.write(i.toFileString()); bw.newLine(); }
        } catch (IOException e) { e.printStackTrace(); }
    }
}