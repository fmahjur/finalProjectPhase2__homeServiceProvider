package ir.maktab.HomeServiceProvider.service;

import java.util.List;

public interface MainService<T> {
    void add(T t);

    void delete(T t);

    void update(T t);

    List<T> selectAll();
}
