package model.DAO;

import model.entity.AbstractEntity;

import java.sql.ResultSet;
import java.util.concurrent.LinkedBlockingQueue;

interface AbstractDAO<T extends AbstractEntity>{

    void addOrUpdate(T t);
    void remove(int id);
    T getById(int id);
    LinkedBlockingQueue<T> getAll();
    T getFromDB(ResultSet resultSet);
    void saveToDB(String sql);

}
