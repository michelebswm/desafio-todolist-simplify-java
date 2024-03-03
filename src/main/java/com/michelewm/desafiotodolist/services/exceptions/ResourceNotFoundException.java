package com.michelewm.desafiotodolist.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("Task not found, id: " + id);
    }

}
