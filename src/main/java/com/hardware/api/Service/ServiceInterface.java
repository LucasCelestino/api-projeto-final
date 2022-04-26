package com.hardware.api.Service;

import java.util.List;

public interface ServiceInterface<T>
{
    List<T> findAll();
    T findById(Long id);
    T create(T obj);
    boolean update(T obj);
    boolean delete(Long id);
}
