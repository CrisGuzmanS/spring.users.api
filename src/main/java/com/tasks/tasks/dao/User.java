package com.tasks.tasks.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tasks.tasks.exceptions.InvalidCredentialsException;
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

    public com.tasks.tasks.models.User find(String email) {
        List<com.tasks.tasks.models.User> foundUsers = entityManager
                .createQuery("FROM User WHERE email = :email",
                        com.tasks.tasks.models.User.class)
                .setParameter("email", email)
                .getResultList();

        if (foundUsers.isEmpty()) {
            return null;
        }

        return foundUsers.get(0);
    }

    public void create(com.tasks.tasks.models.User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        com.tasks.tasks.models.User user = entityManager.find(com.tasks.tasks.models.User.class, id);
        entityManager.remove(user);
    }

    public boolean verify(com.tasks.tasks.models.User user) throws InvalidCredentialsException {
        com.tasks.tasks.models.User foundUser = this.find(user.getEmail());

        if (null == foundUser) {
            throw new InvalidCredentialsException(
                    "Las credenciales del usuario no pudieron ser verificadas");
        }

        return this.encryptor.verify(
                foundUser.getPassword(),
                user.getPassword());
    }
}
