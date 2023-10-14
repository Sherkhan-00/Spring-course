package org.example;

import org.example.database.pool.ConnectionPool;
import org.example.database.repository.CompanyRepository;
import org.example.database.repository.UserRepository;
import org.example.ioc.Container;
import org.example.service.UserService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;


public class Main {
    public static void main(String[] args) {
        String value = "Hello";
        System.out.println(CharSequence.class.isAssignableFrom(value.getClass()));
        System.out.println(BeanFactoryPostProcessor.class.isAssignableFrom(value.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(value.getClass()));

        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
//      clazz -> String -> Map<String, Object>
//        System.out.println(context.getBean(ConnectionPool.class));
        var connectionPool = context.getBean("p1", ConnectionPool.class);
        System.out.println(connectionPool);

        var companyRepository = context.getBean("companyRepository", CompanyRepository.class);
        System.out.println(companyRepository);

        }
    }
}