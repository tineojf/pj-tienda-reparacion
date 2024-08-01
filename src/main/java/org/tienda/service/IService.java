package org.tienda.service;

import org.tienda.exceptions.EntityAlreadyExistsException;
import org.tienda.exceptions.EntityNotFoundException;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    T findById(Long id) throws EntityNotFoundException;

    T save(T t) throws EntityAlreadyExistsException;

    T update(T t) throws EntityNotFoundException, EntityAlreadyExistsException;

    T delete(Long id) throws EntityNotFoundException;
}
