package com.tasks.tasks.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.tasks.models.User;

@RestController
public class AuthController {

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public void login(@RequestBody User user) {
        
    }

}
