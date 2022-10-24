package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
public interface RoleService {

    List<Role> getAllRoles();
    void add(Role role);
    void delete(long id);
    void edit(Role role);
    Role getById(long id);
    Role getByName(String roleName);

}







