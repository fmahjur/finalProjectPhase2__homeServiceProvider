package ir.maktab.HomeServiceProvider.service;

import java.util.List;

public interface MainService<T> {
    T add(T t);

    void delete(T t);

    T update(T t);

    List<T> selectAll();
}
