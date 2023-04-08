package com.lab1917tapoimarius.Exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Long id) {
        super("Could not find " + id);
    }
}
