package org.example.spring;

import org.example.spring.config.ApplicationConfiguration;
import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.database.repository.CrudRepository;
import org.example.spring.service.CompanyService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.Serializable;


public class Main {
    public static void main(String[] args) {
        String value = "Hello";
        System.out.println(CharSequence.class.isAssignableFrom(value.getClass()));
        System.out.println(BeanFactoryPostProcessor.class.isAssignableFrom(value.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(value.getClass()));

        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();
//      clazz -> String -> Map<String, Object>
//        System.out.println(context.getBean(ConnectionPool.class));
        var connectionPool = context.getBean("pool1", ConnectionPool.class);
        System.out.println(connectionPool);

        var companyService = context.getBean(CompanyService.class);
        System.out.println(companyService.findById(1));

        }
    }
}