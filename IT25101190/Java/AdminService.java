package com.example.medicalstore.admin;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private static final String FILE = "data/admins.txt";

    public List<AdminUser> getAllAdmins() {
        List<AdminUser> list = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty())
                    list.add(AdminUser.fromFileString(line.trim()));
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public void register(AdminUser a) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(a.toFileString()); bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public AdminUser login(String email, String password) {
        return getAllAdmins().stream()
                .filter(a -> a.getEmail().equals(email) && a.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    public void updateLevel(String adminId, String newLevel) {
        List<AdminUser> list = getAllAdmins();
        for (AdminUser a : list)
            if (a.getAdminId().equals(adminId)) a.setLevel(newLevel);
        saveAll(list);
    }

    public void delete(String adminId) {
        List<AdminUser> list = getAllAdmins().stream()
                .filter(a -> !a.getAdminId().equals(adminId))
                .collect(Collectors.toList());
        saveAll(list);
    }

    private void saveAll(List<AdminUser> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, false))) {
            for (AdminUser a : list) { bw.write(a.toFileString()); bw.newLine(); }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public String generateId() { return "ADM" + System.currentTimeMillis(); }
}