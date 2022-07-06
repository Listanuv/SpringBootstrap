package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    public User findById(Long id);
    public void save(User user);
    public void deleteById(Long id);
    public List<User> findAll();
    UserDetails loadUserByUsername(String userName);

}
