package ru.kata.spring.boot_security.demo.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
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
    private RoleDAO roleDAO;

    @GetMapping("/admin")
    public String getAllUsers(User user, Model model, Authentication authentication){
        User currentUser= (User) authentication.getPrincipal();
        model.addAttribute("admin", userService.findAll());
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("roles",roleDAO.findAllRole());
        return "admin";
    }
    @PostMapping("/admin")
    public String createUser(@RequestParam("listRole")Long id, User user){
        user.setRoles(Collections.singleton(roleDAO.findRole(id)));
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("updateuser/{id}")
    public String updateUser(@RequestParam("listRole1") Long id, User user){
        System.out.println(user.getId());
        user.setRoles(Collections.singleton(roleDAO.findRole(id)));
        userService.save(user);
        return "redirect:/admin";
    }

}
