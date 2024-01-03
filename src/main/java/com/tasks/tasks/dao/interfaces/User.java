package com.tasks.tasks.dao.interfaces;

import java.util.List;

public interface User {
    List<com.tasks.tasks.models.User> users();

    void delete(Long id);
}