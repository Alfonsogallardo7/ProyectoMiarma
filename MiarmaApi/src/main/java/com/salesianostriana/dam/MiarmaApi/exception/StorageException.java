package com.salesianostriana.dam.MiarmaApi.exception;

public class StorageException extends RuntimeException{

    public StorageException (String message, Exception exception) {
        super(message, exception);
    }

    public StorageException(String message) {
        super(message);
    }
}
