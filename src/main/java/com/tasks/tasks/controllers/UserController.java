package com.tasks.tasks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.tasks.models.User;

@RestController
public class UserController {

    @Autowired
    private com.tasks.tasks.dao.User user;

    @RequestMapping(value = "api/users")
    public List<User> users() {
        return user.get();
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public List<User> createUser() {
        return user.get();
    }

    @RequestMapping(value = "api/users/{id}")
    public User user(@PathVariable Long id) {
        return user.find(id);
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUsers(@PathVariable Long id) {
        user.delete(id);
    }
}
