package com.ay.wewin.api.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public interface IServices<T extends Serializable> {

    T create(final T entity);

    T update(final T entity);

    T findById(final String id);

    List<T> findAll();

    Page<T> findPaginated(int page, int size);

    void delete(final T entity);

    void deleteById(final String id);
    
}
