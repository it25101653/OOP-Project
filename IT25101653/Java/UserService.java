package com.example.medicalstore.user;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String FILE = "data/users.txt";

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return users;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty())
                    users.add(User.fromFileString(line.trim()));
        } catch (IOException e) { e.printStackTrace(); }
        return users;
    }

    public void register(Customer c) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(c.toFileString());
            bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public User login(String email, String password) {
        return getAllUsers().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    public User findById(String userId) {
        return getAllUsers().stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst().orElse(null);
    }

    public void update(String userId, String newName, String newEmail) {
        List<User> users = getAllUsers();
        for (User u : users)
            if (u.getUserId().equals(userId)) {
                u.setName(newName);
                u.setEmail(newEmail);
            }
        saveAll(users);
    }

    public void delete(String userId) {
        List<User> remaining = getAllUsers().stream()
                .filter(u -> !u.getUserId().equals(userId))
                .collect(Collectors.toList());
        saveAll(remaining);
    }

    private void saveAll(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, false))) {
            for (User u : users) { bw.write(u.toFileString()); bw.newLine(); }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public String generateId() { return "USR" + System.currentTimeMillis(); }
}
