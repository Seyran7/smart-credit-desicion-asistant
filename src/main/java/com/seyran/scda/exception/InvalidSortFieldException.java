package com.seyran.scda.exception;

public class InvalidSortFieldException extends RuntimeException {

    public InvalidSortFieldException(String field) {
        super("Invalid sort field: " + field);
    }

}