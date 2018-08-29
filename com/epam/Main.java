package com.epam.carsharing.entity;

import com.epam.carsharing.entity.model.*;

public class Main {

    public static void main(String[] args) {
        ConnectionPool cp = ConnectionPool.getUniqueInstance();
        cp.getConnection();
     }
}
