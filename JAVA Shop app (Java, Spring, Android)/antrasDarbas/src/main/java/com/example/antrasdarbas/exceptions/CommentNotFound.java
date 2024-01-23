package com.example.antrasdarbas.exceptions;

public class CommentNotFound extends RuntimeException{
    public CommentNotFound(int id) {
        super("Could not find comment " + id);
    }
}
