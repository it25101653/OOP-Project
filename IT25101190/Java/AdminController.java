package com.example.medicalstore.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String dashboard(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        return "admin";
    }

    @PostMapping("/admin/register")
    public String registerAdmin(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String level) {
        String id = adminService.generateId();
        AdminUser a = level.equals("SUPER")
                ? new SuperAdmin(id, name, email, password)
                : new AdminUser(id, name, email, password, "ADMIN");
        adminService.register(a);
        return "redirect:/admin";
    }

    @PostMapping("/admin/update")
    public String updateLevel(@RequestParam String adminId,
                              @RequestParam String level) {
        adminService.updateLevel(adminId, level);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deleteAdmin(@RequestParam String id) {
        adminService.delete(id);
        return "redirect:/admin";
    }
}