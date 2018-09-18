package model.DAO;

import model.entity.AbstractEntity;

import java.util.List;

interface AbstractDAO<T extends AbstractEntity>{
    void saveOrUpdate(T t);
    void remove(Long id);
    T getById(Long id);
    List<T> getAll();
}
