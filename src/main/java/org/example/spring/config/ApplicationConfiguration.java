package org.example.spring.config;

import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.database.repository.UserRepository;
import org.example.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

@Import(WebConfiguration.class)
@Configuration(proxyBeanMethods = true)
public class ApplicationConfiguration {

        @Bean("pool2")
        @Scope(BeanDefinition.SCOPE_SINGLETON)
        public ConnectionPool pool2(@Value("${db.username}") String userName) {
                return new ConnectionPool(userName, 20);
        }
        @Bean
        public ConnectionPool pool3() {
                return new ConnectionPool("test-pool", 25);
        }
        @Bean
        @Profile("prod|web")
//      ! & |
        public UserRepository userRepository2(ConnectionPool pool2){
                return new UserRepository(pool2);
        }
        @Bean
        public UserRepository userRepository3(){
                var connectionPool1 = pool3();
                var connectionPool2 = pool3();
                var connectionPool3 = pool3();
                return new UserRepository(pool3());
        }
}
