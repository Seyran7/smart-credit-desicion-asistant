package com.seyran.scda.exception;

public class InvalidSortDirectionException extends RuntimeException {

    public InvalidSortDirectionException(String direction) {
        super("Invalid sort direction: " + direction +
                ". Allowed values are 'asc' or 'desc'.");
    }

}