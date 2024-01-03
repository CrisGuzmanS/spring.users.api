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
    public List<com.tasks.tasks.dao.interfaces.User> users() {
        throw new UnsupportedOperationException("Unimplemented method 'users'");
    }

}
