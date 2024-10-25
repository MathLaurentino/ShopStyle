package com.shopstyle.ms_catalog.exception;

public class CategoryIsInactive extends RuntimeException{

    public CategoryIsInactive(String message) {
        super(message);
    }

}
