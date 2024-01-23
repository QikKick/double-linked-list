package com.example.antrasdarbas.exceptions;

public class WarehouseNotFound extends RuntimeException{
    public WarehouseNotFound(int id) {
        super("Could not find warehouse " + id);
    }
}
