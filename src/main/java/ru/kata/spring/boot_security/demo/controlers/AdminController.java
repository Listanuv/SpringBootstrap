package ru.kata.spring.boot_security.demo.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.dao.RoleDAOImpl;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
    @Autowired
    private UserDAO userService;
    @Autowired
    private RoleDAOImpl roleDAO;

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
    public String createUserForm(User user,Model model){
        model.addAttribute("roles",roleDAO.findAllRole());
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@RequestParam("listRole")Long id, User user){
        user.setRoles(Collections.singleton(roleDAO.findRole(id)));
        userService.save(user);
        return "redirect:/admin";
    }

}
