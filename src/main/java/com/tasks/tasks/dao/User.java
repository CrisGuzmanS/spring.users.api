package com.tasks.tasks.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tasks.tasks.services.EncryptorService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class User implements com.tasks.tasks.dao.interfaces.User {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EncryptorService encryptor;

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
        List<com.tasks.tasks.models.User> foundUsers = entityManager
                .createQuery("FROM User WHERE email = :email",
                        com.tasks.tasks.models.User.class)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (foundUsers.isEmpty()) {
            return false;
        }

        return this.encryptor.verify(
                foundUsers.get(0).getPassword(),
                user.getPassword());
    }
}
