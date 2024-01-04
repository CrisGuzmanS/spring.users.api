package com.tasks.tasks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.tasks.models.User;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    private com.tasks.tasks.dao.User user;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public void login(@RequestBody User requestedUser, HttpServletResponse response) {
        if (!user.verify(requestedUser)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

}
