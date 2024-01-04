package com.tasks.tasks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.tasks.exceptions.InvalidCredentialsException;
import com.tasks.tasks.models.User;
import com.tasks.tasks.services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User requestedUser, HttpServletResponse response) {

        try {
            return userService.login(requestedUser);
        } catch (InvalidCredentialsException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return e.getMessage();
        }
    }

}
