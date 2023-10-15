package org.example.database.repository;

import org.example.bpp.InjectBean;
import org.example.database.pool.ConnectionPool;

public class CompanyRepository {
    @InjectBean
    private  ConnectionPool connectionPool;

}
