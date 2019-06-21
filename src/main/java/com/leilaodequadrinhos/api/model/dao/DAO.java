package com.leilaodequadrinhos.api.model.dao;

import java.util.List;

public interface DAO<T> {

    T findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
    void update(T entity);
    void insert(T entity);
}