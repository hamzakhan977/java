package com.hamzaniazi.ums.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CrudUtil<E, O, I> {
    E getObject(Long id);

    List<E> getAll();

    O getOneResponse(Long id);

    List<O> getAllResponses();

    Page<O> getAllWithPagination(PageRequest request);

    void add(I request);

    void update(I request);

    void delete(Long id);
}
