package com.tasks.tasks.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class User implements com.tasks.tasks.dao.interfaces.User {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<com.tasks.tasks.models.User> get() {
        return entityManager.createQuery("FROM User", com.tasks.tasks.models.User.class).getResultList();
    }

    public com.tasks.tasks.models.User find(Long id) {
        return entityManager.find(com.tasks.tasks.models.User.class, id);
    }

    public void create(com.tasks.tasks.models.User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        com.tasks.tasks.models.User user = entityManager.find(com.tasks.tasks.models.User.class, id);
        entityManager.remove(user);
    }

    public boolean verify(com.tasks.tasks.models.User user) {
        com.tasks.tasks.models.User foundUser = entityManager
                .createQuery("FROM User WHERE email = :email AND password = :password",
                        com.tasks.tasks.models.User.class)
                .setParameter("email", user.getEmail())
                .setParameter("password", user.getPassword())
                .getSingleResult();

        return foundUser != null;
    }
}
