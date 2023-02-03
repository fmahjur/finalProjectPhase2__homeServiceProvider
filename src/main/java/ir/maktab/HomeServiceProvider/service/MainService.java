package ir.maktab.HomeServiceProvider.service;

import java.util.List;

public interface MainService<T> {
    T add(T t);

    void remove(T t);

    T update(T t);

    T findById(Long id);

    List<T> selectAll();

    List<T> selectAllAvailableService();
}
