package org.tienda.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String entity, String attribute) {
        super(entity + " no encontrado (atributo: " + attribute + ")");
    }
}
