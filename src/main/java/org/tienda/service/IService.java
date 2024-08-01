package org.tienda.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    T findById(Long id);

    T save(T t);

    T update(T t);

    T delete(Long id);
}
