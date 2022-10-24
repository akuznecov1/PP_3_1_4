package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface UserService {
    List<User> getAllUser();
    void add(User user);
    void delete(long id);
    void edit(User user);
    User getById(long id);
    void addRole(long userID, Role role);
    Set<Role> getRoles(long userID);
    User getByName(String userName);
}
