package com.tasks.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Configuration
public class SecurityConfig {

    @Bean
    public Argon2 encryptor() {
        return Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    }
}
