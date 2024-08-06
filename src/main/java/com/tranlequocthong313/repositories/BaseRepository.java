/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.repositories;

import com.tranlequocthong313.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;

/**
 * @author tranlequocthong313
 */
@Transactional
public interface BaseRepository<T, ID> {

    default <S extends T> List<S> findAll() {
        return findAll(null);
    }

    <S extends T> List<S> findAll(Map<String, String> queryParams);

    default T getReferenceById(ID id) {
        T o = findById(id).orElse(null);
        if (o == null) {
            throw new EntityNotFoundException(id.toString());
        }
        return o;
    }

    Optional<T> findById(ID id);

    <S extends T> S save(S entity);

    void delete(ID id);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);
}
