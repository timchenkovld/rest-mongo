package com.example.restmongo.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Student not found with id: %s";

    public EntityNotFoundException(String id) {
        super(String.format(ENTITY_NOT_FOUND_EXCEPTION, id));
    }
}
