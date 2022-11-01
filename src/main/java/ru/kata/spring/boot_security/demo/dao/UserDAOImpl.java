package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addRole(long userID, Role role) {
        getById(userID).addRole(role);
    }

    @Override
    public Set<Role> getRoles(long userID) {
        return entityManager.find(User.class, userID).getRoles();
    }

    @Override
    public User getByEmail(String email) {
        User user;
        try {
            user = (User) entityManager.createQuery("FROM User where email = :email").setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }

    @Override
    public User findByName(String userName) {
        User user;
        try {
            user = (User) entityManager.createQuery("FROM User where firstName = :name").setParameter("name", userName).getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }
}
