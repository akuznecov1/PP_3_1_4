package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;
public interface UserDAO{
    List<User> getAllUser();
    void add(User user);
    void delete(long id);
    void edit(User user);
    User getById(long id);
    void addRole(long userID, Role role);
    Set<Role> getRoles(long userID);
    User getByName(String userName);
}
