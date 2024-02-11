package org.example.spring.database.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.bpp.Auditing;
import org.example.spring.bpp.Transaction;
import org.example.spring.database.entity.Company;
import org.example.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transaction
@Auditing
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company> {
    private final ConnectionPool pool1;
    private final List<ConnectionPool> pools;
    @Value("${db.pool.size}")
    private final Integer poolsize;

    @PostConstruct
    private void init() {
        log.info("Init Company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method");
        return Optional.of(new Company(id, null, Collections.emptyMap()));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method");
    }
}
