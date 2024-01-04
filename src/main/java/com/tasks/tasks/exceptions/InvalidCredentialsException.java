package com.tasks.tasks.exceptions;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException(String mensaje) {
        super(mensaje);
    }
}
