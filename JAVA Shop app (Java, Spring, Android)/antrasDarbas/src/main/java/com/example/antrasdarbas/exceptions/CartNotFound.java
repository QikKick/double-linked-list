package com.example.antrasdarbas.exceptions;

public class CartNotFound extends RuntimeException{
    public CartNotFound(int id) {
        super("Could not find cart " + id);
    }
}
