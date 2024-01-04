package com.tasks.tasks.dao.interfaces;

import java.util.List;

public interface User {
    List<com.tasks.tasks.models.User> get();

    void delete(Long id);

    boolean verify(String email, String password);
}