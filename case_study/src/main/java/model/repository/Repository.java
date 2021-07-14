package model.repository;

import java.util.List;

public interface Repository<E> {
    List<E> findAll();
    E findById(int id);
    boolean add(E e);
    boolean update(E e);
    void delete(int id);
}
