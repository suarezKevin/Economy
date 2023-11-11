package com.suarez.economy.exception;

import java.text.MessageFormat;

public class ClientNotFoundException extends RuntimeException{
    // A constructor that takes a string as a parameter and passes it to the super
    // class.
    public ClientNotFoundException(String message) {
        super(MessageFormat.format("Client not found: {0}", message));
    }
}
