package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserDAO userDAO;

    private final RoleService roleService;

    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    private void setRolesForUser(User user) {
        user.setRoles(user.getRoles().stream().map(role -> roleService.getByName(role.getName())).collect(Collectors.toSet()));
    }





    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }




    public UserServiceImpl(UserDAO userDAO, RoleService roleService) {
        this.userDAO = userDAO;


        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Transactional
    @Override
    public void add(User user) {
       user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
       setRolesForUser(user);
        userDAO.add(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Transactional
    @Override
    public void edit(User user) {
        User userFromDb = userDAO.getById(user.getId());
        if(!userFromDb.getPassword().equals(user.getPassword())){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }

        setRolesForUser(user);

        userDAO.edit(user);
    }

    @Override
    public User getById(long id) {
        return userDAO.getById(id);
    }

    @Transactional
    @Override
    public void addRole(long userID, Role role) {
        userDAO.addRole(userID, role);
    }

    @Override
    public Set<Role> getRoles(long userID) {
        return userDAO.getRoles(userID);
    }

    @Override
    public User getByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    @Override
    public User findByName(String userName) {
        return userDAO.findByName(userName);
    }

}