package com.suarez.economy.exception;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException{

    // A constructor that takes a string as a parameter and passes it to the super
    // class.
    public UserNotFoundException(String message) {
        super(MessageFormat.format("User not found: {0}", message));
    }

}
