package model.DAO;

import model.entity.AbstractEntity;

import java.util.List;

interface AbstractDAO<T extends AbstractEntity>{
    void saveOrUpdate(T t);
    void remove(long id);
    T getById(long id);
}
