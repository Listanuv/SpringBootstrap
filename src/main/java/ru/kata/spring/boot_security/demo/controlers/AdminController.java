package ru.kata.spring.boot_security.demo.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
    @Autowired
    private UserDAO userService;

    @GetMapping("/admin")
    public String getAllUsers(Model model){
        model.addAttribute("admin", userService.findAll());
        return "admin";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        userService.deleteById(id);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.save(user);
        return "redirect:/admin";
    }
}
