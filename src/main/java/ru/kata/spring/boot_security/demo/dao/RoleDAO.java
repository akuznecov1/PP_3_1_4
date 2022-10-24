package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
public interface RoleDAO {

    List<Role> getAllRoles();

    void add(Role role);

    void delete(long id);

    void edit(Role role);

    Role getById(long id);

    Role getByName(String roleName);
}
