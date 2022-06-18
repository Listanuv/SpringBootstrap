package ru.kata.spring.boot_security.demo.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Controller
public class UserController {
    @Autowired
    private UserDAO userService;


    @GetMapping("/user")
    public User getUser(Authentication authentication){
        User user= (User) authentication.getPrincipal();
        return user;
    }
}
