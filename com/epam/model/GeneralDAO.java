package com.epam.carsharing.entity.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.LinkedBlockingQueue;

public interface GeneralDAO {
    ConnectionPool connectionPool = ConnectionPool.getUniqueInstance();
    Connection connection = connectionPool.getConnection();

    LinkedBlockingQueue<E> getAll();
    E getEntityById(K id);
    E update(E entity);
    boolean delete(K id);
    boolean create(E entity);
}
