package org.DanielLanzaProject1.Utils;

import java.util.List;

public interface DatabaseInterface<T> {


    int create(T t);

    List<T> getAll();

    T getId(int id);

    T getByCredentials(String username,String password);

    T update(T t);

    boolean delete(T t);

}
