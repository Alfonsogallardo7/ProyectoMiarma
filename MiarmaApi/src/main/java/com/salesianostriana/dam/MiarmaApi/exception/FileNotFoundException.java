package com.salesianostriana.dam.MiarmaApi.exception;

public class FileNotFoundException extends StorageException{

    public FileNotFoundException(String message, Exception exception) {
        super(message, exception);
    }

    public FileNotFoundException(String message) {
        super(message);
    }
}
