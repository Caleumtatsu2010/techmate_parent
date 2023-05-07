package com.caleumtatsu2010.cassandra.dao;

import java.util.List;
import java.util.UUID;

public interface CASDao<T> {

    public List<T> getAll();

    public T get(UUID id);

    public void insert(T t);

    public void update(T t, UUID id);
    
    public void delete(UUID id);

}
