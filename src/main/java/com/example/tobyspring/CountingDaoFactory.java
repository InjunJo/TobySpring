package com.example.tobyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

    /*@Bean
    public UserDao userDao(){
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(countingConnectionMaker());

        return userDao;

    }*/

    @Bean
    public CountingConnectionMaker countingConnectionMaker(){

        CountingConnectionMaker connectionMaker = new CountingConnectionMaker(simpleConnectionMaker());

        return connectionMaker;
    }

    @Bean
    public SimpleConnectionMaker simpleConnectionMaker(){
        SimpleConnectionMaker simpleConnectionMaker = new SimpleConnectionMaker();
        return simpleConnectionMaker;

    }

}
