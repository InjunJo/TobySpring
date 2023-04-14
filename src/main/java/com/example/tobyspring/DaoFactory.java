package com.example.tobyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import user.dao.UserDaoJdbc;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {


    /*@Bean
    public UserDaoJdbc userDao(){

        UserDaoJdbc userDaoJdbc = new UserDaoJdbc();

        *//*userDaoJdbc.setDataSource(dataSource());*//*

        return userDaoJdbc;
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        ConnectionMaker connectionMaker = new SimpleConnectionMaker();

        return connectionMaker;
    }

    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.mariadb.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mariadb://localhost:3307/spring");
        dataSource.setUsername("root");
        dataSource.setPassword("0347793");

        return dataSource;

    }*/


}
