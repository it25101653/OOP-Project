package com.example.medicalstore.medicine;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedicineService {

    private static final String FILE = "data/medicines.txt";

    public List<Medicine> getAllMedicines() {
        List<Medicine> list = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty())
                    list.add(Medicine.fromFileString(line.trim()));
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public List<Medicine> search(String query) {
        return getAllMedicines().stream()
                .filter(m -> m.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Medicine findById(String medId) {
        return getAllMedicines().stream()
                .filter(m -> m.getMedId().equals(medId))
                .findFirst().orElse(null);
    }

    public void add(OTCMedicine m) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(m.toFileString()); bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void update(String medId, double newPrice, int newStock) {
        List<Medicine> list = getAllMedicines();
        for (Medicine m : list)
            if (m.getMedId().equals(medId)) {
                m.setPrice(newPrice);
                m.setStock(newStock);
            }
        saveAll(list);
    }

    public void delete(String medId) {
        List<Medicine> list = getAllMedicines().stream()
                .filter(m -> !m.getMedId().equals(medId))
                .collect(Collectors.toList());
        saveAll(list);
    }

    private void saveAll(List<Medicine> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, false))) {
            for (Medicine m : list) { 
                bw.write(m.toFileString()); bw.newLine(); 
            }
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }

    public String generateId() { 
        return "MED" + System.currentTimeMillis(); 
    }
}
