package com.tasks.tasks.services;

import org.springframework.stereotype.Service;

import de.mkammerer.argon2.Argon2;

@Service
public class EncryptorService {

    private Argon2 encryptor;

    public EncryptorService(Argon2 encryptor) {
        this.encryptor = encryptor;
    }

    public String encrypt(String text) {
        return this.encryptor.hash(1, 1024, 1, text);
    }
}
