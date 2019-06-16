package com.leilaodequadrinhos.api.model.dao;

import java.util.List;

public interface DAO<T> {

    T findById(Long id);
    List<T> findAll();
    Long count();
    void delete(T entity);
    void deleteById(Long id);
    void update(T entity);
}
