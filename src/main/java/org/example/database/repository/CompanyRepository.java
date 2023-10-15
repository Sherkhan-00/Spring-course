package org.example.database.repository;

import jakarta.annotation.PostConstruct;
import org.example.bpp.Auditing;
import org.example.bpp.InjectBean;
import org.example.bpp.Transaction;
import org.example.database.entity.Company;
import org.example.database.pool.ConnectionPool;

import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {
    @InjectBean
    private  ConnectionPool connectionPool;

    @PostConstruct
    private void init(){
        System.out.println("Init Company repository");
    }
    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method");
    }
}
