package model.DAO;

import model.entity.BaseEntity;

interface BaseDAO<T extends BaseEntity>{
    void saveOrUpdate(T t);
    void remove(long id);
    T getById(long id);
}
