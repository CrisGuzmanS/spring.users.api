package com.tasks.tasks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.tasks.models.User;

@RestController
public class UserController {

    @Autowired
    private com.tasks.tasks.dao.User user;

    @RequestMapping(value = "api/users")
    public List<User> users() {
        return user.users();
    }

    @RequestMapping(value = "api/users/{id}")
    public User user(@PathVariable Long id) {

        User user = new User();
        user.setId(id);
        user.setName("Cristian Guzmán");

        return user;
    }

}
