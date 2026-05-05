package com.example.medicalstore.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          Model model) {
        User user = userService.login(email, password);
        if (user != null) return "redirect:/medicines";
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() { return "register"; }

    @PostMapping("/register")
    public String doRegister(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String password) {
        Customer c = new Customer(userService.generateId(), name, email, password);
        userService.register(c);
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/users/edit")
    public String editForm(@RequestParam String id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-edit";
    }

    @PostMapping("/users/edit")
    public String doEdit(@RequestParam String userId,
                         @RequestParam String name,
                         @RequestParam String email) {
        userService.update(userId, name, email);
        return "redirect:/users";
    }

    @GetMapping("/users/delete")
    public String doDelete(@RequestParam String id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
