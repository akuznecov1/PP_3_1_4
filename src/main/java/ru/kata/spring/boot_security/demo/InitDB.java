package ru.kata.spring.boot_security.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class InitDB implements ApplicationRunner {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleService.add(roleUser);

        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.addRole(roleUser);
        userService.add(user);

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleService.add(roleAdmin);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.addRole(roleAdmin);
        userService.add(admin);
    }
}
