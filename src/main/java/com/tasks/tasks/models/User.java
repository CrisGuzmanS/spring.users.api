package com.tasks.tasks.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "users")
public class User {
    @Getter
    @Setter
    @Id
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;
    
    @Getter
    @Setter
    @Column(name = "email")
    private String email;
    
    @Getter
    @Setter
    @Column(name = "password")
    private String password;
}
