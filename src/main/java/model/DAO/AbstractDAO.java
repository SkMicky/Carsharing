package model.DAO;

import model.entity.BaseEntity;

import java.util.List;

interface AbstractDAO<T extends BaseEntity>{
    void saveOrUpdate(T t);
    void remove(long id);
    T getById(long id);
}
