package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    public User findById(Long id);
    public void save(User user);
    public void deleteById(Long id);
    public List<User> findAll();

    public UserDetails loadUserByUsername(String username);
}
