package com.example.antrasdarbas.exceptions;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(int id) {
        super("Could not find product " + id);
    }
}
