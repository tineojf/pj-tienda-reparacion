package org.tienda.exceptions;

public class EntityAlreadeExistsException extends Exception {
    public EntityAlreadeExistsException(String entity, String attribute) {
        super(entity + " ya existe (atributo: " + attribute + ")");
    }
}
