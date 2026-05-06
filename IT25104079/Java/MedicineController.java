package com.example.medicalstore.medicine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    public String listMedicines(@RequestParam(required = false) String search,
                                Model model) {
        List<Medicine> list = (search != null && !search.isEmpty())
                ? medicineService.search(search)
                : medicineService.getAllMedicines();
        model.addAttribute("medicines", list);
        model.addAttribute("search", search);
        return "medicines";
    }

    @PostMapping("/medicines/add")
    public String addMedicine(@RequestParam String name,
                              @RequestParam String category,
                              @RequestParam double price,
                              @RequestParam int stock) {
        OTCMedicine m = new OTCMedicine(
                medicineService.generateId(), name, category, price, stock);
        medicineService.add(m);
        return "redirect:/medicines";
    }

    @GetMapping("/medicines/edit")
    public String editForm(@RequestParam String id, Model model) {
        model.addAttribute("med", medicineService.findById(id));
        return "medicine-edit";
    }

    @PostMapping("/medicines/edit")
    public String doEdit(@RequestParam String medId,
                         @RequestParam double price,
                         @RequestParam int stock) {
        medicineService.update(medId, price, stock);
        return "redirect:/medicines";
    }

    @GetMapping("/medicines/delete")
    public String doDelete(@RequestParam String id) {
        medicineService.delete(id);
        return "redirect:/medicines";
    }
}
