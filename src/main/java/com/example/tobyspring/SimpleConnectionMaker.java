package com.example.tobyspring;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker implements ConnectionMaker {

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        java.sql.Connection conn = null;
        Class.forName("org.mariadb.jdbc.Driver");

        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/spring","root","0347793");

        return conn;
    }

}
