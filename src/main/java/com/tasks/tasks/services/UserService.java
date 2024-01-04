package com.tasks.tasks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasks.tasks.exceptions.InvalidCredentialsException;
import com.tasks.tasks.models.User;

@Service
public class UserService {

    @Autowired
    private com.tasks.tasks.dao.User user;

    @Autowired
    private JWTTokenComponent jwt;

    public String login(User requiredUser) throws InvalidCredentialsException {
        if (!user.verify(requiredUser)) {
            throw new InvalidCredentialsException("El usuario no pudo ser verificado");
        }

        requiredUser = user.find(requiredUser.getEmail());

        return jwt.create(
                requiredUser.getId(),
                requiredUser.getEmail());
    }
}
