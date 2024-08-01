package org.tienda.exceptions;

public class EntityAlreadyExistsException extends Exception {
    public EntityAlreadyExistsException(String entity, String attribute) {
        super(entity + " ya existe (atributo: " + attribute + ")");
    }
}
