package com.example.demo.exception;

public class ObjectNotFoundException extends RuntimeException  {
    public ObjectNotFoundException() {
        super();
    }
    public ObjectNotFoundException(String messsage) {
        super(messsage);
    }
}
